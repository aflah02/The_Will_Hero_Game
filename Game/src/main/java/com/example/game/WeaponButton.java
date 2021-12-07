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
    private String path1 = "src/main/resources/com/example/game/images/smallsword.png";
    private String path2 = "src/main/resources/com/example/game/images/lance.png";

    WeaponButton(int flag,double x , double y){
        this.level = 0;
        this.setLayoutX(x);
        this.setLayoutY(y);
        setText("");
        setPrefHeight(50);
        setPrefWidth(50);
        setStyle(STYLE);
        initialisebutton();
        if(flag ==1){
            ImageView img = new ImageView(new File(path1).toURI().toString());
            img.setFitHeight(35);
            img.setFitWidth(10);
            this.setGraphic(img);


        }
        else{
            ImageView img = new ImageView(new File(path2).toURI().toString());
            img.setFitHeight(35);
            img.setFitWidth(20);
            this.setGraphic(img);
        }
    }

    public int getLevel(){
        return this.level;
    }


    private void initialisebutton(){
        setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                handler();
            }
        });



        setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setEffect(new DropShadow());
                setStyle(STYLE_CLOSED);
            }
        });

        setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setEffect(null);
                setStyle(STYLE);
            }
        });
    }

    private void handler() {
        System.out.println("Weapon Button Clicked");
    }


}
