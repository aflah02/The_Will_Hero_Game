package com.example.game;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class MusicButton extends Button {
    private int flag;
    private MediaPlayer player;
    private final String STYLE = "-fx-background-color:transparent; -fx-background-size: cover";
    private final String STYLE_Closed = "-fx-background-color:transparent; -fx-background-size: cover;";
    private String path = "src/main/resources/com/example/game/images/musicbutton.jpeg";
    private String path2 = "src/main/resources/com/example/game/images/musicstopped.png";

    MusicButton(MediaPlayer player){
        flag = 0;
        this.player = player;
        setText("");
        setPrefHeight(50);
        setPrefWidth(50);
        setStyle(STYLE);
        initialisebutton();
        ImageView img = new ImageView(new File(path).toURI().toString());
        img.setFitHeight(50);
        img.setFitWidth(50);
        this.setGraphic(img);
    }
    private void click(){
        setStyle(STYLE_Closed);
        ImageView img = new ImageView(new File(path).toURI().toString());
        img.setFitHeight(50);
        img.setFitWidth(50);
        this.setGraphic(img);
        //Here we can use Event Handler;
    }

    private void againclick(){
        setStyle(STYLE);
        this.setGraphic(new ImageView(new File(path).toURI().toString()));
    }


    private void initialisebutton(){
        setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                handler();
            }
        });

        setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                    click();
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

    private void handler() {
        if(player==null){
            System.out.println("No media to stop");
            return;
        }
        if(flag == 0){
            flag =1;
            ImageView img = new ImageView(new File(path2).toURI().toString());
            img.setFitHeight(50);
            img.setFitWidth(50);
            this.setGraphic(img);
            player.setMute(true);
        }
        else{
            flag =0;
            ImageView img = new ImageView(new File(path).toURI().toString());
            img.setFitHeight(50);
            img.setFitWidth(50);
            this.setGraphic(img);
            player.setMute(false);
        }
    }


}

