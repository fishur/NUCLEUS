package com.nucleus.controller;

import com.nucleus.model.level.ILevel;
import com.nucleus.model.collision.Vector;
import com.nucleus.views.libGDXGraphics.screens.PlayScreen;

public class InvertedPlayState implements ControllerState {

    private PlayScreen screen;
    Vector lastTouch = new Vector(0,0);

    public InvertedPlayState(PlayScreen screen) {
        this.screen = screen;
    }

    public void drag(int screenX, int screenY, int pointer){
        ILevel level = screen.getLevel();
        Vector newTouch = new Vector(screenX, screenY);
        newTouch.subtract(this.lastTouch);
        level.getMolecule().setRotation(newTouch, lastTouch);
        this.lastTouch = newTouch;
        return;
    }
}