package com.example.tictactoe;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.os.CountDownTimer;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private boolean flag1Player = false;
    private boolean flag2Player = false;
    private int click = 0;
    private String player1Color, player2Color;
    String color;
    MediaPlayer sound;
    Button blue,orange,red,green, yellow,purple;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        blue = findViewById(R.id.blue); blue.setOnClickListener(this);
        orange = findViewById(R.id.orange); orange.setOnClickListener(this);
        red = findViewById(R.id.red); red.setOnClickListener(this);
        green = findViewById(R.id.green); green.setOnClickListener(this);
        yellow = findViewById(R.id.yellow); yellow.setOnClickListener(this);
        purple = findViewById(R.id.purple); purple.setOnClickListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        sound = MediaPlayer.create(MainActivity.this, R.raw.odaberite_dvije_boje);
        sound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                sound.release();
            }
        });
        sound.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        sound.stop();
    }

    @Override
    public void onClick(View view) {
        Button b = (Button)view;
        switch (b.getId()){
            case R.id.blue:
                color = "plavi";
                sound = MediaPlayer.create(MainActivity.this, R.raw.plava);
                break;
            case R.id.orange:
                color = "narančasti";
                sound = MediaPlayer.create(MainActivity.this, R.raw.narancasta);
                break;
            case R.id.red:
                color = "crveni";
                sound = MediaPlayer.create(MainActivity.this, R.raw.crvena);
                break;
            case R.id.green:
                color = "zeleni";
                sound = MediaPlayer.create(MainActivity.this, R.raw.zelena);
                break;
            case R.id.yellow:
                color = "žuti";
                sound = MediaPlayer.create(MainActivity.this, R.raw.zuta);
                break;
            case R.id.purple:
                color = "ružičasti";
                sound = MediaPlayer.create(MainActivity.this, R.raw.ruzicasta);
                break;
        }

        sound.start();
        view.setAlpha(0.5F);
        view.setEnabled(false);
        click++;
        checkPlayer(color);
    }

    private void checkPlayer(String color){
        if(click == 1){
            flag1Player = true;
            player1Color = color;
        }else{
            flag2Player = true;
            player2Color = color;
        }

        if(flag1Player && flag2Player) {
            blue.setClickable(false);
            orange.setClickable(false);
            red.setClickable(false);
            green.setClickable(false);
            yellow.setClickable(false);
            purple.setClickable(false);

            sound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    Intent intent = new Intent(MainActivity.this, StartActivity.class);
                    intent.putExtra("Player1", player1Color);
                    intent.putExtra("Player2", player2Color);
                    startActivity(intent);
                    System.gc();
                    finish();
                }
            });

        }

    }


}