package com.example.game;

import javafx.scene.image.ImageView;

import java.io.File;

public class Lance extends Weapon{
    private String path = "src/main/resources/com/example/game/images/lance.png";

    Lance(){
        super("Lance");
        ImageView image = new ImageView(new File(path).toURI().toString());
        super.setImage(image);
        image.setRotate(25);
        image.setFitWidth(80);
        image.setFitHeight(40);
    }
    @Override
    void animate(){

    }

    @Override
    int setDamage() {
        return 0;
    }

    @Override
    int getRadius() {
        return 0;
    }

}
