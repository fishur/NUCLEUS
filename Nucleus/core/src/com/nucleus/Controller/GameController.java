package com.nucleus.Controller;

import com.nucleus.Model.ILevel;

/**
 * Created by erik on 06/05/16.
 */
public class GameController implements ControllerState{

    ILevel level;

    public GameController(ILevel level){
        this.level = level;
    }

    public void touch(int screenX, int screenY, int pointer, int button){
        return;
    }

    public void drag(int screenX, int screenY, int pointer){
        return;
    }
}