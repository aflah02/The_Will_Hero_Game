package com.example.game;

public abstract class Chests extends Game_Objects{
    Boolean isOpen;
    public Chests(Position position, int speed) {
        super(position, speed);
        this.isOpen = false;
    }

    public void setOpen() {
        isOpen = true;
    }

    public Boolean getOpen() {
        return isOpen;
    }
}
