package com.example.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class StartActivity extends AppCompatActivity implements View.OnClickListener {

    private final AppCompatActivity activity = StartActivity.this;

    private Button[][] buttons = new Button[3][3];

    private boolean player1Turn = true;

    private int roundCount;

    private int player1Points;
    private int player2Points;

    static int color1ID, color2ID;
    private String player1Color = "", player2Color = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        initListeners();

        Intent i  = getIntent();
        player1Color = (String) i.getSerializableExtra("Player1");
        player2Color = (String) i.getSerializableExtra("Player2");
        playerColor(player1Color, player2Color);

    }

    private void initListeners(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "button" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }

    }

    private void playerColor(String player1Color, String player2Color){
        switch (player1Color){
            case "blue":
                color1ID = R.drawable.blue_btn;
                break;
            case "orange":
                color1ID = R.drawable.orange_btn;
                break;
            case "red":
                color1ID = R.drawable.red_btn;
                break;
            case "green":
                color1ID = R.drawable.green_btn;
                break;
            case "yellow":
                color1ID = R.drawable.yellow_btn;
                break;
            case "purple":
                color1ID = R.drawable.purple_btn;
                break;
        }

        switch (player2Color){
            case "blue":
                color2ID = R.drawable.blue_btn;
                break;
            case "orange":
                color2ID = R.drawable.orange_btn;
                break;
            case "red":
                color2ID = R.drawable.red_btn;
                break;
            case "green":
                color2ID = R.drawable.green_btn;
                break;
            case "yellow":
                color2ID = R.drawable.yellow_btn;
                break;
            case "purple":
                color2ID = R.drawable.purple_btn;
                break;
        }

    }



    @Override
    public void onClick(View v) {
        if (!((Button) v).getText().toString().equals("")) {
            return;
        }

        if (player1Turn) {
            ((Button) v).setText("X");
            ((Button)v).setBackground(getDrawable(color1ID));
        } else {
            ((Button) v).setText("O");
            ((Button)v).setBackground(getDrawable(color2ID));
        }

        roundCount++;

        if (checkForWin()) {
            if (player1Turn) {
                player1Wins();
            } else {
                player2Wins();
            }
        } else if (roundCount == 9) {
            draw();
        } else {
            player1Turn = !player1Turn;
        }

    }

    private boolean checkForWin() {
        String[][] field = new String[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && !field[i][0].equals("")) {
                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")) {
                return true;
            }
        }

        if (field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals("")) {
            return true;
        }

        if (field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals("")) {
            return true;
        }

        return false;
    }

    private void player1Wins() {
        showWinnerColors(color1ID);
        player1Points++;
        Toast.makeText(this, "Player 1 wins!", Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    private void player2Wins() {
        showWinnerColors(color2ID);
        player2Points++;
        Toast.makeText(this, "Player 2 wins!", Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    private void draw() {
        Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();
        for(int i = 0; i < 3; i ++){
            for(int j = 0; j < 3; j++){
                buttons[i][j].setBackground(getDrawable(R.drawable.gray_btn));
            }
        }
        resetBoard();
    }

    private void showWinnerColors(int color){

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setBackground(getDrawable(color));
            }
        }

        new CountDownTimer(2000, 50) {
            @Override
            public void onTick(long arg0) {

            }

            @Override
            public void onFinish() {
                for(int i = 0; i < 3; i ++){
                    for(int j = 0; j < 3; j++){
                        buttons[i][j].setBackground(getDrawable(R.drawable.gray_btn));
                    }
                }
            }
        }.start();

    }


    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }

        roundCount = 0;
        player1Turn = true;
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("roundCount", roundCount);
        outState.putInt("player1Points", player1Points);
        outState.putInt("player2Points", player2Points);
        outState.putBoolean("player1Turn", player1Turn);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        roundCount = savedInstanceState.getInt("roundCount");
        player1Points = savedInstanceState.getInt("player1Points");
        player2Points = savedInstanceState.getInt("player2Points");
        player1Turn = savedInstanceState.getBoolean("player1Turn");
    }
}
