package com.kitowcy;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainGame extends ApplicationAdapter {

    public static final String TAG = MainGame.class.getSimpleName();

    SpriteBatch batch;
    Texture img;
    Sprite spirte;
    private Music marioMusic;
    @Override
    public void create() {
        Gdx.app.log(TAG, "create()");
        marioMusic = Gdx.audio.newMusic(Gdx.files.internal("overworld_bgm.mp3"));

        // start the playback of the background music immediately
        marioMusic.setLooping(true);
        marioMusic.play();

        batch = new SpriteBatch();
        img = new Texture("backein.jpg");
        spirte = new Sprite(img);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        batch.begin();
        spirte.draw(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        super.dispose();
        Gdx.app.log(TAG, "dispose()");
        batch.dispose();
        img.dispose();
    }
}
