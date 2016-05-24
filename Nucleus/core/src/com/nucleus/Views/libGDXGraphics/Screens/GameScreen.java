package com.nucleus.Views.libGDXGraphics.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.nucleus.Model.ILevel;
import com.nucleus.Views.libGDXGraphics.Viewables.IViewable;
import com.nucleus.Views.libGDXGraphics.Viewables.BackgroundViewable;
import com.nucleus.Views.libGDXGraphics.Viewables.NucleonViewable;
import com.nucleus.Views.libGDXGraphics.Viewables.CountdownViewable;
import com.nucleus.Views.libGDXGraphics.Viewables.MoleculeViewable;

import com.nucleus.Views.NMusicPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;


/**
 * Created by erik on 25/04/16.
 */
public class GameScreen extends Observable implements Screen {

    private ILevel level;
    private boolean winLoseScreenShow = false;
    private NMusicPlayer mc;
    private WinDialog winDialog;
    private LoseDialog loseDialog;
    private PauseDialog pauseDialog;

    private List<IViewable> views = new ArrayList<IViewable>();
    private OrthographicCamera cam;
    private static SpriteBatch batch;
    private boolean pauseDialogIsShowing = false;

    public GameScreen(int levelNumber, ILevel level){

        this.level = level;
        this.cam = new OrthographicCamera(1080, 1920);
        cam.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        //Adding views
        views.add(new BackgroundViewable());
        views.add(new CountdownViewable(level.getNucleonGun()));
        views.add(new NucleonViewable(level.getAirborneNucleons()));
        views.add(new MoleculeViewable(levelNumber, level.getMolecule()));

        batch = new SpriteBatch();
        batch.setProjectionMatrix(cam.combined);

    }


    @Override
    public void render(float delta) {

        if (level.isGameLost()) {
            if (winLoseScreenShow == false) {
                this.loseDialog = new LoseDialog(batch, level);
                loseDialog.show();
                winLoseScreenShow = true;
            }
            loseDialog.render(delta);

        } else if (level.isGameWon()) {
            if (winLoseScreenShow == false) {
                this.winDialog = new WinDialog(batch, level);
                winDialog.show();
                winLoseScreenShow = true;
            }
            winDialog.render(delta);

        } else if (level.isGamePaused()) {
            if (!pauseDialogIsShowing) {
                pause();
            }
            pauseDialog.render(delta);
            level.update(delta);

        } else {

            pauseDialogIsShowing = false;

            level.update(delta);

            Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            for (IViewable view : views) {
                view.render(batch);
            }
        }

    }

    @Override
    public void resize(int width, int height){
        Gdx.app.log("GameScreen", "resizing");
    }

    @Override
    public void show(){
        Gdx.app.log("GameScreen", "showing");

    }

    @Override
    public void hide(){
        Gdx.app.log("GameScreen", "hide called");
    }

    @Override
    public void pause(){

        Gdx.app.log("GameScreen", "pause called");
        level.pause();
        pauseDialog = new PauseDialog(batch, level);
        pauseDialog.show();
        pauseDialogIsShowing = true;

    }

    @Override
    public void resume(){
        Gdx.app.log("GameScreen", "resume called");

    }

    @Override
    public void dispose() {
        // Leave blank
    }

}