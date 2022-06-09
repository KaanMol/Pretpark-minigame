package com.a2.essteling.Pass;

import android.content.Context;

import com.a2.essteling.R;
import com.a2.essteling.ScoreBoard.History;
import com.a2.essteling.ScoreBoard.HistoryList;
import com.a2.essteling.ScoreBoard.HistoryListener;

import java.util.LinkedList;

public class Player implements HistoryListener {
    private String name;
    private LinkedList<History> gameHistorie;
    private String cardId;
    private int colorId;

    public Player(String name, LinkedList<History> gameHistorie) {
        this.name = name;
        this.gameHistorie = gameHistorie;
        this.colorId = R.color.Red;
    }

    public Player(String name, String cardId, Context context){
        this.name = name;
        this.cardId = cardId;

        HistoryList historyListRequest = new HistoryList(cardId, this, context);

        this.gameHistorie = new LinkedList<>();
        this.colorId = R.color.Red;


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

    public int getColor() {
        return this.colorId;
    }

    public void setColor(int colorId){
        this.colorId = colorId;
    }
}

