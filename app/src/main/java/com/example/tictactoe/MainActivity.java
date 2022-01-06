package com.example.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.os.CountDownTimer;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final AppCompatActivity activity = MainActivity.this;

    private boolean flag1Player = false;
    private boolean flag2Player = false;
    private static int click = 0;
    private String player1Color, player2Color;
    String color;

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
    public void onClick(View view) {

        Button b = (Button)view;
        switch (b.getId()){
            case R.id.blue:
                color = "blue";
                break;
            case R.id.orange:
                color = "orange";
                break;
            case R.id.red:
                color = "red";
                break;
            case R.id.green:
                color = "green";
                break;
            case R.id.yellow:
                color = "yellow";
                break;
            case R.id.purple:
                color = "purple";
                break;
        }

        view.setBackground(getDrawable(R.drawable.gray_btn));
        view.getBackground().setAlpha(64);
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