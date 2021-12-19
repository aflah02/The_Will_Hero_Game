package com.example.game;

import java.io.Serializable;

public class Player implements Serializable {
    private int currentScore;
    private Hero hero;

    Player(){

    }

    public int getCurrentScore() {
        return currentScore;
    }

    public Hero getHero() {
        return hero;
    }

    public void pauseGame(){

    }

    public void loadGame(){

    }

    public void restartGame(){

    }

    public double getSpeed(){
        return hero.getSpeed();
    }

    public void setSpeed(int speed){
        hero.setSpeed(speed);
    }

    public Position getPosition(){
        return hero.getPosition();
    }

    public void addCoins(Coins coin){
        hero.addCoins(coin);
    }

    public void interact(Game_Objects object){

    }

    public void setScore(int score) {

    }
}
