package com.kitowcy.tests;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Timer;

public class HelloGame extends ApplicationAdapter {

    public static final String TAG = HelloGame.class.getSimpleName();

    private SpriteBatch batch;
    private TextureAtlas textureAtlas;
    private Sprite sprite;
    private int currentFrame = 1;
    private String currentAtlasKey = "001";

    @Override
    public void create() {
        Gdx.app.log(TAG, "create()");
        batch = new SpriteBatch();
        textureAtlas = new TextureAtlas(Gdx.files.internal("frames/spritesheet.atlas"));
        TextureAtlas.AtlasRegion region = textureAtlas.findRegion("001");
        sprite = new Sprite(region);
        sprite.setPosition(120, 100);
        sprite.scale(2.5f);
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                currentFrame++;
                if (currentFrame > 7)
                    currentFrame = 1;

                // ATTENTION! String.format() doesnt work under GWT for god knows why...
                currentAtlasKey = String.format("%03d", currentFrame);
                sprite.setRegion(textureAtlas.findRegion(currentAtlasKey));
            }
        }, 0, 1 / 30.0f);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        sprite.draw(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        textureAtlas.dispose();
    }
}
