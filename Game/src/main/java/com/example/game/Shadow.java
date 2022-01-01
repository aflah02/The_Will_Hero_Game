package com.example.game;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class Shadow {
    private Position position;
    private transient ImageView shadowimg;
    private String Path = "src/main/resources/com/example/game/images/panda.png";

    Shadow(Position position,int width,int height){
        this.position = position;
        shadowimg = new ImageView(new File(Path).toURI().toString());
        shadowimg.setX(position.getX());
        shadowimg.setY(position.getY());
        shadowimg.setOpacity(0.5);
        shadowimg.setFitHeight(height);
        shadowimg.setFitWidth(width);
    }

    public ImageView getshadow(){
        return this.shadowimg;
    }

    public void setposition(Position position){
        this.position = position;
        shadowimg.setX(position.getX());
        shadowimg.setY(position.getY());
    }

    public Position getposition(){
        return this.position;
    }
}
