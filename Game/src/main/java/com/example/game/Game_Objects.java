package com.example.game;

import java.io.Serializable;

public abstract class Game_Objects implements Serializable {
    protected Position position;
    private double speed;

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public double getSpeed() {
        return speed;
    }

    public abstract void collide(Player player);
}
