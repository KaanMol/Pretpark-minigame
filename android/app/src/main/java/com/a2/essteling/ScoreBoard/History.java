package com.a2.essteling.ScoreBoard;

public class History {
    private String gameId;
    private String gameLocation = "nope";
    private String timestamp;
    private int points;
    private String nfcId;

    public History(String gameId, String gameLocation, String time, int points, String nfcId) {
        this.gameId = gameId;
        this.gameLocation = gameLocation;
        this.timestamp = time;
        this.points = points;
        this.nfcId = nfcId;
    }

    public History(){

    }

    public String getNfcId() {
        return nfcId;
    }

    public void setNfcId(String nfcId) {
        this.nfcId = nfcId;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getGameLocation() {
        return gameLocation;
    }

    public void setGameLocation(String gameLocation) {
        this.gameLocation = gameLocation;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
