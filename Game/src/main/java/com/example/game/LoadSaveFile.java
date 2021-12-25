package com.example.game;

import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;

import java.io.*;
import java.util.ArrayList;

public class LoadSaveFile {
    private ArrayList<Game_Objects> myList;
    private final Game_Objects deserializedObj = null;
    private Player player;
    private Hero hero;
    private void deserialize(String fileName) throws IOException, ClassNotFoundException {
        ObjectInputStream in = null;
        myList = new ArrayList<>();
        try {
            in = new ObjectInputStream(new FileInputStream(fileName));
            Player deserializePlayer = (Player) in.readObject();
            player = new Player(deserializePlayer.getHero());
            hero = player.getHero();
            while(true) {
                try{
                    Game_Objects tmp = (Game_Objects) in.readObject();
                    myList.add(tmp);
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

    public ArrayList<Game_Objects> regenerateGame_Objects(String fileName, AnchorPane mainPane, WeaponButton button1 , WeaponButton button2) throws IOException, ClassNotFoundException {
        deserialize(fileName);
        System.out.println(fileName);
        ArrayList<Game_Objects> finalList = new ArrayList<>();
        for(int i=0;i<myList.size();i++) {
            Game_Objects obj = myList.get(i);
            if(obj.getName().equals("Green Orc")) {
                Standard_Green_Orc s = new Standard_Green_Orc(mainPane, obj.getPosition(), (int)obj.getImageViewWidth(), (int)obj.getImageViewHeight(), obj.getSpeed(), obj.getIslandofResidence());
                finalList.add(s);
            }
            else if(obj.getName().equals("Red Orc")) {
                Standard_Red_Orc s = new Standard_Red_Orc(mainPane, obj.getPosition(), (int)obj.getImageViewWidth(), (int)obj.getImageViewHeight(), obj.getSpeed(), obj.getIslandofResidence());
                finalList.add(s);
            }
            else if(obj.getName().equals("TNT")) {
                TNT s = new TNT(mainPane, obj.getPosition(), (int)obj.getImageViewWidth(), (int)obj.getImageViewHeight(), obj.getSpeed(), obj.getIslandofResidence());
                finalList.add(s);
            }
            else if(obj.getName().equals("Boss Orc")) {
                Boss_Orc s = new Boss_Orc();
                finalList.add(s);
            }
            else if(obj.getName().equals("Weapon Chest Sword")) {
                Weapon_Chest s = new Weapon_Chest(mainPane, obj.getPosition(), (int) obj.getImageViewWidth(), (int) obj.getImageViewHeight(), "Sword", obj.getIslandofResidence(), button1 , button2);
                finalList.add(s);
            }
            else if(obj.getName().equals("Weapon Chest Lance")) {
                Weapon_Chest s = new Weapon_Chest(mainPane, obj.getPosition(), (int) obj.getImageViewWidth(), (int) obj.getImageViewHeight(), "Lance", obj.getIslandofResidence(), button1 , button2);
                finalList.add(s);
            }
            else if(obj.getName().equals("Coin Chest")) {
                Coin_Chest s = new Coin_Chest(mainPane, obj.getPosition(), (int) obj.getImageViewWidth(), (int) obj.getImageViewHeight(), obj.getIslandofResidence());
                finalList.add(s);
            }
        }
        return finalList;
    }
}