package com.nucleus.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.nucleus.Model.IProgressTracker;
import com.nucleus.Model.Level;
import com.nucleus.NucleusGame;
import com.nucleus.Utils.LevelUtils.LevelBuilder;
import com.nucleus.Utils.ProgressTracker;

/**
 * Created by Quaxi on 10/05/16.
 */
public class GameController extends ClickListener {

    private NucleusGame game;
    private NInputHandler controller;
    private Level level;
    private IProgressTracker progressTracker;

    public GameController() {
        game = new NucleusGame();
        controller = new NInputHandler();
        progressTracker = new ProgressTracker();
    }

    public void startLevelChooser() {
        game.goToLevelChooser();
    }

    private void startLevel(int levelNum) {
        this.level = LevelBuilder.buildLevel(levelNum, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        Gdx.input.setInputProcessor(controller);
        Gdx.input.setInputProcessor(new NInputHandler((Level) level));
        game.goToLevel(levelNum, (Level) level);

    }


    public void touch(int screenX, int screenY, int pointer, int button){
        if ((screenX > level.getWidth() - 20) && screenY < 20) { // if you touch the upper right corner you pause the game
            level.pause();
        }
    }

    public void exit() {
        game.exit();
    }

    public void goToStartScreen(){
        game.goToStartScreen();
    }

    public void setInput(){

    }

    /**
     * Listens for inputs from buttons
     * @param event the evet that happend
     * @param x coordinate for the touch
     * @param y coordinate for the touch
     */
    public void clicked(InputEvent event, float x, float y) {

        String label = event.getTarget().toString();

        if (label.equals("Label: Play")) {
            startLevelChooser();
        }

        else if (label.equals("Label: Options")) {
            game.goToOptions();
        }

        else if (label.equals("Label: Toggle Sound")){
            Gdx.app.log(event.getTarget().toString(), "HEH");
        }

        else if (label.equals("Label: Exit")) {
            exit();
        }

        else if (label.equals("Label: Level 1")) {
            if(progressTracker.checkLevelPermission(1))
                startLevel(1);
        }

        else if (label.equals("Label: Level 2")) {
            if(progressTracker.checkLevelPermission(2))
                startLevel(2);
        }

        else if (label.equals("Label: Play Again")) {
            game.goToStartScreen();
        }

        else if (label.equals("Label: Main Menu")) {
            game.goToStartScreen();
        }

    }
}
