package com.nucleus.Views.libGDXSounds;


public interface INMusicPlayerAdapter {
    void playSongLoop(String song, float volume);
    void playSoundEffect(String song);
    void changeSong(String newSong, String oldSong, float volume);
    void pauseSong (String song);
    void resumeSong(String song);
    void stopSong(String song);
}