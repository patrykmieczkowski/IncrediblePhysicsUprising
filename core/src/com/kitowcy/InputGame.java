package com.kitowcy;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Random;

public class InputGame extends ApplicationAdapter implements InputProcessor {

    public static class Bitch {
        public SpriteBatch batch;
        public Sprite sprite;

        public Bitch(SpriteBatch batch, Sprite sprite) {
            this.batch = batch;
            this.sprite = sprite;
        }
    }

    float w;
    float h;
    int lastCatX, lastCatY;
    int currentOffsetX = 0, currentOffsetY = 0;
    public static final String TAG = InputGame.class.getSimpleName();
    private SpriteBatch batch;
    //    private SpriteBatch backgroundBatch;
    private Texture texture;
    // private Texture backgroundTexture;
    private Sprite sprite;
    //    private Sprite backgroundSprite;
    ArrayList<Bitch> buckets;
    ArrayList<Bitch> markers;
    int selectedScreen;
    Texture bucketTexture;
    Texture markerTexture;
    Finishable finishable;
    long startTime;

    public InputGame(Finishable finishable) {
        super();
        this.finishable = finishable;
//        Gdx.app.log(TAG, "selected screen = " + screen);
//        selectedScreen = screen;
//        Gdx.app.log(TAG, "selected screen: " + String.valueOf(screen));
    }

    private SpriteBatch textBatch;
    private BitmapFont font;

    @Override
    public void create() {
        Gdx.app.log(TAG, "create()");
        startTime = System.currentTimeMillis();
        textBatch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.RED);


        buckets = new ArrayList<Bitch>();
        markers = new ArrayList<Bitch>();
        bucketTexture = new Texture(Gdx.files.internal("delete.png"));
        markerTexture = new Texture(Gdx.files.internal("map_marker.png"));
        Random random = new Random();
        for (int j = 0; j < 5; j++) {
            SpriteBatch batch = new SpriteBatch();
            Sprite sprite1 = new Sprite(bucketTexture);
            sprite1.setPosition(random.nextInt(700), random.nextInt(400));
            buckets.add(new Bitch(batch, sprite1));
        }
        for (int j = 0; j < 5; j++) {
            SpriteBatch batch = new SpriteBatch();
            Sprite sprite1 = new Sprite(markerTexture);
            sprite1.setPosition(random.nextInt(700), random.nextInt(400));
            markers.add(new Bitch(batch, sprite1));
        }

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
//        backgroundBatch = new SpriteBatch();
        texture = new Texture(Gdx.files.internal("cat.png"));
//        backgroundTexture = new Texture(Gdx.files.internal("bigimage.jpg"));
//        backgroundSprite = new Sprite(backgroundTexture);
//        backgroundSprite.setPosition(0, 0);
        sprite = new Sprite(texture);

        int newCatX = (int) (w / 2 - sprite.getWidth() / 2);
        int newCatY = (int) (h / 2 - sprite.getHeight() / 2);
        lastCatX = newCatX;
        lastCatY = newCatY;
        lastCatX = (lastCatX - newCatX < 2.0) ? lastCatX : newCatX;
        lastCatY = (lastCatY - newCatY < 2.0) ? lastCatY : newCatY;

        sprite.setPosition(lastCatX, lastCatY);
    }

    FailedCallback failedCallback = new FailedCallback() {
        @Override
        public void onFail() {
            loser = true;
            if (finishable != null) finishable.finish(false, -1);
        }
    };
    ClearCallback clearCallback = new ClearCallback() {
        @Override
        public void onCleared() {
            winner = true;
            long timeElapsed = (System.currentTimeMillis() - startTime);
            if (finishable != null) finishable.finish(true, timeElapsed);
        }
    };
    boolean winner, loser;

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//        backgroundBatch.begin();
//        backgroundSprite.draw(backgroundBatch);
//        backgroundBatch.end();
        setupBuckets(failedCallback);
        setupMarkers(clearCallback);

        if (winner) {
            Gdx.app.log(TAG, "WINNER!!!");
            textBatch.begin();

            font.draw(textBatch, "Winner!!!", 200, 200);
            textBatch.end();
        }
        if (loser) {

            Gdx.app.log(TAG, "LOSER!!!");
            textBatch.begin();
            font.draw(textBatch, "Loser!!!", 200, 200);
            textBatch.end();
        }

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

        if (!loser && !winner)
            sprite.setPosition(lastCatX, lastCatY);

        sprite.draw(batch);
        batch.end();
    }

    private void setupBuckets(FailedCallback callback) {
        for (int j = 0; j < buckets.size(); j++) {
            Bitch b = buckets.get(j);
            float dx = Math.abs(b.sprite.getX() - lastCatX);
            float dy = Math.abs(b.sprite.getY() - lastCatY);
            if (dx < 20) {
                if (dy < 20) {
                    callback.onFail();
                } else {
                }
            } else {
            }
            b.batch.begin();
            b.sprite.draw(b.batch);
            b.batch.end();
        }
    }

    public interface FailedCallback {
        void onFail();
    }

    public interface ClearCallback {
        void onCleared();
    }

    private void setupMarkers(ClearCallback callback) {
        for (int j = 0; j < markers.size(); j++) {

            Bitch b = markers.get(j);
            float dx = Math.abs(b.sprite.getX() - lastCatX);
            float dy = Math.abs(b.sprite.getY() - lastCatY);
//            Gdx.app.log(TAG, "so close!! " + dx + "," + dy);
            if (dx < 20) {
                if (dy < 20) {
                    b.batch.begin();
                    b.sprite.setPosition(-10000, -10000);
                    b.batch.end();
                    b.batch.dispose();
                    markers.remove(j);
                    if (markers.size() == 0) callback.onCleared();
                } else {
                    b.batch.begin();
                    b.sprite.draw(b.batch);
                    b.batch.end();
                }
            } else {
                b.batch.begin();
                b.sprite.draw(b.batch);
                b.batch.end();
            }
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
        texture.dispose();
        for (Bitch b : buckets) {
            b.batch.dispose();
        }
        for (Bitch b : markers) {
            b.batch.dispose();
        }
        bucketTexture.dispose();
        markerTexture.dispose();

        textBatch.dispose();
        font.dispose();
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
