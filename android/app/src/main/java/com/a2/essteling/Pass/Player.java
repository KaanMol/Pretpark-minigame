package com.a2.essteling.Pass;

import android.content.Context;

import com.a2.essteling.ScoreBoard.History;
import com.a2.essteling.ScoreBoard.HistoryList;
import com.a2.essteling.ScoreBoard.HistoryListener;

import java.util.LinkedList;

public class Player implements HistoryListener {
    private String name;
    private LinkedList<History> gameHistorie;
    private String accountId;

    public Player(String name, LinkedList<History> gameHistorie) {
        this.name = name;
        this.gameHistorie = gameHistorie;
    }

    public Player(String name, String accountId, Context context){
        this.name = name;
        this. accountId = accountId;

        HistoryList historyListRequest = new HistoryList(accountId, this, context);

        this.gameHistorie = new LinkedList<>();

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LinkedList<History> getGameHistorie() {
        return gameHistorie;
    }

    public void setGameHistorie(LinkedList<History> gameHistorie) {
        this.gameHistorie = gameHistorie;
    }

    @Override
    public void onHistoryReceived(LinkedList<History> histories) {
        this.gameHistorie = histories;
    }
}

