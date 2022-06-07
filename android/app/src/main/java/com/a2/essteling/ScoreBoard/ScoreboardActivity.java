package com.a2.essteling.ScoreBoard;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.a2.essteling.Pass.Player;
import com.a2.essteling.R;

import java.util.LinkedList;

public class ScoreboardActivity extends AppCompatActivity implements PlayerButtonListener {
    private static final String LOG_TAG = ScoreboardActivity.class.getSimpleName();
    private LinkedList<Player> players = new LinkedList<>();

    private RecyclerView playerRecyclerView;
    private PlayerListAdapter playerListAdapter;

    private RecyclerView scoreRecyclerView;
    private ScoreBoardAdaptor scoreBoardAdaptor;

    private TextView totalPointsView;

    private int totalPoints;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.Red));
        setContentView(R.layout.activity_scoreboard);

        super.onCreate(savedInstanceState);
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
        scoreBoardAdaptor = new ScoreBoardAdaptor(this, players.get(0));

        //give the adapter to the recyclerview
        scoreRecyclerView.setAdapter(scoreBoardAdaptor);

        //make the recyclerview a linear layout
        scoreRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        totalPointsView = findViewById(R.id.totalPoints);
        calculateTotalPoints();
        totalPointsView.setText("Total Points: " + totalPoints);


    }

    private int i = 0;

    //create a test list for the scoreboard
    private void testlayers(){


        players.add(new Player("Momin" , 0, Color.GRAY, testHistories()));
        players.add(new Player("Coen" , 2, Color.GRAY, testHistories()));
        players.add(new Player("Lucas" , 3, Color.GRAY, testHistories()));
        players.add(new Player("Kaan" , 2, Color.GRAY, testHistories()));
        players.add(new Player("Koen" , 4, Color.GRAY, testHistories()));
    }

    private LinkedList<Historie> testHistories(){
        LinkedList<Historie> histories = new LinkedList<>();
        histories.add(new Historie("game " + i%4, "here", 1420, i%7+1));
        this.i++;
        histories.add(new Historie("game " + i%4, "there", 1420, i%7+1));
        this.i++;
        histories.add(new Historie("game " + i%4, "somewhere", 1420, i%7+1));
        this.i++;
        histories.add(new Historie("game " + i%4, "otherwhere", 1420, i%7+1));
        this.i++;
        histories.add(new Historie("game " + i%4, "softwhere", 1420, i%7+1));
        this.i++;
        histories.add(new Historie("game " + i%4, "hardwhere", 1420, i%7+1));
        this.i++;
        histories.add(new Historie("game " + i%4, "entrance", 1420, i%7+1));
        this.i++;
        histories.add(new Historie("game " + i%4, "ip addres", 1420, i%7+1));
        this.i++;
        histories.add(new Historie("game " + i%4, "test", 1420, i%7+1));
        this.i++;
        return histories;
    }

    private void calculateTotalPoints(){
        totalPoints = 0;
        players.forEach(player -> {
            player.getGameHistorie().forEach(historie -> {
                totalPoints += historie.getPoints();
                Log.d(LOG_TAG, i+"");
            });
        });
    }

    private void addPlayersList(String name, int punten, int color, LinkedList<Historie> histories) {
        players.add(new Player(name, 0, color, histories));

    }

    //change the scoreboard to the clicked player
    @Override
    public void onPlayerClicked(Player player) {
        Log.d(LOG_TAG, player.getName());
        scoreBoardAdaptor.setHistories(player.getGameHistorie());
        scoreBoardAdaptor.notifyDataSetChanged();
    }
}