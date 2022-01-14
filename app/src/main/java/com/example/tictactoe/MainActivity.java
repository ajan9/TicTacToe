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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button blue = findViewById(R.id.blue); blue.setOnClickListener(this);
        Button orange = findViewById(R.id.orange); orange.setOnClickListener(this);
        Button red = findViewById(R.id.red); red.setOnClickListener(this);
        Button green = findViewById(R.id.green); green.setOnClickListener(this);
        Button yellow = findViewById(R.id.yellow); yellow.setOnClickListener(this);
        Button purple = findViewById(R.id.purple); purple.setOnClickListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        sound = MediaPlayer.create(MainActivity.this, R.raw.odaberi_boje);
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
                break;
            case R.id.orange:
                color = "narančasti";
                break;
            case R.id.red:
                color = "crveni";
                break;
            case R.id.green:
                color = "zeleni";
                break;
            case R.id.yellow:
                color = "žuti";
                break;
            case R.id.purple:
                color = "ljubičasti";
                break;
        }

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
            Intent intent = new Intent(this, StartActivity.class);
            intent.putExtra("Player1", player1Color);
            intent.putExtra("Player2", player2Color);
            startActivity(intent);
            finish();
        }

    }


}