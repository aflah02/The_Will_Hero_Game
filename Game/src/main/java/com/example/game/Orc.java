package com.example.game;

public abstract class Orc extends Game_Objects{
    private int HitPoints;
    private int Damage;
    private int Coins;
    private Boolean Dead;
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
}
