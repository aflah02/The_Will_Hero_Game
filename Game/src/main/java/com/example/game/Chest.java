package com.example.game;

import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.ArrayList;

public abstract class Chest extends Game_Objects{
    private Boolean isOpen;
    public ArrayList<Image> chestAnimations;

    public Boolean getOpen() {
        return isOpen;
    }

    public void setOpen(Boolean open) {
        isOpen = open;
    }

    public abstract ImageView getImage();

    public abstract Island getIslandofResidence();

    public abstract void setSpeed(double v);

    public abstract void setPositionY(double v);

    public void animateChest() {
        Chest chest = this;
        Transition animation = new Transition() {
            {setCycleDuration(Duration.millis(500));}
            @Override
            protected void interpolate(double fraction) {
                int index = (int) (fraction*(chestAnimations.size()-1));
                chest.getImage().setImage(chestAnimations.get(index));
            }
        };
        animation.play();
        animation.setCycleCount(1);

    }
}
