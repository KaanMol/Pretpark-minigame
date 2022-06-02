package com.a2.essteling;

public class Historie {
    private String gameName;
    private String gameLocation;
    private int time;
    private int points;

    public Historie(String gameName, String gameLocation, int time, int points) {
        this.gameName = gameName;
        this.gameLocation = gameLocation;
        this.time = time;
        this.points = points;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getGameLocation() {
        return gameLocation;
    }

    public void setGameLocation(String gameLocation) {
        this.gameLocation = gameLocation;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
