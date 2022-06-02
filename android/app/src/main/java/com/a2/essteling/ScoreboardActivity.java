package com.a2.essteling;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ScoreboardActivity extends AppCompatActivity {
    List<Player> players = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private ShopListAdapter mAdapter;
    private LinkedList<ShopItem> items = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        testlayers();


        mRecyclerView = findViewById(R.id.tabNames);

//        mAdapter = new ScoreBoardAdaptor(this, items, this);


        setContentView(R.layout.activity_scoreboard);
    }

    private void testlayers(){
        ArrayList<Historie> emptryArrayList = new ArrayList<Historie>();
        players.add(new Player("Momin" , 0, Color.valueOf(255), emptryArrayList));
        players.add(new Player("Coen" , 2, Color.valueOf(255), emptryArrayList));
        players.add(new Player("Lucas" , 3, Color.valueOf(255), emptryArrayList));
        players.add(new Player("Kaan" , 2, Color.valueOf(255), emptryArrayList));
        players.add(new Player("Koen" , 4, Color.valueOf(255), emptryArrayList));
    }

    private void addPlayersList(String name, int punten, Color color, ArrayList<Historie> emptyArrayList) {
        players.add(new Player(name, 0, Color.valueOf(255,0,0), emptyArrayList));
    }
}