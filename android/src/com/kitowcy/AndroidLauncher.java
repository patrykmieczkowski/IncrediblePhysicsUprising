package com.kitowcy;

import android.os.Bundle;
import android.os.Looper;

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
            initialize(new InputGame(new Finishable() {
                @Override
                public void finish(final boolean result, final long score) {
                    new android.os.Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            AndroidLauncher.this.setResult(200);
                            AndroidLauncher.this.getIntent().putExtra("SCORE", score);
//                            Intent intent = getIntent();
//                            intent.putExtra("SCORE", score);
                            ((App)getApplication()).currentScore=score;
                            AndroidLauncher.this.exit();
                        }
                    }, 2000);
                }
            }), config);
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
