package com.example.tictactoe;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Button;

public class Player {
    int winnerSoundId;
    int turnSoundId;
    int colorId;
    String color;
    int points = 0;
    SquareButton[] pointButton;

    @SuppressLint({"UseCompatLoadingForDrawables", "UseCompatLoadingForColorStateLists"})
    public Player(String color, SquareButton[] pointButton){
        this.color = color;
        this.pointButton = pointButton;

        switch (color){
            case "plavi":
                winnerSoundId = R.raw.pobjednik_je_plavi;
                turnSoundId = R.raw.na_redu_je_plavi;
                colorId = R.color.blue;
                break;
            case "narančasti":
                winnerSoundId = R.raw.pobjednik_je_narancasti;
                turnSoundId = R.raw.na_redu_je_narancasti;
                colorId = R.color.orange;
                break;
            case "crveni":
                winnerSoundId = R.raw.pobjednik_je_crveni;
                turnSoundId = R.raw.na_redu_je_crveni;
                colorId = R.color.red;
                break;
            case "zeleni":
                winnerSoundId = R.raw.pobjednik_je_zeleni;
                turnSoundId = R.raw.na_redu_je_zeleni;
                colorId = R.color.green;
                break;
            case "žuti":
                winnerSoundId = R.raw.pobjednik_je_zuti;
                turnSoundId = R.raw.na_redu_je_zuti;
                colorId = R.color.yellow;
                break;
            case "ružičasti":
                winnerSoundId = R.raw.pobjednik_je_ruzicasti;
                turnSoundId = R.raw.na_redu_je_ruzicasti;
                colorId = R.color.purple;
                break;
        }
    }

}
