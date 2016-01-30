package com.kitowcy;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class AndroidLauncher extends AndroidApplication {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        config.useAccelerometer = true;
        config.useGyroscope = true;
        int screen = 0;

        screen = getIntent().getExtras().getInt(Config.BUNDLE_SCREEN);

        switch (screen) {
            case Config.PLAY:
                initialize(new InputGame(Config.PLAY), config);
                break;
            case Config.HIGHSCORES:
                initialize(new InputGame(Config.HIGHSCORES), config);
                break;
        }
    }
}
