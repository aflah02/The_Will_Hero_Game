package com.example.game;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import java.io.File;
import java.io.Serializable;

public class Island implements Serializable {
    private Position position;
    private int size;
    private ImageView Island;
    private double speed;
    public Island(String path, AnchorPane anchorPane, Position position, int width, int height , double Speed){
        createImage(path);
        Island.setX(position.getX());
        Island.setY(position.getY());
        Island.setFitWidth(width);
        Island.setFitHeight(height);
        anchorPane.getChildren().add(Island);
        this.speed = Speed;
        this.position = position;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setPositionY(double cord){
        Island.setY(cord);
        position.setY(cord);
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
