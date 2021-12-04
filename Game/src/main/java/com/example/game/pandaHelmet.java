package com.example.game;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.File;

public class pandaHelmet {
    private ImageView pandaHelmet;
    private String helmetImage = "src/main/resources/com/example/game/images/panda.png";
    public pandaHelmet(AnchorPane anchorPane, Position position, int width, int height){
        createImage(helmetImage);
        pandaHelmet.setX(position.getX());
        pandaHelmet.setY(position.getY());
        pandaHelmet.setFitWidth(width);
        pandaHelmet.setFitHeight(height);
    }

    public ImageView getPandaHelmet() {
        return pandaHelmet;
    }

    public void createImage(String path){
        this.pandaHelmet = new ImageView(new File(path).toURI().toString());
    }
}
