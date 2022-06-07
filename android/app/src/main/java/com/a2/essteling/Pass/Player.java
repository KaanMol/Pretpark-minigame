package com.a2.essteling.Pass;

import android.graphics.Color;

import com.a2.essteling.ScoreBoard.Historie;

import java.util.ArrayList;
import java.util.LinkedList;

public class Player {
    private String name;
    private int punten;
    private int buttonColor;
    private LinkedList<Historie> gameHistorie;

    public Player(String name, int punten, int buttonColor, LinkedList<Historie> gameHistorie) {
        this.name = name;
        this.punten = punten;
        this.buttonColor = buttonColor;
        this.gameHistorie = gameHistorie;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPunten() {
        return punten;
    }

    public void setPunten(int punten) {
        this.punten = punten;
    }

    public int getButtonColor() {
        return buttonColor;
    }

    public void setButtonColor(int buttonColor) {
        this.buttonColor = buttonColor;
    }

    public LinkedList<Historie> getGameHistorie() {
        return gameHistorie;
    }

    public void setGameHistorie(LinkedList<Historie> gameHistorie) {
        this.gameHistorie = gameHistorie;
    }
}

