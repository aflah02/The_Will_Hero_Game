package com.example.game;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.File;

public class Standard_Green_Orc extends Orc{
    private String imagePath = "src/main/resources/com/example/game/images/StandardGreenOrc.png";
    private ImageView standardGreenOrc;
    public Standard_Green_Orc(AnchorPane anchorPane, Position position, int width, int height){
        createImage();
        standardGreenOrc.setX(position.getX());
        standardGreenOrc.setY(position.getY());
        standardGreenOrc.setFitWidth(width);
        standardGreenOrc.setFitHeight(height);
        anchorPane.getChildren().add(standardGreenOrc);
    }
    @Override
    public void collide(Player player) {

    }
    public void createImage(){
        this.standardGreenOrc = new ImageView(new File(imagePath).toURI().toString());
    }

}
