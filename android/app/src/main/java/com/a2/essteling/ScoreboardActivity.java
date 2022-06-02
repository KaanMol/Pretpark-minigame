package com.a2.essteling;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ScoreboardActivity extends AppCompatActivity {
    List<Player> players = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);
    }

    private void addPlayersList(String name, int punten, Color color, ArrayList<Historie> emptyArrayList) {
        players.add(new Player(name, 0, Color.valueOf(255,0,0), emptyArrayList));
    }
}