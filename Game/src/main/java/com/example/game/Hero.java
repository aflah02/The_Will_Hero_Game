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

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Hero implements Serializable {
    private final String coinpath = "src/main/resources/com/example/game/images/coin.png";
    private final String deathpath = "src/main/resources/com/example/game/images/death.png";
    private Helmet helmet;
    private Position position;
    private ArrayList<Coins> currCoins;
    private double speed;
    private Boolean isRevived;
    private transient ImageView Hero;
    private final transient ImageView deathview;
    private String score;
    private transient Text scoreboard,coinboard;
    private Weapon activeWeapon,sword,lance;
    private transient Text swordt , lancet;
    private transient AnchorPane mainpane;
    private int sflag , lflag;
    private String chosenHelmet;
    private int width, height;
    private int counter;
    public void setHero(ImageView hero) {
        Hero = hero;
    }
    public ImageView getHero() {
        return Hero;
    }

    Hero(AnchorPane anchorPane, Position position, int width, int height , double speed, Text lancet , Text swordt, String chosenHelmet, int Score, Weapon sword, Weapon lance){
        counter = 0;
        this.chosenHelmet = chosenHelmet;
        this.width = width;
        this.height = height;
        this.deathview = new ImageView(new Image(new File(deathpath).toURI().toString()));
        deathview.setFitHeight(600);
        deathview.setFitWidth(800);
        deathview.setX(0);
        deathview.setY(0);
        this.mainpane = anchorPane;
        this.swordt = swordt;
        this.lancet = lancet;
        if (sword == null){
            this.sword = new Sword();
        }
        else{
            this.sword = sword;
        }
        if (sword == null){
            this.lance = new Lance();
        }
        else{
            this.lance = lance;
        }
        this.lancet.setText(Integer.toString(this.lance.getLevel()));
        this.swordt.setText(Integer.toString(this.sword.getLevel()));
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
        scoreboard.setX(400);
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

    public void setCounter(int counter){
        this.counter = counter;
    }
    public int getCounter(){
        return this.counter;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public String getChosenHelmet() {
        return chosenHelmet;
    }

    public void setChosenHelmet(String chosenHelmet) {
        this.chosenHelmet = chosenHelmet;
    }

    public void setHeight(int height) {
        this.height = height;
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

    public Boolean die (AnchorPane mainPane, AnchorPane abyssPane, AnchorPane resultmenu, Timeline time, int orcKillCount, int tntBurstCount, Long startTime, int orcEncounterCount, int swordsCollected, int spearsCollected, int coinChestsOpened) throws Exception {
        time.pause();
        if(!this.isRevived && this.currCoins.size()> 10 ){
            mainpane.getChildren().add(deathview);
            mainpane.getChildren().add(abyssPane);
        }
        else{
            LoadPage.appendToFile("src\\main\\java\\com\\example\\game\\heroLocations.txt", orcKillCount + " " + tntBurstCount + " " + this.getscore() +
                    " " + (java.time.Instant.now().getEpochSecond()-startTime) + " " + orcEncounterCount + " " + swordsCollected + " " + spearsCollected + " " + coinChestsOpened);
            File file1 = new File("src\\main\\java\\com\\example\\game\\heroLocations.txt");
            File file2 = new File("src\\main\\java\\com\\example\\game\\previousheroLocations.txt");
            PrintWriter writer = new PrintWriter("src\\main\\java\\com\\example\\game\\previousheroLocations.txt");
            writer.print("");
            writer.close();
            writefile(file1,file2);
            String[] cmd = {"src\\main\\java\\com\\example\\game\\analyze.bat"};
            Process p = Runtime.getRuntime().exec(cmd);
            System.out.println("analyze" + p);
            isRevived=true;
            mainpane.getChildren().add(resultmenu);
            }
        return isRevived;
    }
    public void writefile(File a,File b) throws Exception{
        FileInputStream in = new FileInputStream(a);
        FileOutputStream out = new FileOutputStream(b);
        try {
            int n;
            while ((n = in.read()) != -1) {
                out.write(n);
            }
        }
        finally {
            in.close();
            out.close();
        }
        System.out.println("File Copied");
    }
    public Text getLanceText(){
        return lancet;
    }
    public Text getSwordText(){
        return swordt;
    }
    public int getSwordLevel(){
        return this.sword.getLevel();
    }
    public int getLanceLevel(){
        return this.lance.getLevel();
    }

    public void updatelevels() {
        this.swordt.setText(Integer.toString(this.sword.getLevel()));
        this.lancet.setText(Integer.toString(this.lance.getLevel()));
    }
}
