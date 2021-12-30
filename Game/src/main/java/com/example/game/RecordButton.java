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

public class RecordButton extends Button {
    private final String STYLE = "-fx-background-color:transparent; -fx-background-size: cover";
    RecordButton(){
        setText("");
        setPrefHeight(50);
        setPrefWidth(50);
        setStyle(STYLE);
        initialisebutton();
        ImageView img;
        String imagePath = "src/main/resources/com/example/game/images/recording.png";
        img = new ImageView(new File(imagePath).toURI().toString());
        img.setFitHeight(50);
        img.setFitWidth(50);
        this.setGraphic(img);
    }

    private void click(){
        String STYLE_Closed = "-fx-background-color:transparent; -fx-background-size: cover;";
        setStyle(STYLE_Closed);
        //Here we can use Event Handler;
    }

    private void againclick(){
        setStyle(STYLE);
    }


    private void initialisebutton(){

        setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
            }
        });

        setOnMousePressed(mouseEvent -> {
            if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                againclick();
            }
        });

        setOnMouseReleased(mouseEvent -> {
            if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                click();
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
