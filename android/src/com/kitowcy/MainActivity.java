package com.kitowcy;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Context c = this;

        TextView playText = (TextView) findViewById(R.id.textStart);
        TextView highscoresText = (TextView) findViewById(R.id.textHighscores);

        playText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(c, AndroidLauncher.class);
                ((App)getApplication()).screen = 0;

//                intent.putExtra(Config.BUNDLE_SCREEN, Config.PLAY);
                startActivity(intent);
            }
        });

        highscoresText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(c, AndroidLauncher.class);
//                intent.putExtra(Config.BUNDLE_SCREEN, Config.HIGHSCORES);
                ((App) getApplication()).screen = 1;
                startActivity(intent);
            }
        });
    }

}
