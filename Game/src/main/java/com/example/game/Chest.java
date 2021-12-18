package com.example.game;

import javafx.scene.image.ImageView;

public abstract class Chest extends Game_Objects{
    private Boolean isOpen;

    public Boolean getOpen() {
        return isOpen;
    }

    public void setOpen(Boolean open) {
        isOpen = open;
    }

    public abstract ImageView getImage();

    public abstract Island getIslandofResidence();
}
