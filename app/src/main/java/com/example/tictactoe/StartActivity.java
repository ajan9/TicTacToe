package com.example.tictactoe;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


public class StartActivity extends AppCompatActivity {

    private Button[] buttons;
    private Player player1, player2;

    View popup;

    MediaPlayer winnerSound, turnSound, newGameSound, winnerShortSound;
    AudioManager am;

    boolean startsFirst = true; // tko igra prvi: true za player1 i false za player2
    boolean gameDone = false;
    boolean gameActive = true;

    // Player representation
    // 0 - X
    // 1 - O
    int activePlayer = 0;

    @SuppressLint("UseCompatLoadingForColorStateLists")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        initViews();
        initListeners();

        SquareButton[] leftPoints = new SquareButton[]{findViewById(R.id.leftResult0), findViewById(R.id.leftResult1), findViewById(R.id.leftResult2), findViewById(R.id.leftResult3)};
        SquareButton[] rightPoints = new SquareButton[]{findViewById(R.id.rightResult0), findViewById(R.id.rightResult1), findViewById(R.id.rightResult2), findViewById(R.id.rightResult3)};

        Intent intent  = getIntent();
        player1 = new Player((String) intent.getSerializableExtra("Player1"), leftPoints);
        player2 = new Player((String) intent.getSerializableExtra("Player2"), rightPoints);

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(gameActive){
            if(activePlayer == 0) {
            turnSound = MediaPlayer.create(StartActivity.this, player1.turnSoundId);
            } else{
                turnSound = MediaPlayer.create(StartActivity.this, player2.turnSoundId);
            }
            turnSound.start();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (turnSound != null) {
            turnSound.stop();
        }
        if (winnerSound != null) {
            winnerSound.stop();
        }
        if (newGameSound != null) {
            newGameSound.stop();
        }
    }

    private void initViews(){
        buttons = new Button[]{findViewById(R.id.button00), findViewById(R.id.button01), findViewById(R.id.button02),
                findViewById(R.id.button10), findViewById(R.id.button11), findViewById(R.id.button12),
                findViewById(R.id.button20), findViewById(R.id.button21), findViewById(R.id.button22)};

        popup = getLayoutInflater().inflate(R.layout.winner_popup, null);
    }

