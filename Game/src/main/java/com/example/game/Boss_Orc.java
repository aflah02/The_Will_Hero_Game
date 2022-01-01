package com.example.game;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.io.Serializable;

public class Boss_Orc extends Orc implements Serializable {
    private String imagePath = "src/main/resources/com/example/game/images/Boss.png";
    private double speed;
    private double initpos;
    private Island islandofResidence;
    private Position position;
    private transient AnchorPane anchorPane;
    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
        BossOrc.setX(position.getX());
        BossOrc.setY(position.getY());
    }

    public Island getIslandofResidence() {
        return islandofResidence;
    }

    @Override
    public void setIslandofResidence(Island island) {
        this.islandofResidence = island;
    }

    private transient ImageView BossOrc;
    public Boss_Orc(AnchorPane anchorPane, Position position, int width, int height, double speed, Island islandofResidence, MediaPlayer diesound,int id){
        super(diesound,id);
        this.width = width;
        this.height = height;
        System.out.println("Boss Orc Built");
        createImage();
        this.speed = speed;
        BossOrc.setX(position.getX());
        BossOrc.setY(position.getY());
        BossOrc.setFitWidth(width);
        BossOrc.setFitHeight(height);
        anchorPane.getChildren().add(BossOrc);
        this.initpos = position.getY();
        this.position = position;
        this.islandofResidence = islandofResidence;
        this.isAboveIsland = true;
        this.anchorPane = anchorPane;
    }

    @Override
    public String getName(){
        return "Boss Orc";
    }

    @Override
    public double getImageViewWidth() {
        return BossOrc.getFitWidth();
    }

    @Override
    public double getImageViewHeight() {
        return BossOrc.getFitHeight();
    }

    @Override
    public void collide(Hero hero){
//        if (this.BossOrc.getX() == hero.getHero().getX()){
//            if (hero.getActiveWeapon() != null){
//                this.anchorPane.getChildren().remove(this);
//            }
//        }
        if (hero.getActiveWeapon() != null){
            this.anchorPane.getChildren().remove(this);
        }
//        this.BossOrc.setX(this.BossOrc.getX()+100);
    }

    public void createImage(){
        this.BossOrc = new ImageView(new File(imagePath).toURI().toString());
    }

    @Override
    public ImageView getImage(){
        return BossOrc;
    }
    @Override
    public ImageView getOrc() {
        return BossOrc;
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
    public double getInitialPosition() {
        return initpos;
    }

    @Override
    public void setInitialPosition(double pos) {
        this.initpos = initpos;
    }
}
