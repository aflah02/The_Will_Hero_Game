package com.example.game;

import java.io.Serializable;

public class Weapon_Chest extends Chests implements Serializable {
    Weapon weapon;
    public Weapon_Chest(Position position, int speed, Weapon weapon) {
        super(position, speed);
        this.weapon = weapon;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    @Override
    public void collide(Player player) {

    }
}
