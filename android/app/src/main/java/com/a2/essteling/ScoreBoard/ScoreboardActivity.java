package com.a2.essteling.ScoreBoard;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.a2.essteling.PlayerData.Player;
import com.a2.essteling.PlayerData.History;
import com.a2.essteling.PlayerData.PlayerList;
import com.a2.essteling.PlayerData.PlayerListener;
import com.a2.essteling.R;

import java.util.LinkedList;

public class ScoreboardActivity extends AppCompatActivity implements PlayerButtonListener, PlayerListener {
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
        Log.i(LOG_TAG, "Opened scoreboard activity");
        getWindow().setStatusBarColor(ContextCompat.getColor(this, android.R.color.darker_gray));
        setContentView(R.layout.activity_scoreboard);

        super.onCreate(savedInstanceState);
//        PlayerList.testPlayers();


        if (!PlayerList.getPlayers().isEmpty()) {
            PlayerList.resetColor();

            this.players = PlayerList.getPlayers();
            this.players.get(0).setColor(R.color.Red);

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
            Toast.makeText(this, R.string.NoCardsRegistered, Toast.LENGTH_LONG).show();
        }


        //set the total points
        totalPointsView = findViewById(R.id.totalPoints);
        totalPointsView.setText(getString(R.string.totalPoints)+" " + PlayerList.totalPoints());
    }

    public void onBackButton(View view){
        Log.i(LOG_TAG, "Back button pressed");
        this.finish();
    }

    //add a player
    private void addPlayerToList(String name, LinkedList<History> histories) {
        Log.i(LOG_TAG, "Adding player to list: " + name);
        players.add(new Player(name, histories));
    }

    //change the playerlist
    private void updatePlayers(LinkedList<Player> players) {
        Log.i(LOG_TAG, "Updating playerlist");
        this.players = players;
        playerListAdapter.setPlayers(this.players);
    }

    //change the scoreboard to the clicked player
    @Override
    public void onPlayerClicked(Player player) {
        Log.d(LOG_TAG, "Showing scoreboard of " + player.getName());

        if(player.getGameHistorie().isEmpty()){
            Toast.makeText(this, getString(R.string.NoHistoryFor) + player.getName(), Toast.LENGTH_LONG).show();
        }

        //refresh the history
        player.updateHistoryRequest(this);

        scoreBoardAdaptor.setHistories(player.getGameHistorie());
    }

    @Override
    public void updatePlayer(Player player) {
        Log.d(LOG_TAG, "updating " + player.getName());
        scoreBoardAdaptor.setHistories(player.getGameHistorie());
        scoreBoardAdaptor.notifyDataSetChanged();
    }
}