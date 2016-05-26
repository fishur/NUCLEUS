package com.nucleus.AssetHandler;

import com.badlogic.gdx.files.FileHandle;

/**
 * Created by paki on 5/25/16.
 */
public class Text {
    private String str;

    public Text (FileHandle fileHandle) {
        this.str = new String(fileHandle.readBytes());
    }

    @Override
    public String toString() {
        return this.str;
    }
}