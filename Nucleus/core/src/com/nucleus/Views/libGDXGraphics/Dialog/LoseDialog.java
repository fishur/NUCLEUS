package com.nucleus.Views.libGDXGraphics.Dialog;

import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.nucleus.AssetHandler.NAssetsData;
import com.nucleus.Views.libGDXMusic.INMusicPlayer;
import com.nucleus.Views.libGDXMusic.NMusicPlayer;

/**
 * Created by andreaserlandsson on 24/05/16.
 */
public class LoseDialog extends WinLoseDialog {

    private INMusicPlayer musicPlayer;

    public LoseDialog(EventListener listener) {
        super(listener);
        this.musicPlayer = NMusicPlayer.getInstance();
        musicPlayer.switchSong(NAssetsData.LOSESOUND);
        this.title = "You Lost";

    }

}