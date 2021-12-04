package com.example.game;

import java.util.ArrayList;

public class Coin_Chest extends Chest{
    private ArrayList<Coins> coins;

    public ArrayList<Coins> getCoins() {
        return coins;
    }

    @Override
    public void collide(Player player) {

    }
}
