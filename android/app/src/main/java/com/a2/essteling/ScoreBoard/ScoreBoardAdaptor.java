package com.a2.essteling.ScoreBoard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.a2.essteling.Pass.Player;
import com.a2.essteling.R;

import java.util.LinkedList;

public class ScoreBoardAdaptor extends RecyclerView.Adapter<ScoreBoardAdaptor.ScoreBoardViewHolder> {
    private static final String LOG_TAG = ScoreBoardAdaptor.class.getSimpleName();
    private LayoutInflater mInFlater;
    private final LinkedList<Player> mPLayers;
    private ScoreBoardListener listener;
    Context context;

    public ScoreBoardAdaptor(LayoutInflater mInFlater, LinkedList<Player> mPLayers, ScoreBoardListener listener, Context context) {
        this.mInFlater = LayoutInflater.from(context);
        this.mPLayers = mPLayers;
        this.listener = listener;
        this.context = context;
    }

    @NonNull
    @Override
    public ScoreBoardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mScoreBoardView = mInFlater.inflate(R.layout.layout_tabmenu, parent, false);
        return new ScoreBoardViewHolder(mScoreBoardView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ScoreBoardViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ScoreBoardViewHolder extends RecyclerView.ViewHolder {
        final ScoreBoardAdaptor mAdaptor;
        public final Button ScoreBoardButton;


        public ScoreBoardViewHolder(@NonNull View itemView, ScoreBoardAdaptor scoreBoardAdaptor) {
            super(itemView);
            ScoreBoardButton = itemView.findViewById(R.id.buttonPlayerName);
            this.mAdaptor =  scoreBoardAdaptor;

        }
    }
}
