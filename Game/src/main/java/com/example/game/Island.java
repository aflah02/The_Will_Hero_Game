package com.example.game;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import java.io.File;
import java.io.Serializable;

public class Island implements Serializable {
    private Position position;
    private int size;
    private ImageView Island;
    public Island(String path, AnchorPane anchorPane, Position position, int width, int height){
        createImage(path);
        Island.setX(position.getX());
        Island.setY(position.getY());
        Island.setFitWidth(width);
        Island.setFitHeight(height);
        anchorPane.getChildren().add(Island);
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
