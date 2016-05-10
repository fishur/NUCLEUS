package com.nucleus.Model;

import java.util.ArrayList;
import java.util.List;

public class Level implements ILevel {
    private int width;
    private int height;

    private float runTime = 0;
    private float lastUpdateTime = 0;
    private float dummyUpdateVariable = 1;

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
        return x - nucleon.getRadius()>=width || x + nucleon.getRadius()<=0 ||
                y - nucleon.getRadius()>=height || y + nucleon.getRadius()<=0;

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

    private void removeNucleon(INucleon nuc){
        airborneNucleons.remove(nuc);
    }

    //TODO: add difficulty multiplier which alters how often the gun shoots and how fast the nucleons fly
    //TODO: add a collision check that also evaluates which gluon point to fill
    //TODO: add a win check (molecule.isFull()) and after, a loss check (airborneNucleons is empty and gun is empty)


    private void winGame() {
        System.out.println("YOU HAVE WON YAY!!");
    }

    private void loseGame(){
        System.out.println("You lost :(((");
    }

    public void update(float delta){

        INucleon rem = null;
        int protons = 0;
        int nucleons = 0;

        runTime += delta;
        for (IGluonPoint gluon : gluons) {
            for (INucleon nucleon : airborneNucleons){
                if (CollisionHandler.collision(gluon, nucleon)) {
                        if (nucleon.getClass().equals(Proton.class)) {
                            gluon.addProton();
                            nucleon.setVelocity(0,0);
                            rem = nucleon;
                            System.out.println(protons + " protons out of " + gluon.getProtonsNeeded() );

                        } else {
                            gluon.addNeutron();
                            nucleon.setVelocity(0,0);
                            rem = nucleon;
                            System.out.println(protons + " neutrons out of " + gluon.getNeutronsNeeded() );


                        }
                }
            }
        }
        if (rem != null){
            removeNucleon(rem);
        }

        if(runTime - lastUpdateTime >= dummyUpdateVariable && !gun.isEmpty()) {
            lastUpdateTime = runTime;
            airborneNucleons.add(gun.shoot());
        }
        for(INucleon nucleon : airborneNucleons){
            nucleon.update(delta);
        }

        if (molecule.isFull()){
            winGame();
        }

        else if (gun.isEmpty()){
            loseGame();
        }

        removeOutOfBoundsNucleons();
    }


}