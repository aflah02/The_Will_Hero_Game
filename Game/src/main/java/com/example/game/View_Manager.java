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
import java.util.Objects;

public class View_Manager {
    private AnchorPane mainPane;
    private Scene mainScene;
    private Stage mainStage;
    private String gradient = "src/main/resources/com/example/game/images/GradientBackground.jpg";
    private MediaPlayer player;
    private MediaView view;

    View_Manager(Stage stage) {

        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane,800,600);
        if(stage==null){
            mainStage = new Stage();
        }
        else{
            mainStage = stage;
        }
        mainStage.setScene(mainScene);
        String path = "src/main/resources/com/example/game/videos/openingBackground.mp4";
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        MediaView mediaView = new MediaView(mediaPlayer);
        mediaPlayer.setAutoPlay(true);
        mainPane.getChildren().add(mediaView);
        createButtons(mediaPlayer);
        this.player = mediaPlayer;
        this.view = view;
    }

    private void createButtons(MediaPlayer player){
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
        button3.setLayoutX(625);
        button3.setLayoutY(50);
        mainPane.getChildren().add(button3);
        Exit_Button exit = new Exit_Button(this.mainStage);
        exit.setLayoutX(700);
        exit.setLayoutY(50);
        mainPane.getChildren().add(exit);
    }

    public AnchorPane getMainPane() {
        return mainPane;
    }

    public Scene getMainScene() {
        return mainScene;
    }

    public Stage getMainStage() {
        Image icon = new Image(new File("src/main/resources/com/example/game/images/logo.png").toURI().toString());
        mainStage.getIcons().add(icon);
        mainStage.setTitle("Will Hero");
        mainStage.setResizable(false);
        return mainStage;
    }

    public void changeScene(Scene scene){
        mainPane.getChildren().remove(player);
        player.setMute(true);
        mainStage.setScene(scene);
        mainStage.show();
    }

    public void removevideo() {
        player.setMute(true);
        mainPane.getChildren().remove(view);
        mainStage.show();
    }
}
