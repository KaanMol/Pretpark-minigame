package com.a2.essteling.ScoreBoard;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

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
//        testlayers();


        if (!PlayerList.players.isEmpty()) {

            this.players = PlayerList.players;

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


        } else {
            Toast.makeText(this, "No cards registered", Toast.LENGTH_LONG).show();
        }


        //set the total points
        totalPointsView = findViewById(R.id.totalPoints);
        totalPointsView.setText("Total Points: " + PlayerList.totalPoints());
    }

    private int i = 0;

    //create a test list for the scoreboard
    private void testlayers() {


        players.add(new Player("Momin", testHistories()));
        players.add(new Player("Coen", testHistories()));
        players.add(new Player("Lucas", testHistories()));
        players.add(new Player("Kaan", testHistories()));
        players.add(new Player("Koen", testHistories()));
    }

    private LinkedList<History> testHistories() {
        LinkedList<History> histories = new LinkedList<>();
        histories.add(new History("game " + i % 4, "here", "14:20", i % 7 + 1, "0000"));
        this.i++;
        histories.add(new History("game " + i % 4, "there", "14:20", i % 7 + 1, "0000"));
        this.i++;
        histories.add(new History("game " + i % 4, "somewhere", "14:20", i % 7 + 1, "0000"));
        this.i++;
        histories.add(new History("game " + i % 4, "otherwhere", "14:20", i % 7 + 1, "0000"));
        this.i++;
        histories.add(new History("game " + i % 4, "softwhere", "14:20", i % 7 + 1, "0000"));
        this.i++;
        histories.add(new History("game " + i % 4, "hardwhere", "14:20", i % 7 + 1, "0000"));
        this.i++;
        histories.add(new History("game " + i % 4, "entrance", "14:20", i % 7 + 1, "0000"));
        this.i++;
        histories.add(new History("game " + i % 4, "ip addres", "14:20", i % 7 + 1, "0000"));
        this.i++;
        histories.add(new History("game " + i % 4, "test", "14:20", i % 7 + 1, "0000"));
        this.i++;
        return histories;
    }

    private void addPlayersList(String name, LinkedList<History> histories) {
        players.add(new Player(name, histories));

    }

    private void updatePlayers(LinkedList<Player> players) {
        this.players = players;
        playerListAdapter.setPlayers(this.players);
    }

    //change the scoreboard to the clicked player
    @Override
    public void onPlayerClicked(Player player) {
        Log.d(LOG_TAG, player.getName());

        if(player.getGameHistorie().isEmpty()){
            Toast.makeText(this, "No history for: " + player.getName(), Toast.LENGTH_LONG).show();
        }

        scoreBoardAdaptor.setHistories(player.getGameHistorie());
    }
}