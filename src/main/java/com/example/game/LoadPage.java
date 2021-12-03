package com.example.game;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.io.File;

public class LoadPage {
    private AnchorPane mainPane;
    private Scene mainScene;
    private String gradient = "src/main/resources/com/example/game/images/GradientBackground.jpg";

    LoadPage() {
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane,800,600);
    }

    private void addobjects(MediaPlayer player){
        /*
        //Image of Gradient to stop video showing extra buttons
        ImageView grad = new ImageView(new File(gradient).toURI().toString());
        grad.setFitHeight(147);
        grad.setFitWidth(65);
        grad.setLayoutX(369);
        grad.setLayoutY(390);
        mainPane.getChildren().add(grad);
        //Start button
        StartButton button = new StartButton(this);
        button.setLayoutX(340);
        button.setLayoutY(260);
        mainPane.getChildren().add(button);
        //Load Button
        LoadButton button1 = new LoadButton();
        button1.setLayoutX(425);
        button1.setLayoutY(380);
        mainPane.getChildren().add(button1);
        //Google Button
        GoogleButton button2 = new GoogleButton();
        button2.setLayoutX(270);
        button2.setLayoutY(380);
        mainPane.getChildren().add(button2);
        //Music Button
        MusicButton button3 = new MusicButton(player);
        button3.setLayoutX(700);
        button3.setLayoutY(50);
        mainPane.getChildren().add(button3);
        */

    }

    public AnchorPane getMainPane() {
        return mainPane;
    }

    public Scene getMainScene() {
        return mainScene;
    }





}
