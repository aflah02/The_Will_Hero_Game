package com.example.game;

import javafx.scene.image.ImageView;

public abstract class Orc extends Game_Objects{
    private int HitPoints;
    private int Damage;
    private int Coins;
    private Boolean Dead;
    protected Boolean isAboveIsland;

    public Boolean getAboveIsland() {
        return isAboveIsland;
    }

    public void setAboveIsland(Boolean aboveIsland) {
        isAboveIsland = aboveIsland;
    }

    public int getHitPoints(){
        return this.HitPoints;
    }
    public int getDamage(){
        return this.Damage;
    }
    public int getCoins(){
        return this.Coins;
    }
    public Boolean isDead(){
        return Dead;
    }
    public void setIsDead(){
        Dead = true;
    }
    public abstract ImageView getOrc();
    public abstract ImageView getImage();
    public abstract void setOrc(ImageView Orc);
    public abstract void setSpeed(double speed);
    public abstract Island getIslandofResidence();
    public abstract double getInitialPosition();
    public abstract void setInitialPosition(double pos);
}
