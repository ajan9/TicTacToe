package com.example.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.os.CountDownTimer;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity{

    private final AppCompatActivity activity = MainActivity.this;

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
                Intent i = new Intent(activity, StartActivity.class);
                startActivity(i);

            }
        });
        orange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setBackground(getDrawable(R.drawable.gray_btn));
                view.setEnabled(false);
            }
        });
        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setBackground(getDrawable(R.drawable.gray_btn));
                new CountDownTimer(2000, 50) {

                    @Override
                    public void onTick(long arg0) {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void onFinish() {
                        view.setBackground(getDrawable(R.drawable.red_btn));
                    }
                }.start();


                view.setEnabled(false);
            }
        });
        green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setBackground(getDrawable(R.drawable.gray_btn));
                view.setEnabled(false);
            }
        });
        yellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setBackground(getDrawable(R.drawable.gray_btn));
                view.setEnabled(false);
            }
        });
        purple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setBackground(getDrawable(R.drawable.gray_btn));
                view.setEnabled(false);
            }
        });


    }



}


