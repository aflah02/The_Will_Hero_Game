package com.example.game;

import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

public abstract class Orc extends Game_Objects implements Serializable {
    String path1 = "src/main/resources/com/example/game/images/d1.png";
    String path2 = "src/main/resources/com/example/game/images/d2.png";
    String path3 = "src/main/resources/com/example/game/images/d3.png";
    String path4 = "src/main/resources/com/example/game/images/d4.png";
    String path5 = "src/main/resources/com/example/game/images/d5.png";
    int id;
    MediaPlayer diesound;
    public String[] imagePaths;
    public ArrayList<Image> OrcAnimations;
    private int HitPoints;
    private int Damage;
    private int Coins;
    private Boolean Dead,iscolliding,isDying;
    protected Boolean isAboveIsland;
    private Orc orcbelow;

    Orc(MediaPlayer diesound,int id){
        this.isDying = false;
        this.id = id;
        this.iscolliding = false;
        this.orcbelow = null;
        this.HitPoints = 0;
        this.diesound = diesound;
        Dead = false;
        this.imagePaths = new String[]{path1,path2, path3, path4, path5};
        OrcAnimations = new ArrayList<>();
        for (String path: imagePaths){
            OrcAnimations.add(new Image(new File(path).toURI().toString()));
        }
    }

    public Boolean getAboveIsland() {
        return isAboveIsland;
    }

    public void setAboveIsland(Boolean aboveIsland) {
        isAboveIsland = aboveIsland;
    }

    public int getHitPoints(){
        return this.HitPoints;
    }
    public int getDamage(){
        return this.Damage;
    }
    public int getCoins(){
        return this.Coins;
    }
    public Boolean isDead(){
        return Dead;
    }
    public void setIsDead(){
        if(diesound!=null){
            diesound.play();
            diesound.seek(Duration.ZERO);
            System.out.println("diesound");
        }
        Dead = true;
    }
    public void setHitPoints(int points){
        this.HitPoints = points;
    }
    public abstract ImageView getOrc();
    public abstract ImageView getImage();
    public abstract void setOrc(ImageView Orc);
    public abstract void setSpeed(double speed);
    public abstract Island getIslandofResidence();
    public abstract void setIslandofResidence(Island island);
    public abstract double getInitialPosition();
    public abstract void setInitialPosition(double pos);

    public void animate() {
        if(!isDead()){
            this.isDying = true;
            Orc orc = this;
            Transition animation = new Transition() {
                {setCycleDuration(Duration.millis(500));}
                @Override
                protected void interpolate(double fraction) {
                    int index = (int) (fraction*(OrcAnimations.size()-1));
                    orc.getOrc().setImage(OrcAnimations.get(index));
                }
            };
            animation.setOnFinished(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    setIsDead();
                }
            });
            animation.setCycleCount(1);
            animation.play();
        }
    }
    public void setOrcbelow(Orc orcbelow) {
        this.orcbelow = orcbelow;
    }

    public Orc getOrcbelow() {
        return orcbelow;
    }

    public void setIscolliding(Boolean iscolliding) {
        this.iscolliding = iscolliding;
    }

    public Boolean getIscolliding() {
        return iscolliding;
    }

    public int getId(){
        return this.id;
    }

    public void setDying(Boolean dying){
        this.isDying = dying;
    }

    public Boolean getDying() {
        return isDying;
    }
}
