package com.example.game;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class Standard_Red_Orc extends Orc{
    private transient ImageView standardRedOrc;
    private double speed;
    private double initpos;
    private Island islandofResidence;
    private Position position;

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
        standardRedOrc.setX(position.getX());
        standardRedOrc.setY(position.getY());
    }
    public Island getIslandofResidence() {
        return islandofResidence;
    }

    @Override
    public void setIslandofResidence(Island island) {
        this.islandofResidence = island;
    }

    public Standard_Red_Orc(AnchorPane anchorPane, Position position, int width, int height, double speed, Island islandofResidence, MediaPlayer diesound,int id){
        super(diesound,id);
        createImage();
        this.width = width;
        this.height = height;
        this.speed = speed;
        standardRedOrc.setX(position.getX());
        standardRedOrc.setY(position.getY());
        standardRedOrc.setFitWidth(width);
        standardRedOrc.setFitHeight(height);
        anchorPane.getChildren().add(standardRedOrc);
        this.initpos = position.getY();
        this.position = position;
        this.islandofResidence = islandofResidence;
        this.isAboveIsland = true;
        this.setHitPoints(50);
    }
    @Override
    public String getName(){
        return "Red Orc";
    }

    public ImageView getOrc() {
        return standardRedOrc;
    }

    @Override
    public ImageView getImage(){
        return standardRedOrc;
    }

    public void setOrc(ImageView standardRedOrc) {
        this.standardRedOrc = standardRedOrc;
    }

    @Override
    public void collide(Hero hero) {
        if (hero.getActiveWeapon() != null){
            System.out.println("not null");
            this.setHitPoints(this.getHitPoints() - hero.getActiveWeapon().getDamage());
            if(this.getHitPoints()<=0){
                this.animate();
            }
        }
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

    @Override
    public double getImageViewWidth() {
        return standardRedOrc.getFitWidth();
    }

    @Override
    public double getImageViewHeight() {
        return standardRedOrc.getFitHeight();
    }
}
