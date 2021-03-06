package com.example.game;

import javafx.animation.Transition;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

public class Weapon_Chest extends Chest implements Serializable {
    private Weapon weapon;
    private final String path1 = "src/main/resources/com/example/game/images/chest1.png";
    private final String path2 = "src/main/resources/com/example/game/images/chest2.png";
    private final String path3 = "src/main/resources/com/example/game/images/chest3.png";
    private final String path4 = "src/main/resources/com/example/game/images/chest4.png";
    private final String path5 = "src/main/resources/com/example/game/images/chest5.png";
    private final String path6 = "src/main/resources/com/example/game/images/chest6.png";
    private final String path7 = "src/main/resources/com/example/game/images/chest7.png";
    private final String path8 = "src/main/resources/com/example/game/images/chest8.png";
    private transient MediaPlayer opensound;
    private final transient ImageView chest;
    private Position position;
    public String[] imagePaths;
    private boolean isopen;
    private String weaponName;
    private final Island islandOfResidence;
    private double speed;
    private transient WeaponButton button1, button2;

    public Island getIslandOfResidence() {
        return islandOfResidence;
    }

    public WeaponButton getButton1() {
        return button1;
    }

    public void setButton1(WeaponButton button1) {
        this.button1 = button1;
    }

    public WeaponButton getButton2() {
        return button2;
    }

    public void setButton2(WeaponButton button2) {
        this.button2 = button2;
    }



    Weapon_Chest(AnchorPane anchorPane, Position position, int width, int height, String WeaponName, Island islandOfResidence,WeaponButton button1 , WeaponButton button2,MediaPlayer opensound, Boolean isopen){
        this.opensound = opensound;
        this.isopen = isopen;
        this.width = width;
        this.height = height;
        this.button1 = button1;
        this.button2 = button2;
        this.imagePaths = new String[]{path1, path2, path3, path4, path5, path6, path7, path8};
        chestAnimations = new ArrayList<>();
        this.islandOfResidence = islandOfResidence;
        for (String path: imagePaths){
            chestAnimations.add(new Image(new File(path).toURI().toString()));
        }
        this.chest = new ImageView();
        Image img = new Image(new File(path1).toURI().toString());
        chest.setImage(img);
        chest.setX(position.getX());
        chest.setY(position.getY());
        chest.setFitWidth(width);
        chest.setFitHeight(height);
        anchorPane.getChildren().add(chest);
        this.position = position;
        if (WeaponName.equals("Sword")){
            this.weapon = new Sword();
        }
        else{
            this.weapon = new Lance();
        }
        this.speed = islandOfResidence.getSpeed();
        this.weaponName = WeaponName;
        if (isopen){
            animateChest();
        }
    }
    @Override
    public String getName(){
        if (this.weaponName.equals("Sword")){
            return "Weapon Chest Sword";
        }
        else{
            return "Weapon Chest Lance";
        }

    }

    @Override
    public double getImageViewWidth() {
        return chest.getFitWidth();
    }

    @Override
    public double getImageViewHeight() {
        return chest.getFitHeight();
    }

    @Override
    public void collide(Hero hero) {
        if(!isopen){
            opensound.play();
            opensound.seek(Duration.ZERO);
            isopen = true;
            animateChest();
            hero.setWeapon(this.weapon);
            button1.setactive();
            button2.setinactive();
        }
    }

    @Override
    public ImageView getImage() {
        return this.chest;
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
        chest.setX(position.getX());
        chest.setY(position.getY());
    }
    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public Island getIslandofResidence() {
        return this.islandOfResidence;
    }

    @Override
    public void setSpeed(double v) {
        this.speed = v;
    }

    @Override
    public void setPositionY(double v) {
        this.position.setY(v);
        this.chest.setY(v);
    }

    @Override
    public Boolean getOpen(){
        return this.isopen;
    }


}
