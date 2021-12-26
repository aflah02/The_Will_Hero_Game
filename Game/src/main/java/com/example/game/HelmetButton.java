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

public class HelmetButton extends Button {
    private final String STYLE = "-fx-background-color:transparent; -fx-background-size: cover;-fx-border-color: grey; -fx-border-style: solid; -fx-border-width: 2;";
    private final String STYLE_CLOSED = "-fx-background-color:transparent; -fx-background-size: cover;-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 2;";
    private final String STYLE_ACTIVE = "-fx-background-color:black; -fx-background-size: cover;-fx-border-color: black; -fx-border-style: solid; -fx-border-width: 2;";
    private String pandaHelmetImage = "src/main/resources/com/example/game/images/panda.png";
    private String angelHelmetImage = "src/main/resources/com/example/game/images/angel.png";
    private String jotunHelmetImage = "src/main/resources/com/example/game/images/jotun.png";
    private String lokiHelmetImage = "src/main/resources/com/example/game/images/loki.png";
    private Weapon weapon;
    private int type;
    private boolean isactive;

    HelmetButton(String HelmetName, double x , double y){
        this.setLayoutX(x);
        this.setLayoutY(y);
        setText("");
        setPrefHeight(50);
        setPrefWidth(50);
        setStyle(STYLE);
        initializeButton();
        ImageView img;
        switch (HelmetName) {
            case "Jotun" -> {
                img = new ImageView(new File(jotunHelmetImage).toURI().toString());
                img.setFitHeight(40);
                img.setFitWidth(7);
                img.setRotate(30);
                type = 1;
            }
            case "Angel" -> {
                img = new ImageView(new File(angelHelmetImage).toURI().toString());
                img.setFitHeight(40);
                img.setFitWidth(20);
                type = 2;
            }
            case "Panda" -> {
                img = new ImageView(new File(pandaHelmetImage).toURI().toString());
                img.setFitHeight(40);
                img.setFitWidth(20);
                type = 3;
            }
            default -> {
                img = new ImageView(new File(lokiHelmetImage).toURI().toString());
                img.setFitHeight(40);
                img.setFitWidth(20);
                type = 4;
            }
        }
        this.setGraphic(img);
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
        isactive = true;
        setStyle(STYLE_ACTIVE);
    }

    public void setinactive(){
        isactive = false;
        setStyle(STYLE);
    }


}
