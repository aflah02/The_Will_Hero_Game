package com.example.game;

import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;

import java.io.*;
import java.util.ArrayList;

public class LoadSaveFile {
    private ArrayList<Game_Objects> tempGameObjectsStore;
    private ArrayList<Island> tempIslandStore;
    private final Game_Objects deserializedObj = null;
    private Player player;
    private Hero hero;
    private void deserialize(String fileName, int islandCount, int gameObjectCount) throws IOException, ClassNotFoundException {
        ObjectInputStream in = null;
        tempGameObjectsStore = new ArrayList<>();
        in = new ObjectInputStream(new FileInputStream(fileName));
        try {
            Player deserializePlayer = (Player) in.readObject();
            player = new Player(deserializePlayer.getHero());
            hero = player.getHero();
            int i = 0;
            while (i < islandCount){
                try{
                    Island tmp = (Island) in.readObject();
                    i++;
                    this.tempIslandStore.add(tmp);
                }catch (EOFException e) {
                    break;
                }catch (ClassCastException e) {
                    System.out.println("Invalid Class Cast Exception");
                }
            }
            int j = 0;
            while(j < gameObjectCount) {
                j++;
                try{
                    Game_Objects tmp = (Game_Objects) in.readObject();
                    tempGameObjectsStore.add(tmp);
                }catch (EOFException e) {
                    break;
                }catch (ClassCastException e) {
                    System.out.println("Invalid Class Cast Exception");
                }
            }
        }
        finally {
            in.close();
        }
    }

    public ArrayListPairStore loadGameState(String fileName, AnchorPane mainPane, WeaponButton button1 , WeaponButton button2, int islandCount, int gameObjectCount) throws IOException, ClassNotFoundException {
        deserialize(fileName, islandCount, gameObjectCount);
        System.out.println(fileName);
        ArrayList<Game_Objects> gameObjectsList = new ArrayList<>();
        ArrayList<Island> islandList = new ArrayList<>();
        for (Island island : tempIslandStore) {
            islandList.add(new Island(island.getIslandType(), mainPane, island.getPosition(), (int) island.getIslandImageViewWidth(), (int) island.getIslandImageViewHeight(), island.getSpeed()));
        }
        for (Game_Objects obj : tempGameObjectsStore) {
            if (obj.getName().equals("Green Orc")) {
                Standard_Green_Orc s = new Standard_Green_Orc(mainPane, obj.getPosition(), (int) obj.getImageViewWidth(), (int) obj.getImageViewHeight(), obj.getSpeed(), obj.getIslandofResidence());
                gameObjectsList.add(s);
            } else if (obj.getName().equals("Red Orc")) {
                Standard_Red_Orc s = new Standard_Red_Orc(mainPane, obj.getPosition(), (int) obj.getImageViewWidth(), (int) obj.getImageViewHeight(), obj.getSpeed(), obj.getIslandofResidence());
                gameObjectsList.add(s);
            } else if (obj.getName().equals("TNT")) {
                TNT s = new TNT(mainPane, obj.getPosition(), (int) obj.getImageViewWidth(), (int) obj.getImageViewHeight(), obj.getSpeed(), obj.getIslandofResidence());
                gameObjectsList.add(s);
            } else if (obj.getName().equals("Boss Orc")) {
                Boss_Orc s = new Boss_Orc();
                gameObjectsList.add(s);
            } else if (obj.getName().equals("Weapon Chest Sword")) {
                Weapon_Chest s = new Weapon_Chest(mainPane, obj.getPosition(), (int) obj.getImageViewWidth(), (int) obj.getImageViewHeight(), "Sword", obj.getIslandofResidence(), button1, button2);
                gameObjectsList.add(s);
            } else if (obj.getName().equals("Weapon Chest Lance")) {
                Weapon_Chest s = new Weapon_Chest(mainPane, obj.getPosition(), (int) obj.getImageViewWidth(), (int) obj.getImageViewHeight(), "Lance", obj.getIslandofResidence(), button1, button2);
                gameObjectsList.add(s);
            } else if (obj.getName().equals("Coin Chest")) {
                Coin_Chest s = new Coin_Chest(mainPane, obj.getPosition(), (int) obj.getImageViewWidth(), (int) obj.getImageViewHeight(), obj.getIslandofResidence());
                gameObjectsList.add(s);
            }
        }
        return new ArrayListPairStore(gameObjectsList, islandList);
    }
}