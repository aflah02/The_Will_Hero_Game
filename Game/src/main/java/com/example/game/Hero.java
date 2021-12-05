package com.example.game;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

public class Hero {
    private pandaHelmet helmet;
    private Position position;
    private ArrayList<Coins> currCoins;
    private double speed;
    private Boolean isRevived;
    private ImageView Hero;

    public ImageView getHero() {
        return Hero;
    }

    public void setHero(ImageView hero) {
        Hero = hero;
    }

    Hero(AnchorPane anchorPane, Position position, int width, int height , double speed){
        helmet = new pandaHelmet(anchorPane, position, width, height);
        Hero = helmet.getPandaHelmet();
        anchorPane.getChildren().add(Hero);
        this.speed = speed;
    }
    public void setPosition(Position position) {
        this.position = position;
    }

    public void addCoins(Coins coins) {
        this.currCoins.add(coins);
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setRevived(Boolean revived) {
        isRevived = revived;
    }

    public ArrayList<Coins> getCurrCoins() {
        return currCoins;
    }

    public double getSpeed() {
        return speed;
    }

    public Boolean getRevived() {
        return isRevived;
    }

    public Position getPosition() {
        return new Position(2,2);
    }


}
