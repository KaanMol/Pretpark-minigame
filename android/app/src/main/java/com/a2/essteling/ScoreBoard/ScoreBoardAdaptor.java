package com.a2.essteling.ScoreBoard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.a2.essteling.Pass.Player;
import com.a2.essteling.R;

import java.util.LinkedList;

public class ScoreBoardAdaptor extends RecyclerView.Adapter<ScoreBoardAdaptor.ScoreBoardViewHolder> {
    private static final String LOG_TAG = ScoreBoardAdaptor.class.getSimpleName();
    private LayoutInflater mInFlater;
    private LinkedList<History> mHistories;
    private ScoreBoardListener listener;
    Context context;

    public ScoreBoardAdaptor(Context context, Player player) {
        this.mInFlater = LayoutInflater.from(context);
        this.mHistories = player.getGameHistorie();
    }

    @NonNull
    @Override
    public ScoreBoardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mScoreBoardView = mInFlater.inflate(R.layout.layout_scoreboard, parent, false);
        return new ScoreBoardViewHolder(mScoreBoardView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ScoreBoardViewHolder holder, int position) {
        holder.GameTime.setText(mHistories.get(position).getTimestamp()+"");
        holder.GameLocation.setText(mHistories.get(position).getGameLocation());
        holder.GamePoints.setText(mHistories.get(position).getPoints()+" points");
        holder.GameName.setText(mHistories.get(position).getGameId());
    }

    public void setHistories(LinkedList<History> mHistories) {
        this.mHistories = mHistories;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mHistories.size();
    }

    public class ScoreBoardViewHolder extends RecyclerView.ViewHolder {
        final ScoreBoardAdaptor mAdaptor;
        public final TextView GameName;
        public final TextView GamePoints;
        public final TextView GameTime;
        public final TextView GameLocation;


        public ScoreBoardViewHolder(@NonNull View itemView, ScoreBoardAdaptor scoreBoardAdaptor) {
            super(itemView);
            GameName = itemView.findViewById(R.id.gameName);
            GamePoints = itemView.findViewById(R.id.GamePoints);
            GameLocation = itemView.findViewById(R.id.GameLocation);
            GameTime = itemView.findViewById(R.id.GameTime);
            this.mAdaptor =  scoreBoardAdaptor;

        }
    }
}
