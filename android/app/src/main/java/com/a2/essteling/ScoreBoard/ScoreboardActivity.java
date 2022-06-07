package com.a2.essteling.ScoreBoard;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.a2.essteling.Pass.Player;
import com.a2.essteling.R;
import com.a2.essteling.Shop.ShopActivity;

import java.util.ArrayList;
import java.util.LinkedList;

public class ScoreboardActivity extends AppCompatActivity implements PlayerButtonListener {
    private static final String LOG_TAG = ScoreboardActivity.class.getSimpleName();
    private LinkedList<Player> players = new LinkedList<>();

    private RecyclerView playerRecyclerView;
    private PlayerListAdapter playerListAdapter;

    private RecyclerView scoreRecyclerView;
    private ScoreBoardAdaptor scoreBoardAdaptor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.Red));
        setContentView(R.layout.activity_scoreboard);

        super.onCreate(savedInstanceState);
        testlayers();
        testlayers();
        testlayers();
        testlayers();

        //players recycleview
        //get the recyclerview
        playerRecyclerView = findViewById(R.id.PlayerRecyclerView);

        //create the adapter
        playerListAdapter = new PlayerListAdapter(this, players, this);

        //give the adapter to the recyclerview
        playerRecyclerView.setAdapter(playerListAdapter);

        //make the recyclerview a linear layout
        playerRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        //scoreboard recycleview
        //get the recyclerview
        scoreRecyclerView = findViewById(R.id.ScoreRecyclerView);

        //create the adapter
        scoreBoardAdaptor = new ScoreBoardAdaptor(this, players.get(5));

        //give the adapter to the recyclerview
        scoreRecyclerView.setAdapter(scoreBoardAdaptor);

        //make the recyclerview a linear layout
        scoreRecyclerView.setLayoutManager(new LinearLayoutManager(this));


    }

    private int i = 0;


    private void testlayers(){
        LinkedList<Historie> histories = new LinkedList<>();
        histories.add(new Historie("shit game", "hell", 1420, i));
        this.i++;
        histories.add(new Historie("shit game", "hell", 1420, i));
        this.i++;
        histories.add(new Historie("shit game", "hell", 1420, i));
        this.i++;
        histories.add(new Historie("shit game", "hell", 1420, i));
        this.i++;
        histories.add(new Historie("shit game", "hell", 1420, i));
        this.i++;
        histories.add(new Historie("shit game", "hell", 1420, i));
        this.i++;
        histories.add(new Historie("shit game", "hell", 1420, i));
        this.i++;
        histories.add(new Historie("shit game", "hell", 1420, i));
        this.i++;
        histories.add(new Historie("shit game", "hell", 1420, i));
        this.i++;

        players.add(new Player("Momin" , 0, Color.GRAY, histories));
        players.add(new Player("Coen" , 2, Color.GRAY, histories));
        players.add(new Player("Lucas" , 3, Color.GRAY, histories));
        players.add(new Player("Kaan" , 2, Color.GRAY, histories));
        players.add(new Player("Koen" , 4, Color.GRAY, histories));
    }

    private void addPlayersList(String name, int punten, int color, LinkedList<Historie> histories) {
        players.add(new Player(name, 0, color, histories));

    }

    @Override
    public void onPlayerClicked(Player player) {
        Log.d(LOG_TAG, player.getName());
        scoreBoardAdaptor.setHistories(player.getGameHistorie());
        scoreBoardAdaptor.notifyDataSetChanged();
    }
}