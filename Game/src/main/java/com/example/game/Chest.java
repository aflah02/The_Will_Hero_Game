package com.example.game;

public abstract class Chest extends Game_Objects{
    private Boolean isOpen;

    public Boolean getOpen() {
        return isOpen;
    }

    public void setOpen(Boolean open) {
        isOpen = open;
    }
}
