package com.example.tictactoe;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;


public class StartActivity extends AppCompatActivity implements View.OnClickListener {

    private Button[][] buttons = new Button[3][3];
    private boolean player1Turn = true;
    private int roundCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        initListeners();
    }

    public void initListeners(){

        for (int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                String buttonID = "button" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }
    }

    @Override
    public void onClick(View v) {
        if(!((Button)v).getText().toString().equals("")){
            return;
        }

        if(player1Turn){
            ((Button)v).setBackground(getDrawable(R.drawable.blue_btn));
            ((Button)v).setText("X");
        }else{
            ((Button)v).setBackground(getDrawable(R.drawable.orange_btn));
            ((Button)v).setText("O");
        }

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
        String[][] array = new String[3][3];

        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                array[i][j] = buttons[i][j].getText().toString();
            }
        }

        for(int i = 0; i < 3; i++){
            if(array[i][0].equals(array[i][1]) && array[i][0].equals(array[i][2])
                    && !array[i][0].equals("")){
                return true;
            }
        }
        for(int i = 0; i < 3; i++){
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
        Toast.makeText(this,"Player 1 wins!", Toast.LENGTH_LONG).show();
        resetBoard();
    }
    private void player2Wins(){
        Toast.makeText(this,"Player 2 wins!", Toast.LENGTH_LONG).show();
        resetBoard();
    }

    private void draw(){
        Toast.makeText(this,"Draw!", Toast.LENGTH_SHORT).show();
        resetBoard();
    }


    private void resetBoard(){
        for(int i = 0; i < 3; i ++){
            for(int j = 0; j < 3; j++){
                buttons[i][j].setText("");
                buttons[i][j].setBackground(getDrawable(R.drawable.gray_btn));
            }
        }
        player1Turn = true;
    }


}