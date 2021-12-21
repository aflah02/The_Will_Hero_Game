package com.example.game;

public abstract class Weapon {
    private String name;
    private int damage;
    private int radius;
    private Boolean stunEffect;
    private Boolean canPierce;
    private int level;
    Weapon(String name){
        this.name = name;
        damage = 0;
        level = 0;
        stunEffect = false;
        canPierce = false;
        radius = 0;
    }
    public String getName(){
        return this.name;
    }
    abstract void animate();
    abstract int getDamage();
    abstract int setDamage();
    abstract int getRadius();
    public void increaseLevel(){
        level++;
        damage = level*150;
    }
    public int getLevel(){
        return this.level;
    }
}
