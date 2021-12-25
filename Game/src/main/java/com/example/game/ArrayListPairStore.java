package com.example.game;

import java.util.ArrayList;

public class ArrayListPairStore {
    ArrayList<Game_Objects> gameObjectsArrayList;
    ArrayList<Island> islandArrayList;

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

    ArrayListPairStore(ArrayList<Game_Objects> objectsArrayList, ArrayList<Island> islandArrayList){
        this.gameObjectsArrayList = objectsArrayList;
        this.islandArrayList = islandArrayList;
    }
}
