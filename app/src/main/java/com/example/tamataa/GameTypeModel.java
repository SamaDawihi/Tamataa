package com.example.tamataa;

public class GameTypeModel {
    private int gameTypeId;
    private String name;

    public GameTypeModel(int gameTypeId, String name) {
        this.gameTypeId = gameTypeId;
        this.name = name;
    }

    public int getGameTypeId() {
        return gameTypeId;
    }

    public void setGameTypeId(int gameTypeId) {
        this.gameTypeId = gameTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
