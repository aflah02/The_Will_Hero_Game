package com.example.game;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.File;

public class Standard_Red_Orc extends Orc{
    private ImageView standardRedOrc;
    private double speed;
    private double initpos;
    private Island islandofResidence;

    public Island getIslandofResidence() {
        return islandofResidence;
    }

    public Standard_Red_Orc(AnchorPane anchorPane, Position position, int width, int height, double speed, Island islandofResidence){
        createImage();
        this.speed = speed;
        standardRedOrc.setX(position.getX());
        standardRedOrc.setY(position.getY());
        standardRedOrc.setFitWidth(width);
        standardRedOrc.setFitHeight(height);
        anchorPane.getChildren().add(standardRedOrc);
        this.initpos = position.getY();
        this.position = position;
        this.islandofResidence = islandofResidence;
    }

    public ImageView getOrc() {
        return standardRedOrc;
    }

    public void setOrc(ImageView standardRedOrc) {
        this.standardRedOrc = standardRedOrc;
    }

    @Override
    public void collide(Player player) {

    }
    public void createImage(){
        String imagePath = "src/main/resources/com/example/game/images/StandardRedOrc.png";
        this.standardRedOrc = new ImageView(new File(imagePath).toURI().toString());
    }

    @Override
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    @Override
    public double getSpeed(){
        return this.speed;
    }

    @Override
    public double getInitialPosition() {
        return initpos;
    }

    @Override
    public void setInitialPosition(double pos) {
        this.initpos = initpos;
    }
}
