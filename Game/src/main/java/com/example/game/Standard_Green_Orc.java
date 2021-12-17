package com.example.game;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.File;

public class Standard_Green_Orc extends Orc{
    private String imagePath = "src/main/resources/com/example/game/images/troll_1.png";
    private double speed;
    private double initpos;
    private final Island islandofResidence;

    public Island getIslandofResidence() {
        return islandofResidence;
    }

    private ImageView standardGreenOrc;
    public Standard_Green_Orc(AnchorPane anchorPane, Position position, int width, int height,double speed,Island islandofResidence){
        createImage();
        this.speed = speed;
        standardGreenOrc.setX(position.getX());
        standardGreenOrc.setY(position.getY());
        standardGreenOrc.setFitWidth(width);
        standardGreenOrc.setFitHeight(height);
        anchorPane.getChildren().add(standardGreenOrc);
        this.initpos = position.getY();
        this.islandofResidence = islandofResidence;
    }
    @Override
    public void collide(Player player) {
    }
    public void createImage(){
        this.standardGreenOrc = new ImageView(new File(imagePath).toURI().toString());
    }

    @Override
    public ImageView getOrc() {
        return standardGreenOrc;
    }

    @Override
    public void setOrc(ImageView Orc) {
    }

    @Override
    public double getSpeed() {
        return speed;
    }

    @Override
    public void setSpeed(double speed) {
        this.speed = speed;
    }
    @Override
    public double getinitpos() {
        return initpos;
    }

    @Override
    public void setinitpos(double pos) {
        this.initpos = initpos;
    }
}
