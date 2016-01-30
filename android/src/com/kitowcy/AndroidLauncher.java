package com.kitowcy;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.kitowcy.tutorialowe.CameraGame;

public class AndroidLauncher extends AndroidApplication {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        config.useAccelerometer = true;
        config.useGyroscope = true;
        int screen = ((App) getApplication()).screen;

//        Gdx.app.log("APP", "screen selected = " + screen);

        if (screen == 0)
            initialize(new InputGame(screen), config);
        else initialize(new CameraGame(), config);

//
//        screen = getIntent().getExtras().getInt(Config.BUNDLE_SCREEN);

//        switch (screen) {
//            case Config.PLAY:
//                initialize(new InputGame(Config.PLAY), config);
//                break;
//            case Config.HIGHSCORES:
//            default:
//                initialize(new InputGame(Config.HIGHSCORES), config);
//                break;
//        }
    }
}
