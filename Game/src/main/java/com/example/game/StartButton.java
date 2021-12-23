package com.example.game;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;

public class StartButton extends Button {
    private final String STYLE = "-fx-background-color:transparent; -fx-background-size: cover";
    private final String STYLE_Closed = "-fx-background-color:transparent; -fx-background-size: cover";
    private String path = "src/main/resources/com/example/game/images/resume.png";
    private View_Manager manager;

    StartButton(View_Manager manager){
        this.manager = manager;
        setText("");
        setPrefHeight(110);
        setPrefWidth(110);
        setStyle(STYLE);
        initialisebutton();
        ImageView img = new ImageView(new File(path).toURI().toString());
        img.setFitHeight(110);
        img.setFitWidth(110);
        this.setGraphic(img);
    }
    private void click(){
        setStyle(STYLE_Closed);
        //Here we can use Event Handler;
    }

    private void againclick(){
        setStyle(STYLE);
    }


    private void handler() throws FileNotFoundException {
        LoadPage page = new LoadPage(manager.getMainStage());
        manager.removevideo();
        page.start();
    }


    private void initialisebutton(){
        setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    handler();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

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
