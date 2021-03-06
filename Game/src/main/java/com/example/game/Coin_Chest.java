package com.example.game;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;

public class Coin_Chest extends Chest{
    private ArrayList<Chest_Coin> coins;
    String path1 = "src/main/resources/com/example/game/images/coinchest0.png";
    String path2 = "src/main/resources/com/example/game/images/coinchest1.png";
    String path3 = "src/main/resources/com/example/game/images/coinchest2.png";
    String path4 = "src/main/resources/com/example/game/images/coinchest3.png";
    String path5 = "src/main/resources/com/example/game/images/coinchest4.png";
    String path6 = "src/main/resources/com/example/game/images/coinchest5.png";
    String path7 = "src/main/resources/com/example/game/images/coinchest6.png";
    private transient MediaPlayer chestopen;
    private final transient ImageView chest;
    private boolean  isopen;
    private Position position;
    public String[] imagePaths;

    Island islandOfResidence;
    private double speed;
    Coin_Chest(AnchorPane anchorPane, Position position, int width, int height, Island islandOfResidence,MediaPlayer chestopen, Boolean isopen){
        this.chestopen = chestopen;
        this.isopen = isopen;
        this.width = width;
        this.height = height;
        coins = new ArrayList<>();
        int max = 80;
        int min = 10;
        int randomcoins = (int) (Math.random()*(max - min + 1) + min);
        for(int i=0;i<randomcoins;i++){
            Coins coin = new Chest_Coin();
            coins.add((Chest_Coin) coin);
        }
        this.imagePaths = new String[]{path1, path2, path3, path4, path5, path6, path7};
        chestAnimations = new ArrayList<>();
        this.islandOfResidence = islandOfResidence;
        for (String path: imagePaths){
            chestAnimations.add(new Image(new File(path).toURI().toString()));
        }
        this.chest = new ImageView();
        Image img = new Image(new File(path1).toURI().toString());
        chest.setImage(img);
        chest.setX(position.getX());
        chest.setY(position.getY());
        chest.setFitWidth(width);
        chest.setFitHeight(height);
        if (isopen){
            animateChest();
        }
        anchorPane.getChildren().add(chest);
        this.position = position;
        this.speed = islandOfResidence.getSpeed();
    }

    @Override
    public void collide(Hero hero) {
        if(!isopen){
            chestopen.play();
            chestopen.seek(Duration.ZERO);
            isopen = true;
            animateChest();
            for(Coins coin: coins){
                hero.addCoins(coin);
            }
            hero.updatecoins();
        }

    }

    @Override
    public String getName(){
        return "Coin_Chest";
    }
    @Override
    public ImageView getImage() {
        return this.chest;
    }

    @Override
    public Island getIslandofResidence() {
        return this.islandOfResidence;
    }

    @Override
    public void setSpeed(double v) {
        this.speed = v;
    }

    @Override
    public void setPositionY(double v) {
        this.position.setY(v);
        this.chest.setY(v);
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
        chest.setX(position.getX());
        chest.setY(position.getY());
    }
    @Override
    public Position getPosition() {
        return position;
    }

    public ArrayList<Chest_Coin> getCoins() {
        return coins;
    }

    @Override
    public double getImageViewWidth() {
        return chest.getFitWidth();
    }

    @Override
    public double getImageViewHeight() {
        return chest.getFitHeight();
    }
    @Override
    public Boolean getOpen(){
        return this.isopen;
    }
}
