package com.example.game;

public abstract class Orc extends Game_Objects{
    int hitPoints;
    int damage;
    int coins;
    Boolean dead;

    public Orc(Position position, int speed, int hitPoints, int damage, int coins) {
        super(position, speed);
        this.hitPoints = hitPoints;
        this.damage = damage;
        this.coins = coins;
        this.dead = false;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public int getDamage() {
        return damage;
    }

    public int getCoins() {
        return coins;
    }

    public boolean isDead() {
        return this.dead;
    }

    public void setIsDead() {
        this.dead = true;
    }
}