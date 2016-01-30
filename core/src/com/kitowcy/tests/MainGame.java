package com.kitowcy.tests;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.awt.Rectangle;

public class MainGame extends ApplicationAdapter {

    public static final String TAG = MainGame.class.getSimpleName();
    private OrthographicCamera camera;
    SpriteBatch batch;
    Texture img;
    //    Sprite spirte;
    Rectangle rectangle;
    private Music marioMusic;

    void startMusic() {
        startMusic();
        marioMusic = Gdx.audio.newMusic(Gdx.files.internal("overworld_bgm.mp3"));

        // start the playback of the background music immediately
        marioMusic.setLooping(true);
        marioMusic.play();
    }

    @Override
    public void create() {
        Gdx.app.log(TAG, "create()");

        rectangle = new Rectangle(64, 64);
        rectangle.setLocation(100, 100);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        batch = new SpriteBatch();
        batch.setColor(Color.CYAN);
        img = new Texture("backein.jpg");
//        spirte = new Sprite(img);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (Gdx.input.isTouched()) {
            Vector2 touchPos = new Vector2();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY());
//            camera.unproject(touchPos);
            rectangle.x = (int) (touchPos.x - 64 / 2);
            rectangle.y = (int) (touchPos.y - 64 / 2);
        }

        batch.begin();
        batch.draw(img, rectangle.x, rectangle.y);
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
