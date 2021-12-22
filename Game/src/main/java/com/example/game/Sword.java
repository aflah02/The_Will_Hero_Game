package com.example.game;

import javafx.scene.image.ImageView;

import java.io.File;

public class Sword extends Weapon{
    private String path = "src/main/resources/com/example/game/images/sword.png";

    Sword(){
        super("Sword");
        ImageView image = new ImageView(new File(path).toURI().toString());
        super.setImage(image);
        image.setRotate(160);
        image.setFitWidth(80);
        image.setFitHeight(20);
    }
    @Override
    void animate(){

    }
    @Override
    int getDamage() {
        return 0;
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
