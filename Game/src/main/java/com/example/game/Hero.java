package com.example.game;

import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;

public class Hero {
    private String coinpath = "src/main/resources/com/example/game/images/coin.png";

    private pandaHelmet helmet;
    private Position position;
    private ArrayList<Coins> currCoins;
    private double speed;
    private Boolean isRevived;
    private ImageView Hero;
    private String score;
    private Text scoreboard,coinboard;
    private Weapon activeWeapon,sword,lance;
    private Text swordt , lancet;

    public ImageView getHero() {
        return Hero;
    }

    public void setHero(ImageView hero) {
        Hero = hero;
    }

    Hero(AnchorPane anchorPane, Position position, int width, int height , double speed, Text lancet , Text swordt){
        this.swordt = swordt;
        this.lancet = lancet;
        this.sword = null;
        this.lance = null;
        this.currCoins = new ArrayList<>();
        helmet = new pandaHelmet(anchorPane, position, width, height);
        Hero = helmet.getPandaHelmet();
        anchorPane.getChildren().add(Hero);
        this.speed = speed;
        scoreboard = new Text();
        score = Integer.toString(0);
        scoreboard.setText(score);
        scoreboard.setFont(Font.font ("Verdana", 70));
        scoreboard.setFill(Color.WHITE);
        scoreboard.setX(410);
        scoreboard.setY(130);
        anchorPane.getChildren().add(scoreboard);
        ImageView coinimage = new ImageView(new Image(new File(coinpath).toURI().toString()));
        coinimage.setX(650);
        coinimage.setY(45);
        coinimage.setFitWidth(40);
        coinimage.setFitHeight(40);
        coinboard = new Text();
        String coinsval = Integer.toString(0);
        coinboard.setText(coinsval);
        coinboard.setFont(Font.font ("Verdana", 40));
        coinboard.setFill(Color.YELLOW);
        coinboard.setX(700);
        coinboard.setY(80);
        anchorPane.getChildren().add(coinimage);
        anchorPane.getChildren().add(coinboard);
        this.position = position;
    }

    public Weapon getsword(){
        return this.sword;
    }
    public Weapon getlance(){
        return this.lance;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void addCoins(Coins coins) {
        this.currCoins.add(coins);
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setRevived(Boolean revived) {
        isRevived = revived;
    }

    public ArrayList<Coins> getCurrCoins() {
        return currCoins;
    }

    public double getSpeed() {
        return speed;
    }

    public Boolean getRevived() {
        return isRevived;
    }

    public Position getPosition() {
        return this.position;
    }

    public void animate(){
        helmet.animate();
    }


    public void setScore(int score) {
        this.score = Integer.toString(score);
        scoreboard.setText(this.score);
    }

    public void updatecoins() {
        coinboard.setText(Integer.toString(currCoins.size()));
    }

    public void setWeapon(Weapon weapon) {
        if(weapon.getName().equals("Sword")){
            if(this.sword==null){
                this.sword = weapon;
                this.sword.increaseLevel();
                this.swordt.setText(Integer.toString(this.sword.getLevel()));
            }
            else{
                this.sword.increaseLevel();
                this.swordt.setText(Integer.toString(this.sword.getLevel()));
            }
            this.activeWeapon = this.sword;
        }
        if(weapon.getName().equals("Lance")){
            if(this.lance==null){
                this.lance = weapon;
                this.lance.increaseLevel();
                this.lancet.setText(Integer.toString(this.lance.getLevel()));
            }
            else{
                this.lance.increaseLevel();
                this.lancet.setText(Integer.toString(this.lance.getLevel()));
            }
            this.activeWeapon = this.lance;
        }
        System.out.println("hero got" + this.activeWeapon.getName());
        animate(this.activeWeapon);
    }
    public void setActiveWeapon(Weapon weapon){
        if(weapon!=null){
            this.activeWeapon=weapon;

            System.out.println("hero changed weapon to" + this.activeWeapon.getName());
            animate(this.activeWeapon);
        }
    }
    public void animate(Weapon wp){
        if(wp==null){
            return;
        }

    }
    public void strike(){
        if(this.activeWeapon==null){
            return;
        }
    }
}