    private void initListeners(){
        popup.findViewById(R.id.reset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter = 0;
                finish();
                Intent intent = new Intent(StartActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    // State meanings:
    //    0 - X
    //    1 - O
    //    2 - Null
    // put all win positions in a 2D array
    int[][] winPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}};
    public static int counter = 0;

    // this function will be called every time a
    // players tap in an empty box of the grid
    @SuppressLint({"ResourceAsColor", "UseCompatLoadingForColorStateLists", "SetTextI18n"})
    public void playerTap(View view) {
        turnSound.stop(); // zaustavlja sound tko je na redu cim igrac odigra
        playSound();
        Button btn = (Button) view;
        int tappedButton = Integer.parseInt(btn.getTag().toString());

        if(gameDone) {
            counter = 0;
            finish();
            Intent intent = new Intent(StartActivity.this, MainActivity.class);
            startActivity(intent);
            return;
        }

        // game reset function will be called
        // if someone wins or the boxes are full
        if (!gameActive) {
            gameReset();
        } else {
            view.setClickable(false);
            // if the tapped image is empty
            if (gameState[tappedButton] == 2) {
                // increase the counter
                // after every tap
                counter++;

                // check if its the last box
                if (counter == 9) {
                    // reset the game
                    gameActive = false;
                }

                // mark this position
                gameState[tappedButton] = activePlayer;

                // change the active player
                // from 0 to 1 or 1 to 0
                if (activePlayer == 0) {
                    // set the image of x
                    btn.setBackgroundTintList(this.getResources().getColorStateList(player1.colorId));
                    activePlayer = 1;

                } else {
                    // set the image of o
                    btn.setBackgroundTintList(this.getResources().getColorStateList(player2.colorId));
                    activePlayer = 0;
                }
            }
            int flag = 0;
            // Check if any player has won
            for (int[] winPosition : winPositions) {
                if (gameState[winPosition[0]] == gameState[winPosition[1]] &&
                        gameState[winPosition[1]] == gameState[winPosition[2]] &&
                        gameState[winPosition[0]] != 2) {
                    flag = 1;

                    // game reset function be called
                    gameActive = false;
                    if (gameState[winPosition[0]] == 0) {
                        player1.points++;
                        startAnimation(player1.pointButton[player1.points-1], player1.colorId);

                        if(player1.points == 4){
                            showPopup(player1);
                            gameDone = true;
                        }
                    } else {
                        player2.points++;
                        startAnimation(player2.pointButton[player2.points-1], player2.colorId);

                        if(player2.points == 4){
                            showPopup(player2);
                            gameDone = true;
                        }
                    }

                    for (Button button : buttons) {
                        if ((winPosition[0] != Integer.parseInt(button.getTag().toString())) && (winPosition[1] != Integer.parseInt(button.getTag().toString())) && (winPosition[2] != Integer.parseInt(button.getTag().toString()))) {
                            button.setAlpha(0.4F);
                        }
                        button.setClickable(true);
                    }

                    break;
                }
            }
            // set the status if the match draw
            if (counter == 9 && flag == 0) {
                for (Button button : buttons) {
                    button.setAlpha(0.4F);
                    button.setClickable(true);
                }
                Toast.makeText(this, "Neriješeno!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void playSound(){
        am = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        am.playSoundEffect(AudioManager.FX_KEYPRESS_SPACEBAR, 1f);
    }

    // reset the game
    @SuppressLint({"ResourceAsColor", "UseCompatLoadingForColorStateLists"})
    public void gameReset() {
        gameActive = true;
        counter = 0;

        startsFirst = !startsFirst;
        if(startsFirst) {
            activePlayer = 0;
            turnSound = MediaPlayer.create(StartActivity.this, player1.turnSoundId);
        } else{
            activePlayer = 1;
            turnSound = MediaPlayer.create(StartActivity.this, player2.turnSoundId);
        }

        for (int i = 0; i < 9; i++) {
            gameState[i] = 2;
            buttons[i].setBackgroundTintList(this.getResources().getColorStateList(R.color.gray));
            buttons[i].setAlpha(1F);
        }

        turnSound.start();
    }

    public void startAnimation(View view, int color){
        ObjectAnimator animation = ObjectAnimator.ofFloat(view, "rotationY", 180f, 0f);
        animation.setDuration(800);
        animation.start();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @SuppressLint("UseCompatLoadingForColorStateLists")
            @Override
            public void run() {
                view.setBackgroundTintList(getResources().getColorStateList(color));
            }
        }, 400);
    }

    @SuppressLint({"UseCompatLoadingForColorStateLists", "SetTextI18n"})
    public void showPopup(Player player) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        AlertDialog dialog;
        popup.findViewById(R.id.squareButton).setBackgroundTintList(this.getResources().getColorStateList(player.colorId));
        ((TextView) popup.findViewById(R.id.colorId)).setText(player.color.toUpperCase() + "!");
        dialogBuilder.setView(popup);
        dialog = dialogBuilder.create();
        dialog.show();

        winnerShortSound = MediaPlayer.create(StartActivity.this, R.raw.pobjednik_zvuk);
        winnerSound = MediaPlayer.create(StartActivity.this, player.winnerSoundId); // POBJEDNIK JE

        winnerShortSound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                winnerSound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        newGameSound = MediaPlayer.create(StartActivity.this, R.raw.nova_igra);
                        newGameSound.start();
                    }
                });
                winnerSound.start();
            }
        });
        winnerShortSound.start();

    }
}

