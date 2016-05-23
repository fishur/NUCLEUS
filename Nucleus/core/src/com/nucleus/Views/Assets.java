package com.nucleus.Views;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import java.util.HashMap;
import java.util.Map;

public class Assets {
    public static AssetManager audioAssets = new AssetManager();
    public static AssetManager textureAssets = new AssetManager();

    private static Map<String, String> audio = new HashMap<String, String>();
    private static Map<String, String> texture = new HashMap<String, String>();


    public static void loadMusicFiles() {
        FileHandle musicPath;

        if (Gdx.app.getType() == Application.ApplicationType.Android) {
            musicPath = Gdx.files.internal("assets/music");
        } else {
            musicPath = Gdx.files.internal("music/");
        }
        for (FileHandle entry : musicPath.list()) {
            audio.put(entry.path(), entry.name());
            System.out.println("path == " +entry.path() + "\n" + " name = " + entry.name());

        }
        loadMusic();
        finishLoading();

        System.out.println(audioAssets.getLoadedAssets());
    }

    public static void loadTextureFiles() {
        FileHandle texturePath;
        if (Gdx.app.getType() == Application.ApplicationType.Android) {
            texturePath = Gdx.files.internal("assets/graphics");
        } else {
            texturePath = Gdx.files.internal("graphics/");
        }
        for (FileHandle entry : texturePath.list()) {
            texture.put(entry.path(), entry.name());
            //System.out.println("Path ====== "+entry.path());
            //System.out.println("namn ==== " +entry.name());
        }
    }

    public static void loadMusic () {
        if (audio != null) {
            for (Map.Entry entry : audio.entrySet()) {
                audioAssets.load((String) entry.getKey(), Music.class);
                System.out.println("key ====== " +entry.getKey());
                System.out.println("namn på låt == " + entry.getValue());

            }
        }
    }

    public static void loadTexture() {
        if (texture != null) {
            for (Map.Entry entry : texture.entrySet()) {
                textureAssets.load((String) entry.getValue(), Skin.class);
                //audioAssets.finishLoading();

                //System.out.println("key ====== " +entry.getKey());
                //System.out.println("namn på grafisk skit == " + entry.getValue());
            }
        }
    }

    public static void getMusic (String str) {
        if (audio != null) {
            for (Map.Entry entry : audio.entrySet()) {
                if (entry.getKey().equals(str)) {
                    audioAssets.load((String) entry.getValue(), Music.class);
                }
            }
        }
    }

    public static Music playMusic(String song) {
        String songFile = audio.get(song);
        if (songFile != null) {
            Music music = audioAssets.get(song,Music.class);
            if (music != null) {
                return music;
            }
        }
        return null;
    }

    public static Music stopMusic(String song) {
        String songFile = audio.get(song);

        if (songFile != null) {
            Music music = audioAssets.get(song,Music.class);
            return music;
        }
    return null;
    }

    public static Music playSound(String sound) {
        String soundFile = audio.get(sound);
        if (sound != null) {
            Music soundEffect = audioAssets.get(sound,Music.class);
            return soundEffect;
        }
        return null;
    }

    public static Music pauseMusic(String song) {
        String songFile = audio.get(song);
        if(songFile != null){
            Music music = audioAssets.get(song,Music.class);
            return music;
        }
        return null;
    }

    public static Music resumeMusic (String song) {
        
    }


    public static void finishLoading(){
        while(!(audioAssets.update()));
    }

}
