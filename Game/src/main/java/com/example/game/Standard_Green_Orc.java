package com.example.game;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.File;

public class Standard_Green_Orc extends Orc{
    private String imagePath = "src/main/resources/com/example/game/images/troll_1.png";
    private double speed;
    private double initpos;
    private Island islandofResidence;
    private Position position;
    private AnchorPane anchorPane;
    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
        standardGreenOrc.setX(position.getX());
        standardGreenOrc.setY(position.getY());
    }

    public Island getIslandofResidence() {
        return islandofResidence;
    }

    @Override
    public void setIslandofResidence(Island island) {
        this.islandofResidence = island;
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
        this.position = position;
        this.islandofResidence = islandofResidence;
        this.isAboveIsland = true;
        this.anchorPane = anchorPane;
    }

    @Override
    public String getName(){
        return "Green Orc";
    }

    @Override
    public double getImageViewWidth() {
        return standardGreenOrc.getFitWidth();
    }

    @Override
    public double getImageViewHeight() {
        return standardGreenOrc.getFitHeight();
    }

    @Override
    public void collide(Hero hero){
//        if (this.standardGreenOrc.getX() == hero.getHero().getX()){
//            if (hero.getActiveWeapon() != null){
//                this.anchorPane.getChildren().remove(this);
//            }
//        }
        if (hero.getActiveWeapon() != null){
            this.anchorPane.getChildren().remove(this);
        }
//        this.standardGreenOrc.setX(this.standardGreenOrc.getX()+100);
    }

    public void createImage(){
        this.standardGreenOrc = new ImageView(new File(imagePath).toURI().toString());
    }

    @Override
    public ImageView getImage(){
        return standardGreenOrc;
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
    public double getInitialPosition() {
        return initpos;
    }

    @Override
    public void setInitialPosition(double pos) {
        this.initpos = initpos;
    }
}
