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
    private ArrayList<Game_Objects> tempGameObjectsStore;
    private ArrayList<Island> tempIslandStore;
    private final Game_Objects deserializedObj = null;
    private Player player;
    private Hero hero;
    private MediaPlayer orcjump,orcdie;
    private MediaPlayer herojump,herodie,herorevive;
    private MediaPlayer tntburstsound , weaponchestsound , coinchestsound;
    private WeaponButton swordbutton;
    private WeaponButton lancebutton;

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

    public SaveFileReturn loadGameState(String fileName, AnchorPane mainPane, int islandCount, int gameObjectCount) throws IOException, ClassNotFoundException {
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
        this.coinchestsound = player1;
        mainPane.getChildren().add(coinchestsound);
        String audiopath2 = "src/main/resources/com/example/game/audios/chestopen.mp3";
        Media  media2= new Media(new File(audiopath2).toURI().toString());
        MediaPlayer player2 = new MediaPlayer(media2);
        this.weaponchestsound = player2;
        MediaView weaponchestsound = new MediaView(player2);
        player2.setVolume(0.4);
        player2.setCycleCount(1);
        mainPane.getChildren().add(weaponchestsound);
        String audiopath3 = "src/main/resources/com/example/game/audios/herodie.mp3";
        Media  media3= new Media(new File(audiopath3).toURI().toString());
        MediaPlayer player3 = new MediaPlayer(media3);
        this.herodie = player3;
        MediaView herodiesound = new MediaView(player3);
        player3.setVolume(0.4);
        player3.setCycleCount(1);
        mainPane.getChildren().add(herodiesound);
        String audiopath4 = "src/main/resources/com/example/game/audios/herorevive.mp3";
        Media  media4= new Media(new File(audiopath4).toURI().toString());
        MediaPlayer player4 = new MediaPlayer(media4);
        this.herorevive = player4;
        MediaView herorevivesound = new MediaView(player4);
        player4.setVolume(0.4);
        player4.setCycleCount(1);
        mainPane.getChildren().add(herorevivesound);
        String audiopath5 = "src/main/resources/com/example/game/audios/orcdie.mp3";
        Media  media5= new Media(new File(audiopath5).toURI().toString());
        MediaPlayer player5 = new MediaPlayer(media5);
        this.orcdie = player5;
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
        this.tntburstsound = player6;
        mainPane.getChildren().add(tntburstsound);
        //.................
        deserialize(fileName, islandCount, gameObjectCount);
        System.out.println(fileName);
        ArrayList<Game_Objects> gameObjectsList = new ArrayList<>();
        ArrayList<Island> islandList = new ArrayList<>();
        for (Island island : tempIslandStore) {
            islandList.add(new Island(island.getIslandType(), mainPane, island.getPosition(), (int) island.getIslandImageViewWidth(), (int) island.getIslandImageViewHeight(), island.getSpeed()));
        }
        for (Game_Objects obj : tempGameObjectsStore) {
            if (obj.getName().equals("Green Orc")) {
                Standard_Green_Orc s = new Standard_Green_Orc(mainPane, obj.getPosition(), (int) obj.getImageViewWidth(), (int) obj.getImageViewHeight(), obj.getSpeed(), obj.getIslandofResidence(),this.orcdie);
                gameObjectsList.add(s);
            } else if (obj.getName().equals("Red Orc")) {
                Standard_Red_Orc s = new Standard_Red_Orc(mainPane, obj.getPosition(), (int) obj.getImageViewWidth(), (int) obj.getImageViewHeight(), obj.getSpeed(), obj.getIslandofResidence(),this.orcdie);
                gameObjectsList.add(s);
            } else if (obj.getName().equals("TNT")) {
                TNT s = new TNT(mainPane, obj.getPosition(), (int) obj.getImageViewWidth(), (int) obj.getImageViewHeight(), obj.getSpeed(), obj.getIslandofResidence(),this.tntburstsound);
                gameObjectsList.add(s);
            } else if (obj.getName().equals("Boss Orc")) {
                Boss_Orc s = new Boss_Orc();
                gameObjectsList.add(s);
            } else if (obj.getName().equals("Weapon Chest Sword")) {
                Weapon_Chest s = new Weapon_Chest(mainPane, obj.getPosition(), (int) obj.getImageViewWidth(), (int) obj.getImageViewHeight(), "Sword", obj.getIslandofResidence(), swordbutton, lancebutton,this.weaponchestsound);
                gameObjectsList.add(s);
            } else if (obj.getName().equals("Weapon Chest Lance")) {
                Weapon_Chest s = new Weapon_Chest(mainPane, obj.getPosition(), (int) obj.getImageViewWidth(), (int) obj.getImageViewHeight(), "Lance", obj.getIslandofResidence(), swordbutton, lancebutton,this.weaponchestsound);
                gameObjectsList.add(s);
            } else if (obj.getName().equals("Coin Chest")) {
                Coin_Chest s = new Coin_Chest(mainPane, obj.getPosition(), (int) obj.getImageViewWidth(), (int) obj.getImageViewHeight(), obj.getIslandofResidence(),this.coinchestsound);
                gameObjectsList.add(s);
            }
        }
        return new SaveFileReturn(gameObjectsList, islandList, player);
    }
}