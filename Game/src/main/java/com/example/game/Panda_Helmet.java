package com.example.game;

public class Panda_Helmet {
    private Weapon activeWeapon;
    private Weapon passiveWeapon;
    Panda_Helmet(){

    }
    public Weapon getActiveWeapon() {
        return activeWeapon;
    }

    public void setActiveWeapon(Weapon activeWeapon) {
        this.activeWeapon = activeWeapon;
    }

    public Weapon getPassiveWeapon() {
        return passiveWeapon;
    }

    public void setPassiveWeapon(Weapon passiveWeapon) {
        this.passiveWeapon = passiveWeapon;
    }
}
