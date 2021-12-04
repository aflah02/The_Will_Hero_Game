package com.example.game;

public abstract class Weapon {
    private int damage;
    private int radius;
    private Boolean stunEffect;
    private Boolean canPierce;
    private int level;
    Weapon(){

    }
    private void animate(){

    }
    abstract int getDamage();
    abstract int setDamage();
    abstract int getRadius();
    abstract int increaseLevel();
}
