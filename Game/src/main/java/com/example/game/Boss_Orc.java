package com.example.game;

import javafx.scene.image.ImageView;

public class Boss_Orc extends Orc{
    private double speed,initpos;
    private Island islandofResidence;

    public Island getIslandofResidence() {
        return islandofResidence;
    }
    @Override
    public void collide(Player player) {

    }
    public void activate(){

    }

    @Override
    public ImageView getOrc() {
        return null;
    }

    @Override
    public void setOrc(ImageView Orc) {

    }

    @Override
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    @Override
    public double getSpeed() {
        return speed;
    }
    @Override
    public double getinitpos() {
        return initpos;
    }

    @Override
    public void setinitpos(double pos) {
        this.initpos = initpos;
    }
}
