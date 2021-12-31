package com.example.game;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.File;

public class WeaponButton extends Button {
    private int level;
    private final String STYLE = "-fx-background-color:transparent; -fx-background-size: cover;-fx-border-color: grey; -fx-border-style: solid; -fx-border-width: 2;";
    private final String STYLE_CLOSED = "-fx-background-color:transparent; -fx-background-size: cover;-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 2;";
    private final String STYLE_ACTIVE = "-fx-background-color:black; -fx-background-size: cover;-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 2;";
    private String path1 = "src/main/resources/com/example/game/images/smallsword.png";
    private String path2 = "src/main/resources/com/example/game/images/lance.png";
    private Hero hero;
    private Weapon weapon;
    private int type;
    private boolean isactive;

    WeaponButton(String weaponType, double x , double y,Hero hero){
        this.hero = hero;
        this.level = 0;
        this.setLayoutX(x);
        this.setLayoutY(y);
        setText("");
        setPrefHeight(50);
        setPrefWidth(50);
        setStyle(STYLE);
        initializeButton();
        ImageView img;
        if(weaponType.equals("Sword")){
            img = new ImageView(new File(path1).toURI().toString());
            img.setFitHeight(40);
            img.setFitWidth(7);
            img.setRotate(30);
            this.weapon = hero.getsword();
            type = 1;
        }
        else{
            img = new ImageView(new File(path2).toURI().toString());
            img.setFitHeight(40);
            img.setFitWidth(20);
            this.weapon = hero.getlance();
            type = 2;
        }
        this.setGraphic(img);
    }

    public int getLevel(){
        return this.level;
    }


    private void initializeButton(){

        setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(!isactive){
                    setEffect(new DropShadow());
                    setStyle(STYLE_CLOSED);
                }

            }
        });

        setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(!isactive){
                    setEffect(null);
                    setStyle(STYLE);
                }
            }
        });
    }

    public void setactive(){
        if(type==1){
            this.weapon = hero.getsword();
        }
        if(type==2){
            this.weapon = hero.getlance();
        }
        if(weapon!=null && weapon.getLevel()!=0&&!isactive){
            isactive = true;
            setStyle(STYLE_ACTIVE);
            hero.setActiveWeapon(this.weapon);
        }
        if(weapon==null || weapon.getLevel()==0){
            hero.setActiveWeapon(null);
        }
    }

    public void setinactive(){
        if(type==1){
            this.weapon = hero.getsword();
        }
        if(type==2){
            this.weapon = hero.getlance();
        }
        if(weapon!=null && weapon.getLevel()!=0 && isactive){
            isactive = false;
            hero.setinActiveWeapon(this.weapon);
        }

        setStyle(STYLE);
    }


}
