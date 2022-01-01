package com.example.game;

import javafx.geometry.Pos;
import javafx.scene.image.ImageView;

import java.io.Serializable;

public abstract class Game_Objects implements Serializable {
    protected int width, height;
    private double speed;
    abstract void setPosition(Position position);
    abstract Position getPosition();
    public double getSpeed() {
        return speed;
    }
    abstract ImageView getImage();

    public abstract void collide(Hero hero);

    public abstract String getName();
    public abstract Island getIslandofResidence();
    public abstract double getImageViewWidth();
    public abstract double getImageViewHeight();

    @Override
    public String toString() {
        return getName();
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
