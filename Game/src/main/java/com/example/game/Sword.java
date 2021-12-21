package com.example.game;

public class Sword extends Weapon{

    Sword(){
        super("Sword");
    }
    @Override
    void animate(){
    }
    @Override
    int getDamage() {
        return 0;
    }

    @Override
    int setDamage() {
        return 0;
    }

    @Override
    int getRadius() {
        return 0;
    }

}
