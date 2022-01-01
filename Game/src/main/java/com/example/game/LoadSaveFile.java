package com.example.game;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.*;
import java.util.ArrayList;

public class LoadSaveFile {
    private final ArrayList<Object> store = new ArrayList<>();
    private final Game_Objects deserializedObj = null;
    private Player player;
    private Hero hero;
    private transient MediaPlayer orcjump;
    private transient MediaPlayer herojump;
    private WeaponButton swordbutton;
    private WeaponButton lancebutton;

    public LoadSaveFile() {

    }

    private void deserialize(String fileName) throws IOException, ClassNotFoundException {
        ObjectInputStream in = null;
        in = new ObjectInputStream(new FileInputStream(fileName));
        try {
            Player deserializePlayer = (Player) in.readObject();
            player = new Player(deserializePlayer.getHero());
            hero = player.getHero();
            while(true) {
                try{
                    Object tmp = in.readObject();
                    store.add(tmp);
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

    public SaveFileReturn loadGameState(String fileName, AnchorPane mainPane) throws IOException, ClassNotFoundException {
        deserialize(fileName);
        this.swordbutton = new WeaponButton("Sword",25,525,hero);
        this.lancebutton = new WeaponButton("Lance",100,525,hero);
        swordbutton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                swordbutton.setactive();
                lancebutton.setinactive();

            }
        });
        lancebutton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                lancebutton.setactive();
                swordbutton.setinactive();

            }
        });
        //.................
        String audiopath1 = "src/main/resources/com/example/game/audios/chestcollect.mp3";
        Media media1= new Media(new File(audiopath1).toURI().toString());
        MediaPlayer player1 = new MediaPlayer(media1);
        MediaView coinchestsound = new MediaView(player1);
        player1.setVolume(0.4);
        player1.setCycleCount(1);
        mainPane.getChildren().add(coinchestsound);
        String audiopath2 = "src/main/resources/com/example/game/audios/chestopen.mp3";
        Media  media2= new Media(new File(audiopath2).toURI().toString());
        MediaPlayer player2 = new MediaPlayer(media2);
        MediaView weaponchestsound = new MediaView(player2);
        player2.setVolume(0.4);
        player2.setCycleCount(1);
        mainPane.getChildren().add(weaponchestsound);
        String audiopath3 = "src/main/resources/com/example/game/audios/herodie.mp3";
        Media  media3= new Media(new File(audiopath3).toURI().toString());
        MediaPlayer player3 = new MediaPlayer(media3);
        MediaView herodiesound = new MediaView(player3);
        player3.setVolume(0.4);
        player3.setCycleCount(1);
        mainPane.getChildren().add(herodiesound);
        String audiopath4 = "src/main/resources/com/example/game/audios/herorevive.mp3";
        Media  media4= new Media(new File(audiopath4).toURI().toString());
        MediaPlayer player4 = new MediaPlayer(media4);
        MediaView herorevivesound = new MediaView(player4);
        player4.setVolume(0.4);
        player4.setCycleCount(1);
        mainPane.getChildren().add(herorevivesound);
        String audiopath5 = "src/main/resources/com/example/game/audios/orcdie.mp3";
        Media  media5= new Media(new File(audiopath5).toURI().toString());
        MediaPlayer player5 = new MediaPlayer(media5);
        MediaView orcdiesound = new MediaView(player5);
        player5.setVolume(0.4);
        player5.setCycleCount(1);
        mainPane.getChildren().add(orcdiesound);
        String audiopath6 = "src/main/resources/com/example/game/audios/tntburst.mp3";
        Media  media6= new Media(new File(audiopath6).toURI().toString());
        MediaPlayer player6 = new MediaPlayer(media6);
        MediaView tntburstsound = new MediaView(player6);
        player6.setVolume(0.4);
        player6.setCycleCount(1);
        mainPane.getChildren().add(tntburstsound);
        //.................

        System.out.println(fileName);
        ArrayList<Game_Objects> gameObjectsList = new ArrayList<>();
        ArrayList<Island> islandList = new ArrayList<>();
        int orcid =0;
        for (Object obj: store){
            if (obj.toString().equals("Island")){
                Island island = (Island)obj;
                islandList.add(new Island(island.getIslandType(), mainPane, island.getPosition(), island.getWidth(), island.getHeight(), island.getSpeed()));
            }
            else if (obj.toString().equals("Green Orc")) {
                Standard_Green_Orc standard_green_orc = (Standard_Green_Orc)obj;
                Standard_Green_Orc s = new Standard_Green_Orc(mainPane, standard_green_orc.getPosition(), standard_green_orc.getWidth(), standard_green_orc.getHeight(), standard_green_orc.getSpeed(), standard_green_orc.getIslandofResidence(), player5,orcid);
                orcid++;
                gameObjectsList.add(s);
            } else if (obj.toString().equals("Red Orc")) {
                Standard_Red_Orc standard_red_orc = (Standard_Red_Orc)obj;
                Standard_Red_Orc s = new Standard_Red_Orc(mainPane, standard_red_orc.getPosition(),standard_red_orc.getWidth(), standard_red_orc.getHeight(), standard_red_orc.getSpeed(), standard_red_orc.getIslandofResidence(), player5,orcid);
                orcid++;
                gameObjectsList.add(s);
            } else if (obj.toString().equals("TNT")) {
                TNT tnt = (TNT)obj;
                TNT s = new TNT(mainPane, tnt.getPosition(), tnt.getWidth(), tnt.getHeight(), tnt.getSpeed(), tnt.getIslandofResidence(), player6);
                gameObjectsList.add(s);
            } else if (obj.toString().equals("Boss Orc")) {
                Boss_Orc Boss_Orc = (Boss_Orc)obj;
                Boss_Orc s = new Boss_Orc(mainPane, Boss_Orc.getPosition(), Boss_Orc.getWidth(), Boss_Orc.getHeight(), Boss_Orc.getSpeed(), Boss_Orc.getIslandofResidence(), player5,orcid);
                orcid++;
                gameObjectsList.add(s);
            } else if (obj.toString().equals("Weapon Chest Sword")) {
                Weapon_Chest weapon_chest = (Weapon_Chest)obj;
                Weapon_Chest s = new Weapon_Chest(mainPane, weapon_chest.getPosition(), weapon_chest.getWidth(), weapon_chest.getHeight(), "Sword", weapon_chest.getIslandofResidence(), swordbutton, lancebutton, player2);
                gameObjectsList.add(s);
            } else if (obj.toString().equals("Weapon Chest Lance")) {
                Weapon_Chest weapon_chest = (Weapon_Chest)obj;
                Weapon_Chest s = new Weapon_Chest(mainPane, weapon_chest.getPosition(), weapon_chest.getWidth(), weapon_chest.getHeight(), "Lance", weapon_chest.getIslandofResidence(), swordbutton, lancebutton, player2);
                gameObjectsList.add(s);
            } else if (obj.toString().equals("Coin Chest")) {
                Coin_Chest coin_chest = (Coin_Chest)obj;
                Coin_Chest s = new Coin_Chest(mainPane, coin_chest.getPosition(), coin_chest.getWidth(), coin_chest.getHeight(), coin_chest.getIslandofResidence(), player1);
                gameObjectsList.add(s);
            }
        }
        return new SaveFileReturn(gameObjectsList, islandList, player);
    }
}