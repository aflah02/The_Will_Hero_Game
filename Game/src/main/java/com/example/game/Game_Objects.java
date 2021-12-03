package com.example.game;

public abstract class Game_Objects {
    private Position position;
    private int speed;

    public Game_Objects(Position position, int speed) {
        this.position = position;
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public abstract void collide(Player player);
}