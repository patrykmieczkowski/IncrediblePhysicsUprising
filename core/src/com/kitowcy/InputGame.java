package com.kitowcy;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;

public class InputGame extends ApplicationAdapter implements InputProcessor {

    public static final String TAG = InputGame.class.getSimpleName();
    private SpriteBatch batch;
    private Texture texture;
    private Sprite sprite;

    @Override
    public void create() {
        Gdx.app.log(TAG, "create()");
        GestureDetector gd = new GestureDetector(new GestureDetector.GestureListener() {
            @Override
            public boolean touchDown(float x, float y, int pointer, int button) {
                return false;
            }

            @Override
            public boolean tap(float x, float y, int count, int button) {
                return false;
            }

            @Override
            public boolean longPress(float x, float y) {
                return false;
            }

            @Override
            public boolean fling(float velocityX, float velocityY, int button) {
                return false;
            }

            @Override
            public boolean pan(float x, float y, float deltaX, float deltaY) {
                return false;
            }

            @Override
            public boolean panStop(float x, float y, int pointer, int button) {
                return false;
            }

            @Override
            public boolean zoom(float initialDistance, float distance) {
                return false;
            }

            @Override
            public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
                return false;
            }
        });
        Gdx.input.setInputProcessor(this);
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        batch = new SpriteBatch();

        texture = new Texture(Gdx.files.internal("jet.png"));
        sprite = new Sprite(texture);
        sprite.setPosition(w / 2 - sprite.getWidth() / 2, h / 2 - sprite.getHeight() / 2);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

//        if (Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)) {
//            Gdx.app.log(TAG, "pressed");
//
//            if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT))
//                sprite.translateX(-1f);
//            else
//                sprite.translateX(-10.0f);
//        }
//        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
//            if(Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT))
//                sprite.translateX(1f);
//            else
//                sprite.translateX(10.0f);
//        }
        batch.begin();
        sprite.draw(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        texture.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        Gdx.app.log(TAG, "keyDown " + keycode);

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        Gdx.app.log(TAG, "keyUp " + keycode);
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        Gdx.app.log(TAG, "keyTyped " + character);
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Gdx.app.log(TAG, "touchDown");
        sprite.translateX(-15f);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        Gdx.app.log(TAG, "touchDown");
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        Gdx.app.log(TAG, "touchDragged");
        sprite.translateX(3f);
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        Gdx.app.log(TAG, "mouseMoved " + screenX + "," + screenY);

        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        Gdx.app.log(TAG, "scrolled " + amount);
        return false;
    }
}
