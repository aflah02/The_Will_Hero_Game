package com.example.game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.File;

public class TNT extends gameObstacles{
    private Position position;

    @Override
    public Position getPosition() {
        return position;
    }

    private int timeToBurst;
    private Island islandofResidence;
    private Boolean isBurst;
    private int Radius;
    private double initpos;
    private double speed;
    String pathgif = "src/main/resources/com/example/game/images/tntgif.gif";

    private ImageView tnt;
    TNT(AnchorPane anchorPane, Position position, int width, int height, double speed, Island islandofResidence){
        this.tnt = new ImageView();
        this.speed = speed;
        this.position = position;
        this.initpos = position.getY();
        this.islandofResidence = islandofResidence;
        Image img = new Image(new File(pathgif).toURI().toString());
        tnt.setImage(img);
        tnt.setX(position.getX());
        tnt.setY(position.getY());
        tnt.setFitWidth(width);
        tnt.setFitHeight(height);
        anchorPane.getChildren().add(tnt);
    }
    public void setPositionY(double cord){
        tnt.setY(cord);
        position.setY(cord);
    }
    @Override
    public double getSpeed() {
        return speed;
    }
    public int getTimeToBurst() {
        return timeToBurst;
    }
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public Island getIslandofResidence() {
        return islandofResidence;
    }

    public void setinitpos(double pos) {
        this.initpos = initpos;
    }



    public ImageView getTnt() {
        return tnt;
    }

    public void Burst() {
        isBurst = true;
    }

    public void collide(Player player) {
    }

    public double getinitpos() {
        return initpos;
    }
}
