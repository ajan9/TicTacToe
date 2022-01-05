package com.example.tictactoe;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class StartActivity extends AppCompatActivity implements View.OnClickListener {

    private Button[][] buttons = new Button[3][3];
    private boolean player1Turn = true;
    private int roundCount;
    private int player1Points;
    private int player2Points;

    private TextView player1;
    private TextView player2;
    private Button reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        initListeners();
    }

    public void initListeners(){

        player1 = findViewById(R.id.player1);
        player2 = findViewById(R.id.player2);
        reset = findViewById(R.id.reset);

        for (int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                String buttonID = "button" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetGame();
            }
        });

    }

    @Override
    public void onClick(View v) {

        // Ako je prazan
        if(!((Button)v).getText().toString().equals("")){
            return;
        }

        if(player1Turn){
            ((Button)v).setText("X");
        }else{
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
        player1Points++;
        Toast.makeText(this,"Player 1 wins!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }
    private void player2Wins(){
        player2Points++;
        Toast.makeText(this,"Player 2 wins!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }

    private void draw(){
        Toast.makeText(this,"Draw!", Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    private void updatePointsText(){
        player1.setText("Player 1: " + player1Points);
        player2.setText("Player 2: " + player2Points);
    }

    private void resetBoard(){
        for(int i = 0; i < 3; i ++){
            for(int j = 0; j < 3; j++){
                buttons[i][j].setText("");
            }
        }
        roundCount = 0;
        player1Turn = true;
    }

    public void resetGame(){
        player1Points = 0;
        player2Points = 0;
        updatePointsText();
        resetBoard();
    }

}
