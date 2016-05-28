package com.nucleus.AssetHandler;

import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;


public class TextLoader extends AsynchronousAssetLoader<Text, TextLoader.TextParameter> {

    public class TextParameter extends AssetLoaderParameters<Text> {
    }

    Text text;

    public TextLoader(FileHandleResolver resolver) {
        super(resolver);
    }

    public void loadAsync(AssetManager manager, String fileName, FileHandle file, TextParameter parameter) {
        this.text = new Text(file);
    }

    public Text loadSync(AssetManager manager, String fileName, FileHandle file, TextParameter parameter) {
        Text newText = this.text;
        this.text = null;
        return newText;
    }

    public Array<AssetDescriptor> getDependencies(String fileName, FileHandle file, TextParameter parameter) {
        return null;
    }
}