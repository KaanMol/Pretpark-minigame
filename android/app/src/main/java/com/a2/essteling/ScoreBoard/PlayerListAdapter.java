package com.a2.essteling.ScoreBoard;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.a2.essteling.PlayerData.Player;
import com.a2.essteling.PlayerData.PlayerList;
import com.a2.essteling.R;

import java.util.LinkedList;

public class PlayerListAdapter extends RecyclerView.Adapter<PlayerListAdapter.PlayerListHolder> {
    private LinkedList<Player> playerList;
    private LayoutInflater mInflater;
    private PlayerButtonListener listener;
    private Context context;

    public PlayerListAdapter(Context context, LinkedList<Player> playerList, PlayerButtonListener listener) {
        this.mInflater = LayoutInflater.from(context);
        this.playerList = playerList;
        this.listener = listener;
        this.context = context;
    }

    @NonNull
    @Override
    public PlayerListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mPlayerView = mInflater.inflate(R.layout.layout_tabmenu, parent, false);
        return new PlayerListHolder(mPlayerView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerListHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.playerButton.setText(playerList.get(position).getName());
        holder.playerButton.setBackgroundTintList(context.getResources().getColorStateList(playerList.get(position).getColor()));

        //React on a clicked player
        holder.playerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //tell teh scoreboard that it needs to show a different history
                listener.onPlayerClicked(playerList.get(position));

                //change the colors of the buttons
                PlayerList.resetColor();
                playerList.get(position).setColor(R.color.Red);

                //notify that the colors have echanged
                notifyDataSetChanged();
            }
        });


    }

    @Override
    public int getItemCount() {
        return this.playerList.size();
    }

    public void setPlayers(LinkedList<Player> players) {
        this.playerList = players;
        notifyDataSetChanged();
    }

    public class PlayerListHolder extends RecyclerView.ViewHolder {
        final PlayerListAdapter mAdapter;
        public final Button playerButton;

        public PlayerListHolder(@NonNull View itemView, PlayerListAdapter adapter) {
            super(itemView);
            playerButton = itemView.findViewById(R.id.buttonPlayerName);
            this.mAdapter = adapter;
        }
    }
}
