package com.example.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.os.CountDownTimer;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity{

    private final AppCompatActivity activity = MainActivity.this;

    private boolean flag1Player = false;
    private boolean flag2Player = false;
    private static int click = 0;
    private static String color = "";
    private String player1Color = "", player2Color = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button blue = findViewById(R.id.blue);
        Button orange = findViewById(R.id.orange);
        Button red = findViewById(R.id.red);
        Button green = findViewById(R.id.green);
        Button yellow = findViewById(R.id.yellow);
        Button purple = findViewById(R.id.purple);


        blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.getBackground().setAlpha(64);
                view.setEnabled(false);
                click++;
                color = "blue";
                checkPlayer(color);
            }
        });
        orange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.getBackground().setAlpha(64);
                view.setEnabled(false);
                color = "orange";
                click++;
                checkPlayer(color);
            }
        });
        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.getBackground().setAlpha(64);
                view.setEnabled(false);
                color = "red";
                click++;
                checkPlayer(color);

            }
        });
        green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.getBackground().setAlpha(64);
                view.setEnabled(false);
                click++;
                color = "green";
                checkPlayer(color);
            }
        });
        yellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.getBackground().setAlpha(64);
                view.setEnabled(false);
                click++;
                color = "yellow";
                checkPlayer(color);
            }
        });
        purple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.getBackground().setAlpha(64);
                view.setEnabled(false);
                click++;
                color = "purple";
                checkPlayer(color);
            }
        });

    }

    private void checkPlayer(String color){
        if(click == 1){
            flag1Player = true;
            player1Color = color;
        }else{
            flag2Player = true;
            player2Color = color;
        }

        if(flag1Player && flag2Player){
            Intent i = new Intent(activity, StartActivity.class);
            startActivity(i);

            Intent i2 = new Intent(this, StartActivity.class);
            i2.putExtra("Player1", player1Color);
            i2.putExtra("Player2", player2Color);
            startActivity(i2);
            finish();
        }

    }





}


