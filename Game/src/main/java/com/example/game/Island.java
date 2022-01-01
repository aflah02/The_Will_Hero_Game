package com.example.game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

public class Island implements Serializable {
    private Position position , initial_position;
    private int size;
    private int width, height;
    private transient ImageView Island;
    private transient Image island_image;
    private double speed;
    private String islandType;
    private String treeImage = "src/main/resources/com/example/game/images/Tree7.png";
    private transient ImageView treeImageView = new ImageView(new File(treeImage).toURI().toString());
    private String ruinImage = "src/main/resources/com/example/game/images/ruins4.png";
    private transient ImageView ruinImageView = new ImageView(new File(ruinImage).toURI().toString());
    private String spruceImage = "src/main/resources/com/example/game/images/Spruce1.png";
    private transient ImageView spruceImageView = new ImageView(new File(spruceImage).toURI().toString());

    public String getIslandType() {
        return islandType;
    }

    private String islandSmall = "src/main/resources/com/example/game/images/T_Islands_07.png";
    private String islandMedium = "src/main/resources/com/example/game/images/T_Islands_09.png";
    private String islandLarge = "src/main/resources/com/example/game/images/T_Islands_01.png";
    private final transient ArrayList<ImageView> islandObjects = new ArrayList<>();

    public Island(String islandType, AnchorPane anchorPane, Position position, int width, int height , double Speed){
        this.width = width;
        this.height = height;
        this.initial_position = new Position(position.getX(), position.getY());
        this.islandType = islandType;
        if (islandType.equals("Small")){
            createImage(islandSmall);
            treeImageView.setX(position.getX()+70);
            treeImageView.setY(position.getY()-250);
            treeImageView.setFitHeight(200);
            treeImageView.setFitWidth(70);
            islandObjects.add(treeImageView);
        }
        else if (islandType.equals("Medium")){
            createImage(islandMedium);
            ruinImageView.setX(position.getX()+250);
            ruinImageView.setY(position.getY()-100);
            ruinImageView.setFitHeight(150);
            ruinImageView.setFitWidth(30);
            islandObjects.add(ruinImageView);
        }
        else{
            createImage(islandLarge);
            spruceImageView.setX(position.getX()+275);
            spruceImageView.setY(position.getY()-200);
            spruceImageView.setFitHeight(200);
            spruceImageView.setFitWidth(50);
            treeImageView.setX(position.getX()+70);
            treeImageView.setY(position.getY()-250);
            treeImageView.setFitHeight(200);
            treeImageView.setFitWidth(70);
            islandObjects.add(treeImageView);
            islandObjects.add(spruceImageView);
        }
        Island.setX(position.getX());
        Island.setY(position.getY());
        Island.setFitWidth(width);
        Island.setFitHeight(height);
        this.speed = Speed;
        this.position = position;
        setimages(anchorPane);
        anchorPane.getChildren().add(Island);

    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    private void setimages(AnchorPane anchorPane){
        for (ImageView image: this.islandObjects){
            double h = image.getFitHeight();
            double w = image.getFitWidth();
            double ih = Island.getFitHeight();
            double iw = Island.getFitWidth();
            image.setY(Island.getY() - ih/2 - h/2);
            anchorPane.getChildren().add(image);
        }
    }

    public ImageView getIsland() {
        return Island;
    }

    public void setPosition(Position position) {
        this.initial_position.setX(position.getX());
        this.position = position;
        Island.setX(position.getX());
        this.setPositionY(position.getY());
        treeImageView.setX(position.getX()+70);
        ruinImageView.setX(position.getX()+250);
        spruceImageView.setX(position.getX()+275);
        /*
        Island.setY(position.getY());
        treeImageView.setY(position.getY()-250);
        treeImageView.setFitHeight(200);
        treeImageView.setFitWidth(70);
        ruinImageView.setY(position.getY()-100);
        ruinImageView.setFitHeight(150);
        ruinImageView.setFitWidth(30);
        spruceImageView.setY(position.getY()-200);
        spruceImageView.setFitHeight(200);
        spruceImageView.setFitWidth(50);
        */
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setPositionY(double cord){
        Island.setY(cord);
        position.setY(cord);
        for (ImageView image: this.islandObjects){
            double h = image.getFitHeight();
            double ih = Island.getFitHeight();
            image.setY(position.getY() - ih/2 - h/2);
        }
    }

    public Position getinitial(){ return this.initial_position;}

    public double getSpeed() {
        return speed;
    }

    public Position getPosition() {
        return position;
    }

    public int getSize() {
        return size;
    }

    public void createImage(String path){
        this.island_image = new Image(new File(path).toURI().toString());
        this.Island = new ImageView(island_image);
        Island.setPreserveRatio(false);
    }

    public double getIslandImageViewHeight(){
        return this.Island.getFitHeight();
    }

    public double getIslandImageViewWidth(){
        return this.Island.getFitWidth();
    }

    public String getName(){
        return this.islandType;
    }
    @Override
    public String toString() {
        return "Island";
    }
}
