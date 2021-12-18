package com.example.game;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

public class Island implements Serializable {
    private Position position;
    private int size;
    private ImageView Island;
    private double speed;
    private String islandType;
    String treeImage = "src/main/resources/com/example/game/images/Tree7.png";
    ImageView treeImageView = new ImageView(new File(treeImage).toURI().toString());
    String ruinImage = "src/main/resources/com/example/game/images/ruins4.png";
    ImageView ruinImageView = new ImageView(new File(ruinImage).toURI().toString());
    String spruceImage = "src/main/resources/com/example/game/images/Spruce1.png";
    ImageView spruceImageView = new ImageView(new File(spruceImage).toURI().toString());

    public String getIslandType() {
        return islandType;
    }

    String islandSmall = "src/main/resources/com/example/game/images/T_Islands_07.png";
    String islandMedium = "src/main/resources/com/example/game/images/T_Islands_09.png";
    String islandLarge = "src/main/resources/com/example/game/images/T_Islands_01.png";
    private final ArrayList<ImageView> islandObjects = new ArrayList<>();

    public Island(String islandType, AnchorPane anchorPane, Position position, int width, int height , double Speed){
        this.islandType = islandType;
        if (islandType.equals("Small")){
            createImage(islandSmall);
            treeImageView.setX(position.getX()+50);
            treeImageView.setY(position.getY()-170);
            treeImageView.setFitHeight(170);
            treeImageView.setFitWidth(50);
            islandObjects.add(treeImageView);
        }
        else if (islandType.equals("Medium")){
            createImage(islandMedium);
            ruinImageView.setX(position.getX()+50);
            ruinImageView.setY(position.getY()-100);
            ruinImageView.setFitHeight(100);
            ruinImageView.setFitWidth(30);
            islandObjects.add(ruinImageView);
        }
        else{
            createImage(islandLarge);
            spruceImageView.setX(position.getX()+75);
            spruceImageView.setY(position.getY()-200);
            spruceImageView.setFitHeight(200);
            spruceImageView.setFitWidth(50);
            treeImageView.setX(position.getX()+100);
            treeImageView.setY(position.getY()-170);
            treeImageView.setFitHeight(170);
            treeImageView.setFitWidth(50);
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

    private void setimages(AnchorPane anchorPane){
        for (ImageView image: this.islandObjects){
            double h = image.getFitHeight();
            double w = image.getFitWidth();
            double ih = Island.getFitHeight();
            double iw = Island.getFitWidth();
            image.setY(Island.getY() - ih/2 -h/2);
            anchorPane.getChildren().add(image);
        }
    }

    public void setPosition(Position position) {
        this.position = position;
        Island.setX(position.getX());
        Island.setY(position.getY());
        treeImageView.setX(position.getX()+100);
        treeImageView.setY(position.getY()-170);
        treeImageView.setFitHeight(170);
        treeImageView.setFitWidth(50);
        ruinImageView.setX(position.getX()+50);
        ruinImageView.setY(position.getY()-100);
        ruinImageView.setFitHeight(100);
        ruinImageView.setFitWidth(30);
        spruceImageView.setX(position.getX()+75);
        spruceImageView.setY(position.getY()-200);
        spruceImageView.setFitHeight(200);
        spruceImageView.setFitWidth(50);
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
        this.Island = new ImageView(new File(path).toURI().toString());
    }
}
