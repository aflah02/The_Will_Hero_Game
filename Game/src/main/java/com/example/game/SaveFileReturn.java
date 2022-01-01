package com.example.game;

import java.io.Serializable;
import java.util.ArrayList;

public class SaveFileReturn implements Serializable {
    ArrayList<Game_Objects> gameObjectsArrayList;
    ArrayList<Island> islandArrayList;
    Player player;
    public ArrayList<Game_Objects> getGameObjectsArrayList() {
        return gameObjectsArrayList;
    }

    public void setGameObjectsArrayList(ArrayList<Game_Objects> gameObjectsArrayList) {
        this.gameObjectsArrayList = gameObjectsArrayList;
    }

    public ArrayList<Island> getIslandArrayList() {
        return islandArrayList;
    }

    public void setIslandArrayList(ArrayList<Island> islandArrayList) {
        this.islandArrayList = islandArrayList;
    }

    SaveFileReturn(ArrayList<Game_Objects> objectsArrayList, ArrayList<Island> islandArrayList, Player player){
        this.gameObjectsArrayList = objectsArrayList;
        this.islandArrayList = islandArrayList;
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
