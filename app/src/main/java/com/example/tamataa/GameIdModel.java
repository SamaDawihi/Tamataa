package com.example.tamataa;

public class GameIdModel {
    private int gameId;
    private int gameTypeId;
    private String majorName;
    private String name;

    public GameIdModel(int gameId, int gameTypeId, String majorName, String name) {
        this.gameId = gameId;
        this.gameTypeId = gameTypeId;
        this.majorName = majorName;
        this.name = name;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getGameTypeId() {
        return gameTypeId;
    }

    public void setGameTypeId(int gameTypeId) {
        this.gameTypeId = gameTypeId;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
