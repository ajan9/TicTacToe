package com.example.tictactoe;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Button;

public class Player {
    int soundId;
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
                soundId = R.raw.odaberi_boje;
                colorId = R.color.blue;
                break;
            case "narančasti":
                soundId = R.raw.odaberi_boje;
                colorId = R.color.orange;
                break;
            case "crveni":
                soundId = R.raw.odaberi_boje;
                colorId = R.color.red;
                break;
            case "zeleni":
                soundId = R.raw.odaberi_boje;
                colorId = R.color.green;
                break;
            case "žuti":
                soundId = R.raw.odaberi_boje;
                colorId = R.color.yellow;
                break;
            case "ljubičasti":
                soundId = R.raw.odaberi_boje;
                colorId = R.color.purple;
                break;
        }
    }

}
