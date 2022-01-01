package com.example.game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class Floor_Loot_Coin extends Game_Objects implements Coins{
    private Boolean isCollected;
    private Position position;
    private String coinpath = "src/main/resources/com/example/game/images/coin.png";
    private transient ImageView coinimg;

    public Position getPosition() {
        return position;
    }

    Floor_Loot_Coin(Position position){
        this.height = 30;
        this.width = 30;
        this.position = position;
        isCollected = false;
        coinimg = new ImageView(new File(coinpath).toURI().toString());
        coinimg.setX(position.getX());
        coinimg.setY(position.getY());
    }

    public Boolean getIsCollected() {
        return isCollected;
    }

    @Override
    ImageView getImage() {
        return this.coinimg;
    }

    @Override
    public double getImageViewHeight() {
        return this.height;
    }

    @Override
    public double getImageViewWidth() {
        return this.width;
    }

    @Override
    public double getSpeed() {
        return 0;
    }

    @Override
    public Island getIslandofResidence() {
        return null;
    }

    @Override
    public String getName() {
        return "Floor Coin";
    }

    @Override
    public void collide(Hero hero) {
        if(!isCollected){
            isCollected = true;
            System.out.println("collecting Coin");
            hero.addCoins(this);
            hero.updatecoins();
        }
    }
    @Override
    void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public void setWidth(int width) {
        super.setWidth(width);
    }

    @Override
    public int getHeight() {
        return super.getHeight();
    }

    @Override
    public void setHeight(int height) {
        super.setHeight(height);
    }
}
