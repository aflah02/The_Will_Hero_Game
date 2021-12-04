package com.example.game;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.File;

public class Standard_Red_Orc extends Orc{
    private String imagePath = "src/main/resources/com/example/game/images/StandardRedOrc.png";
    private ImageView standardRedOrc;
    public Standard_Red_Orc(AnchorPane anchorPane, Position position, int width, int height){
        createImage();
        standardRedOrc.setX(position.getX());
        standardRedOrc.setY(position.getY());
        standardRedOrc.setFitWidth(width);
        standardRedOrc.setFitHeight(height);
        anchorPane.getChildren().add(standardRedOrc);
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
}
