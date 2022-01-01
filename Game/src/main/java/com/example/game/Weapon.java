package com.example.game;

import javafx.scene.image.ImageView;

import java.io.Serializable;

public abstract class Weapon implements Serializable {
    private transient ImageView image;
    private String name;
    private int damage;
    private int radius;
    private Boolean stunEffect;
    private Boolean canPierce;
    private int level;
    Weapon(String name){
        this.image = null;
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
    public int getDamage(){
        return this.damage;
    }
    abstract int setDamage();
    abstract int getRadius();
    public void increaseLevel(){
        level++;
        damage = level*150;
    }
    public int getLevel(){
        return this.level;
    }
    public ImageView getimage(){ return this.image; }
    public void setImage(ImageView image){ this.image = image;}
}
