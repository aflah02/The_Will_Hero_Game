package com.example.game;

public abstract class gameObstacles extends Game_Objects{
    private int damage;
    private int radius;

    public int getDamage() {
        return damage;
    }

    public int getRadius() {
        return radius;
    }

    public abstract void setPositionY(double v);
}
