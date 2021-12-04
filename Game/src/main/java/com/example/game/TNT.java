package com.example.game;

public class TNT extends gameObstacles{
    private int timeToBurst;
    private Boolean isBurst;
    private int Radius;

    public int getTimeToBurst() {
        return timeToBurst;
    }

    public void Burst() {
        isBurst = true;
    }

    public void collide(Player player) {
    }
}
