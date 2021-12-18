package com.example.game;

import javafx.geometry.Pos;

import java.io.Serializable;

public abstract class Game_Objects implements Serializable {
    private double speed;
    abstract void setPosition(Position position);
    abstract Position getPosition();
    public double getSpeed() {
        return speed;
    }

    public abstract void collide(Player player);
}
