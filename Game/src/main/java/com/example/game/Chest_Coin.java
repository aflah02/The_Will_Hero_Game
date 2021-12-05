package com.example.game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.File;

public class Chest_Coin implements Coins{

    Chest_Coin(){
    }
    private Boolean isCollected;

    public Boolean getIsCollected() {
        return isCollected;
    }
    public void collide(Player player){
    }
}
