package com.example.game;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.File;

public class Standard_Red_Orc extends Orc{
    private String imagePath = "src/main/resources/com/example/game/images/StandardRedOrc.png";
    private ImageView standardRedOrc;
    private double speed;
    private double initpos;
    public Standard_Red_Orc(AnchorPane anchorPane, Position position, int width, int height,double speed){
        createImage();
        this.speed = speed;
        standardRedOrc.setX(position.getX());
        standardRedOrc.setY(position.getY());
        standardRedOrc.setFitWidth(width);
        standardRedOrc.setFitHeight(height);
        anchorPane.getChildren().add(standardRedOrc);
        this.initpos = position.getY();
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
    public double getinitpos() {
        return initpos;
    }

    @Override
    public void setinitpos(double pos) {
        this.initpos = initpos;
    }
}
