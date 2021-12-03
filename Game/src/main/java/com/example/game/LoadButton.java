package com.example.game;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.io.File;

public class LoadButton extends Button {
    private final String STYLE = "-fx-background-color:transparent; -fx-background-size: cover";
    private final String STYLE_Closed = "-fx-background-color:transparent; -fx-background-size: cover;";
    private String path = "src/main/resources/com/example/game/images/LevelButton.jpg";

    LoadButton(){
        setText("");
        setPrefHeight(110);
        setPrefWidth(110);
        setStyle(STYLE);
        initialisebutton();
        this.setGraphic(new ImageView(new File(path).toURI().toString()));
    }
    private void click(){
        setStyle(STYLE_Closed);
        this.setGraphic(new ImageView(new File(path).toURI().toString()));
        //Here we can use Event Handler;
    }

    private void againclick(){
        setStyle(STYLE);
        this.setGraphic(new ImageView(new File(path).toURI().toString()));
    }


    private void initialisebutton(){
        setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                    againclick();
                }
            }
        });

        setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                    click();
                }
            }
        });

        setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setEffect(new DropShadow());
            }
        });

        setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setEffect(null);
            }
        });
    }


}
