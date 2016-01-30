package com.kitowcy;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
                ((App) getApplication()).screen = 0;

//                intent.putExtra(Config.BUNDLE_SCREEN, Config.PLAY);
                startActivityForResult(intent, 0);
            }
        });

        highscoresText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(c, AndroidLauncher.class);
//                intent.putExtra(Config.BUNDLE_SCREEN, Config.HIGHSCORES);
                ((App) getApplication()).screen = 1;
                startActivityForResult(intent, 0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        final long score = ((App)getApplication()).currentScore;
        switch (resultCode) {
            case 200: {
                final Dialog d = new Dialog(MainActivity.this);
                d.setContentView(R.layout.enter_high_score);
                final EditText ed = (EditText) d.findViewById(R.id.editText);
                d.findViewById(R.id.ok).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String newHighScore = ed.getText().toString();
                        if (newHighScore.length() == 0)
                            Toast.makeText(MainActivity.this,
                                    "Enter valid name!", Toast.LENGTH_SHORT).show();
                        else {
                            Toast.makeText(MainActivity.this,
                                    "Score" + score + " saved!", Toast.LENGTH_SHORT).show();
                            d.dismiss();
                        }
                    }
                });
                d.show();
                break;
            }
            default:
                new AlertDialog.Builder(this).setTitle("You loose!!!")
                        .setPositiveButton("Fuck you", null).setNegativeButton("Ok, Exit",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        }).show();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
