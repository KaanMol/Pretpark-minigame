package com.a2.essteling.Pass;

import android.graphics.Color;

import com.a2.essteling.ScoreBoard.Historie;

import java.util.ArrayList;

public class Player {
    private String name;
    private int punten;
    private Color buttonColor;
    private ArrayList<Historie> gameHistorie;

    public Player(String name, int punten, Color buttonColor, ArrayList<Historie> gameHistorie) {
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

    public Color getButtonColor() {
        return buttonColor;
    }

    public void setButtonColor(Color buttonColor) {
        this.buttonColor = buttonColor;
    }

    public ArrayList<Historie> getGameHistorie() {
        return gameHistorie;
    }

    public void setGameHistorie(ArrayList<Historie> gameHistorie) {
        this.gameHistorie = gameHistorie;
    }
}
