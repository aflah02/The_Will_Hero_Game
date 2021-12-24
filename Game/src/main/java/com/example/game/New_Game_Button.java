package com.example.game;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class New_Game_Button extends Button {
    private final String STYLE = "-fx-background-color:transparent; -fx-background-size: cover";
    private final String STYLE_Closed = "-fx-background-color:transparent; -fx-background-size: cover;";
    private String path = "src/main/resources/com/example/game/images/resume.png";
    private Stage stage;

    New_Game_Button(Stage stage){
        this.stage = stage;
        setText("");
        setPrefHeight(100);
        setPrefWidth(100);
        setStyle(STYLE);
        initialisebutton();
        ImageView img = new ImageView(new File(path).toURI().toString());
        img.setFitHeight(100);
        img.setFitWidth(100);
        this.setGraphic(img);
    }
    private void click(){
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
                try {
                    handler();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
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

    private void handler() throws IOException, InterruptedException {
        LoadPage page = new LoadPage(stage);
        page.start();
    }


}