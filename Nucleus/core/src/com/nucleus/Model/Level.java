package com.nucleus.Model;

import java.util.ArrayList;
import java.util.List;

public class Level implements ILevel {
    private int width;
    private int height;

    private float runTime = 0;
    private float lastUpdateTime = 0;
    private float dummyUpdateVariable = 1;

    private boolean gameWon = false;
    private boolean gameLost = false;

    private boolean gamePaused = false;

    private enum GameState{
        RUNNING,
        PAUSED,
        PAUSEDWIN,
        PAUSEDLOSE
    }

    private GameState currentState = GameState.RUNNING;

    private INucleonGun gun;
    private List<INucleon> airborneNucleons = new ArrayList<INucleon>();
    private IMolecule molecule;
    private IGluonPoint[] gluons;

    public Level(int width, int height, INucleonGun gun, IMolecule molecule, IGluonPoint[] gluons){
        this.width = width;
        this.height = height;
        this.gun = gun;
        this.molecule = molecule;
        this.gluons = gluons;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public boolean isGameWon() {
        return gameWon;
    }

    public boolean isGameLost() { return  gameLost; }

    public boolean isGamePaused() { return gamePaused; }


    public INucleonGun getNucleonGun(){
        return gun;
    }

    public List<INucleon> getAirborneNucleons(){
        return airborneNucleons;
    }

    public IMolecule getMolecule(){
        return molecule;
    }

    //Possible obsolete, required for bugtesting at the moment
    public IGluonPoint[] getGluons() {
        return gluons;
    }

    /*Function should probably be removed*/
    public void addAirborneNucleon(INucleon nucleon){
        airborneNucleons.add(nucleon);
    }

    public boolean isOutOfBoundsCheck(INucleon nucleon){
        float x = nucleon.getPosition().getX();
        float y = nucleon.getPosition().getY();
        float bufferSize = 50; //nucleons aren't considered out of bounds until their "trails" are completely off-screen
        return x - nucleon.getRadius()>=width+bufferSize || x + nucleon.getRadius()<=0-bufferSize ||
                y - nucleon.getRadius()>=height+bufferSize || y + nucleon.getRadius()<=0-bufferSize;
    }

    //TODO: Check so this still works correctly with tests
    public void removeOutOfBoundsNucleons(){ // checks if any nucleons in airborne Nucleons is out of bounds
        for (int i=0; i<airborneNucleons.size(); i++){
            INucleon nucleon = airborneNucleons.get(i);
            if (isOutOfBoundsCheck(nucleon)){
                removeNucleon(nucleon);
                i--;
            }
        }
    }

    private void removeNucleon(INucleon nucleon){
        airborneNucleons.remove(nucleon);
    }

    //TODO: add difficulty multiplier which alters how often the gun shoots and how fast the nucleons fly

    private void checkWinGame() {
        if (molecule.isFull()) {
                gameWon = true;
                currentState = GameState.PAUSEDWIN;
        } else if (gun.isEmpty() && airborneNucleons.isEmpty()) {
            loseGame();
        }
    }

    private void loseGame(){
        gameLost = true;
        currentState = GameState.PAUSEDLOSE;
    }

    public void collisionCheck(){
        INucleon collidingNucleon = null;

        for (IGluonPoint gluon : gluons) {
            for (INucleon nucleon : airborneNucleons){
                if (CollisionHandler.collision(gluon, nucleon)) {

                    if (nucleon.getClass().equals(Proton.class)) {
                        if (gluon.getProtonsNeeded() > 0){
                            gluon.addProton();
                            collidingNucleon = nucleon;
                            checkWinGame();
                        } else {
                            loseGame();
                        }
                    }


                    else {
                        if (gluon.getNeutronsNeeded() > 0){
                            gluon.addNeutron();
                            collidingNucleon = nucleon;
                            checkWinGame();
                        } else {
                            loseGame();
                        }
                    }
                }
            }
            if (collidingNucleon != null) {
                removeNucleon(collidingNucleon);
            }


        }
        if (collidingNucleon != null){
            removeNucleon(collidingNucleon);
        }
    }

    public void pause(){
        currentState = GameState.PAUSED;
        gamePaused = true;
    }

    public void resume(){
        currentState = GameState.RUNNING;
        gamePaused = false;
    }

    public void update(float delta){
        if(currentState==GameState.RUNNING) {
            checkWinGame();
            runTime += delta;
            collisionCheck();
            if (runTime - lastUpdateTime >= dummyUpdateVariable && !gun.isEmpty()) {
                lastUpdateTime = runTime;
                airborneNucleons.add(gun.shoot());
            }
            for (INucleon nucleon : airborneNucleons) {
                nucleon.update(delta);
            }
            removeOutOfBoundsNucleons();
        }

    }
}