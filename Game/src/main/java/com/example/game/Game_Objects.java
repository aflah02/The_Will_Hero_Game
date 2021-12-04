package com.example.game;

import java.io.Serializable;

public abstract class Game_Objects implements Serializable {
    private Position position;
    private int speed;

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getSpeed() {
        return speed;
    }

    public abstract void collide(Player player);
}
