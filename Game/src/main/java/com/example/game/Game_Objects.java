package com.example.game;

import javafx.geometry.Pos;
import javafx.scene.image.ImageView;

import java.io.Serializable;

public abstract class Game_Objects implements Serializable {
    private double speed;
    abstract void setPosition(Position position);
    abstract Position getPosition();
    public double getSpeed() {
        return speed;
    }
    abstract ImageView getImage();

    public abstract void collide(Hero hero);

    public abstract String getName();

    public abstract double getImageViewWidth();
    public abstract double getImageViewHeight();
}
