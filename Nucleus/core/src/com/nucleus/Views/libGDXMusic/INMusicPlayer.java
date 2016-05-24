package com.nucleus.Views.libGDXMusic;

/**
 * Created by paki on 5/24/16.
 */
public interface INMusicPlayer {
    void loadMusic();
    void playMusic(String song);
    void stopMusic(String song);
    void playSound(String sound);
    void switchSong(String newSong);
    void pauseMusic(String song);
    void resumeMusic(String song);
    void setMasterVolume(float volume);
}