package com.kitowcy.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Patryk Mieczkowski on 30.01.16
 */
public class StartScreen implements Screen {

    private SpriteBatch spriteBatch;
    private Texture startTexture;
    private Game myGame;

    public StartScreen(Game game) {
        myGame = game;
    }

    @Override
    public void show() {

        spriteBatch = new SpriteBatch();
        startTexture = new Texture("backein.jpg");

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spriteBatch.begin();
        spriteBatch.draw(startTexture, 0, 0);
        spriteBatch.end();

        if (Gdx.input.justTouched()){
            myGame.setScreen(new AccelerometerScreen());
        }

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
