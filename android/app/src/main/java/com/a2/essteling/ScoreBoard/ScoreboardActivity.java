package com.a2.essteling.ScoreBoard;

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
        PlayerList.testPlayers();


        if (!PlayerList.getPlayers().isEmpty()) {
            PlayerList.resetColor();

            this.players = PlayerList.getPlayers();
            this.players.get(0).setColor(R.color.black);

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