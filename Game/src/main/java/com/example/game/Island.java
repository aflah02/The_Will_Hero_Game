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
    ArrayList<ImageView> objects;
    public Island(String path, AnchorPane anchorPane, Position position, int width, int height , double Speed , ArrayList<ImageView>objects){
        createImage(path);
        Island.setX(position.getX());
        Island.setY(position.getY());
        Island.setFitWidth(width);
        Island.setFitHeight(height);
        this.speed = Speed;
        this.position = position;
        this.objects = objects;
        setimages(anchorPane);
        anchorPane.getChildren().add(Island);
    }

    private void setimages(AnchorPane anchorPane){
        for (ImageView image: this.objects){
            double h = image.getFitHeight();
            double w = image.getFitWidth();
            double ih = Island.getFitHeight();
            double iw = Island.getFitWidth();
            image.setY(Island.getY() - ih/2 -h/2);
            anchorPane.getChildren().add(image);
        }
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setPositionY(double cord){
        Island.setY(cord);
        position.setY(cord);
        for (ImageView image: this.objects){
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
