package com.kitowcy;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;

public class InputGame extends ApplicationAdapter implements InputProcessor {
    float w;
    float h;
    int lastCatX, lastCatY;
    int currentOffsetX = 0, currentOffsetY = 0;
    public static final String TAG = InputGame.class.getSimpleName();
    private SpriteBatch batch;
    private SpriteBatch backgroundBatch;
    private Texture texture;
    private Texture backgroundTexture;
    private Sprite sprite;
    private Sprite backgroundSprite;

    int selectedScreen;

    public InputGame(int screen ) {
        super();
//        Gdx.app.log(TAG, "selected screen = " + screen);

//        selectedScreen = screen;
//        Gdx.app.log(TAG, "selected screen: " + String.valueOf(screen));
    }

    @Override
    public void create() {
        Gdx.app.log(TAG, "create()");

        InputMultiplexer im = new InputMultiplexer();
        GestureDetector gd = new GestureDetector(new GestureDetector.GestureListener() {
            @Override
            public boolean touchDown(float x, float y, int pointer, int button) {
                Gdx.app.log(TAG, "touchDown " + pointer);
                currentOffsetX = 0;
                currentOffsetY = 0;
                return false;
            }

            @Override
            public boolean tap(float x, float y, int count, int button) {
                Gdx.app.log(TAG, "tap " + count + " times");
                return false;
            }

            @Override
            public boolean longPress(float x, float y) {
                Gdx.app.log(TAG, "longPress");
                return false;
            }

            @Override
            public boolean fling(float velocityX, float velocityY, int button) {
                Gdx.app.log(TAG, "fling " + velocityX + "," + velocityY);
                currentOffsetX = 100;
                return false;
            }

            @Override
            public boolean pan(float x, float y, float deltaX, float deltaY) {
                Gdx.app.log(TAG, "pan");
                return false;
            }

            @Override
            public boolean panStop(float x, float y, int pointer, int button) {
                Gdx.app.log(TAG, "panStop");
                return false;
            }

            @Override
            public boolean zoom(float initialDistance, float distance) {
                Gdx.app.log(TAG, "zoom");
                return false;
            }

            @Override
            public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
                Gdx.app.log(TAG, "pinch");
                return false;
            }
        });
        im.addProcessor(gd);
        im.addProcessor(this);
        Gdx.input.setInputProcessor(im);
        w = Gdx.graphics.getWidth();
        h = Gdx.graphics.getHeight();
        batch = new SpriteBatch();
        backgroundBatch = new SpriteBatch();
        texture = new Texture(Gdx.files.internal("cat.png"));
        backgroundTexture = new Texture(Gdx.files.internal("bigimage.jpg"));
        backgroundSprite = new Sprite(backgroundTexture);
        backgroundSprite.setPosition(0, 0);
        sprite = new Sprite(texture);

        int newCatX = (int) (w / 2 - sprite.getWidth() / 2);
        int newCatY = (int) (h / 2 - sprite.getHeight() / 2);
        lastCatX = newCatX;
        lastCatY = newCatY;
        lastCatX = (lastCatX - newCatX < 2.0) ? lastCatX : newCatX;
        lastCatY = (lastCatY - newCatY < 2.0) ? lastCatY : newCatY;

        sprite.setPosition(lastCatX, lastCatY);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        backgroundBatch.begin();
        backgroundSprite.draw(backgroundBatch);
        backgroundBatch.end();

        batch.begin();
        float accelX = Gdx.input.getAccelerometerX();
        float accelY = Gdx.input.getAccelerometerY();
//        Gdx.app.log(TAG, "accelX = " + accelX);
//        Gdx.app.log(TAG, "accelY = " + accelY);
//        float accelZ = Gdx.input.getAccelerometerZ();
        int newCatX = (int) (w / 2 - sprite.getWidth() / 2 - accelY * 100);
        int newCatY = (int) (h / 2 - sprite.getHeight() / 2 + accelX * 100);
        lastCatX = (Math.abs(lastCatX - newCatX) < 10.07) ? lastCatX : newCatX;
        lastCatY = (Math.abs(lastCatY - newCatY) < 10.07) ? lastCatY : newCatY;

        sprite.setPosition(lastCatX, lastCatY);

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
