package com.kitowcy.tests;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class SceneDemo extends ApplicationAdapter {
    MyActor myActor;

    public class MyActor extends Actor {
        Texture texture = new Texture(Gdx.files.internal("cat2.png"));

        @Override
        public void draw(Batch batch, float alpha) {
            batch.draw(texture, 0, 0);
            batch.setColor(Color.YELLOW);
        }
    }

    private Stage stage;

    @Override
    public void create() {
        stage = new Stage();

//        ViewPort port =new ViewPort(Gdx.graphics.getWidth(),Gdx.graphics.getHeight(),Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
//        stage.setViewport(port);

        myActor = new MyActor();
        stage.addActor(myActor);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        float accelX = Gdx.input.getAccelerometerX() * 10;
        float accelY = Gdx.input.getAccelerometerY() * 100;


        stage.draw();
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
}
