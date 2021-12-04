package com.example.game;

public class Floor_Loot_Coin implements Coins{
    private Boolean isCollected;
    private Position position;

    public Position getPosition() {
        return position;
    }

    Floor_Loot_Coin(){

    }

    public Boolean getIsCollected() {
        return isCollected;
    }

    public void collide(Player player){

    }
}
