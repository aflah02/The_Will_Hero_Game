package com.example.game;

import javafx.animation.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

public class Hero implements Serializable {
    private String coinpath = "src/main/resources/com/example/game/images/coin.png";
    private String deathpath = "src/main/resources/com/example/game/images/death.png";
    private Helmet helmet;
    private Position position;
    private ArrayList<Coins> currCoins;
    private double speed;
    private Boolean isRevived;
    private transient ImageView Hero;
    private final transient ImageView deathview;
    private String score;
    private Text scoreboard,coinboard;
    private Weapon activeWeapon,sword,lance;
    private Text swordt , lancet;
    private AnchorPane mainpane;
    private int sflag , lflag;
    public ImageView getHero() {
        return Hero;
    }
    String chosenHelmet;

    public void setHero(ImageView hero) {
        Hero = hero;
    }

    Hero(AnchorPane anchorPane, Position position, int width, int height , double speed, Text lancet , Text swordt, String chosenHelmet, int Score){
        this.chosenHelmet = chosenHelmet;
        this.deathview = new ImageView(new Image(new File(deathpath).toURI().toString()));
        deathview.setFitHeight(600);
        deathview.setFitWidth(800);
        deathview.setX(0);
        deathview.setY(0);
        this.mainpane = anchorPane;
        this.swordt = swordt;
        this.lancet = lancet;
        this.sword = new Sword();
        this.lance = new Lance();
        this.currCoins = new ArrayList<>();
        helmet = new Helmet(anchorPane, position, width, height, chosenHelmet);
        Hero = helmet.getHelmet();
        anchorPane.getChildren().add(Hero);
        this.speed = speed;
        scoreboard = new Text();
        score = Integer.toString(Score);
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
        this.isRevived = false;
    }

    public Weapon getsword(){
        return this.sword;
    }
    public Weapon getlance(){
        return this.lance;
    }

    public void setPosition(Position position) {
        if(this.activeWeapon!=null){
            ImageView image = this.activeWeapon.getimage();
            image.setX(position.getX());
            image.setY(position.getY() + this.Hero.getFitHeight() - image.getFitHeight()/2);
            if(this.activeWeapon.getName().equals("Sword")){
                image.setX(position.getX()-image.getFitWidth()/2);
            }
        }
        this.position = new Position(75, position.getY());
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
        helmet.animate(this.activeWeapon);
    }

    public String getscore(){
        return this.score;
    }


    public void setScore(int score) {
        this.score = Integer.toString(score);
        scoreboard.setText(this.score);
    }

    public void updatecoins() {
        coinboard.setText(Integer.toString(currCoins.size()));
    }

    public void setWeapon(Weapon weapon) {
        if(weapon==null){
            return;
        }
        if(weapon.getName().equals("Sword")){
            this.sword.increaseLevel();
            this.swordt.setText(Integer.toString(this.sword.getLevel()));
            this.activeWeapon = this.sword;
        }
        if(weapon.getName().equals("Lance")){
            this.lance.increaseLevel();
            this.lancet.setText(Integer.toString(this.lance.getLevel()));
            this.activeWeapon = this.lance;
        }
        System.out.println("hero got" + this.activeWeapon.getName());
    }

    private void setweapon(Weapon weapon) {
        ImageView image = weapon.getimage();
        image.setX(this.Hero.getX());
        image.setY(this.Hero.getY()+this.Hero.getFitHeight()-image.getFitHeight()/2);
    }

    public void setActiveWeapon(Weapon weapon){
        if(weapon==null){
            this.activeWeapon = null;
            return;
        }
        if(this.activeWeapon!=null){
            mainpane.getChildren().remove(this.activeWeapon);
            if(weapon.getName().equals("Sword")){
                if(this.sword!=null){
                    mainpane.getChildren().add(this.sword.getimage());
                }
            }
            else{
                if(this.lance!=null){
                    mainpane.getChildren().add(this.lance.getimage());
                }
            }
        }
        if(this.activeWeapon==null){
            if(weapon.getName().equals("Sword")){
                if(this.sword!=null){
                    mainpane.getChildren().add(this.sword.getimage());
                }
            }
            else{
                if(this.lance!=null){
                    mainpane.getChildren().add(this.lance.getimage());
                }
            }
        }
        this.activeWeapon = weapon;
    }

    public void revivehero(){
        mainpane.getChildren().remove(deathview);
    }



    public void animate(Weapon wp){
        if(wp==null){
            return;
        }
    }

    public void strike(){
        System.out.println("In Strike");
        if (this.activeWeapon != null){
            if (this.activeWeapon.getName().equals("Lance")){
//                TranslateTransition translateTransition1 = new TranslateTransition();
//                translateTransition1.setDuration(Duration.millis(250));
//                translateTransition1.setNode(this.activeWeapon.getimage());
//                translateTransition1.setByX(40);
//                translateTransition1.setCycleCount(1);
//                translateTransition1.setAutoReverse(false);
//                TranslateTransition translateTransition2 = new TranslateTransition();
//                translateTransition2.setDuration(Duration.millis(250));
//                translateTransition2.setNode(this.activeWeapon.getimage());
//                translateTransition2.setByX(-40);
//                translateTransition2.setCycleCount(1);
//                translateTransition2.setAutoReverse(false);
//                SequentialTransition seqT = new SequentialTransition(translateTransition1, translateTransition2);
//                seqT.play();
            }
            else if (this.activeWeapon.getName().equals("Sword")){
                RotateTransition rotate = new RotateTransition();
                rotate.setAxis(Rotate.Z_AXIS);
                rotate.setByAngle(30);
                rotate.setCycleCount(1);
                rotate.setDuration(Duration.millis(500));
                rotate.setNode(this.activeWeapon.getimage());
                rotate.play();
            }
        }
    }

    public void setinActiveWeapon(Weapon weapon) {
        mainpane.getChildren().remove(weapon.getimage());
    }

    public Weapon getActiveWeapon() {
        return activeWeapon;
    }

    public void removecoins(int i) {
        if(currCoins.size()<i){
            return;
        }
        for(int x=i-1;x>=0;x--){
            currCoins.remove(x);
        }
        updatecoins();
    }

    public Boolean die(AnchorPane mainPane, AnchorPane abyssPane, AnchorPane resultmenu, Timeline time, int orcKillCount, int tntBurstCount, Long startTime, int orcEncounterCount, int swordsCollected, int spearsCollected, int coinChestsOpened) {
        time.pause();
        if(!this.isRevived && this.currCoins.size()> 10 ){
            mainpane.getChildren().add(deathview);
            mainpane.getChildren().add(abyssPane);
        }
        else{
            isRevived=true;
            mainpane.getChildren().add(resultmenu);
            LoadPage.appendToFile("src\\main\\java\\com\\example\\game\\heroLocations.txt", orcKillCount + " " + tntBurstCount + " " + this.getscore() +
                    " " + (java.time.Instant.now().getEpochSecond()-startTime) + " " + orcEncounterCount + " " + swordsCollected + " " + spearsCollected + " " + coinChestsOpened);
        }
        return isRevived;
    }
}
