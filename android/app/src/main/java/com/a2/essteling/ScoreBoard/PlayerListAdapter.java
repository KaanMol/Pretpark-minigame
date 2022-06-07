package com.a2.essteling.ScoreBoard;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.a2.essteling.Pass.Player;
import com.a2.essteling.R;
import com.a2.essteling.Shop.ShopListAdapter;

import java.util.LinkedList;

public class PlayerListAdapter extends RecyclerView.Adapter<PlayerListAdapter.PlayerListHolder>{
    private LinkedList<Player> playerList;
    private LayoutInflater mInflater;
    private PlayerButtonListener listener;

    public PlayerListAdapter(Context context, LinkedList<Player> playerList, PlayerButtonListener listener) {
        this.mInflater = LayoutInflater.from(context);
        this.playerList = playerList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PlayerListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mPlayerView = mInflater.inflate(R.layout.layout_tabmenu, parent, false);
        return new PlayerListHolder(mPlayerView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerListHolder holder, int position) {
        holder.playerButton.setText(playerList.get(position).getName());
        holder.playerButton.setHintTextColor(playerList.get(position).getButtonColor());

        holder.playerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onPlayerClicked(playerList.get(position));
            }
        });



    }

    @Override
    public int getItemCount() {
        return this.playerList.size();
    }

    public class PlayerListHolder extends RecyclerView.ViewHolder{
        final PlayerListAdapter mAdapter;
        public final Button playerButton;

        public PlayerListHolder(@NonNull View itemView, PlayerListAdapter adapter) {
            super(itemView);
            playerButton = itemView.findViewById(R.id.buttonPlayerName);
            this.mAdapter = adapter;
        }
    }
}
