package com.example.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class StartActivity extends AppCompatActivity implements View.OnClickListener {

    int MAX_LENGTH = 3;
    private Button[][] buttons = new Button[MAX_LENGTH][MAX_LENGTH];
    private boolean player1Turn = true;
    private int roundCount = 0;

    static int color1ID, color2ID;

    private String player1Color = "", player2Color = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        initListeners();
    }

    public void initListeners(){

        for (int i = 0; i < MAX_LENGTH; i++){
            for(int j = 0; j < MAX_LENGTH; j++){
                String buttonID = "button" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }
        Intent i  = getIntent();
        player1Color = (String) i.getSerializableExtra("Player1");
        player2Color = (String) i.getSerializableExtra("Player2");
        playerColor(player1Color, player2Color);

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

        playerColor(player1Color, player2Color);

        if(!((Button)v).getText().toString().equals("")){
            return;
        }

        if(player1Turn){
            ((Button)v).setBackground(getDrawable(color1ID));
            ((Button)v).setText("X");
        }else{
            ((Button)v).setBackground(getDrawable(color2ID));
            ((Button)v).setText("O");
        }

        roundCount++;

        if(checkForWin()){
            if(player1Turn){
                player1Wins();
            }else{
                player2Wins();
            }
        }else if(roundCount == 9){
            draw();
        }else{
            player1Turn = !player1Turn;
        }

    }

    private boolean checkForWin(){
        String[][] array = new String[MAX_LENGTH][MAX_LENGTH];

        for(int i = 0; i < MAX_LENGTH; i++){
            for(int j = 0; j < MAX_LENGTH; j++){
                array[i][j] = buttons[i][j].getText().toString();
            }
        }

        for(int i = 0; i < MAX_LENGTH; i++){
            if(array[i][0].equals(array[i][1]) && array[i][0].equals(array[i][2])
                    && !array[i][0].equals("")){
                return true;
            }
        }
        for(int i = 0; i < MAX_LENGTH; i++){
            if(array[0][i].equals(array[1][i]) && array[0][i].equals(array[2][i])
                    && !array[i][0].equals("")){
                return true;
            }
        }

        if(array[0][0].equals(array[1][1]) && array[0][0].equals(array[2][2])
                && !array[0][0].equals("")){
            return true;
        }

        if(array[0][2].equals(array[1][1]) && array[0][2].equals(array[2][0])
                && !array[0][2].equals("")){
            return true;
        }

        return false;
    }

    private void player1Wins(){
        showWinnerColors(color1ID);
        Toast.makeText(this,"Player 1 wins!", Toast.LENGTH_LONG).show();
        resetBoard();
    }

    private void player2Wins(){
        showWinnerColors(color2ID);
        Toast.makeText(this,"Player 2 wins!", Toast.LENGTH_LONG).show();
        resetBoard();
    }

    private void draw(){
        Toast.makeText(this,"Draw!", Toast.LENGTH_SHORT).show();
        for(int i = 0; i < 3; i ++){
            for(int j = 0; j < 3; j++){
                buttons[i][j].setBackground(getDrawable(R.drawable.gray_btn));
            }
        }
        resetBoard();
    }

    private void showWinnerColors(int color){

        for (int i = 0; i < MAX_LENGTH; i++) {
            for (int j = 0; j < MAX_LENGTH; j++) {
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

    private void resetBoard(){
        for(int i = 0; i < 3; i ++){
            for(int j = 0; j < 3; j++){
                buttons[i][j].setText("");
            }
        }
        roundCount = 0;
        player1Turn = !player1Turn;
    }


}