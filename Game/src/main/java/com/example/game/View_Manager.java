package com.example.game;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

public class View_Manager {
    private final AnchorPane mainPane;
    private final Scene mainScene;
    private final Stage mainStage;
    private final ArrayList<MediaPlayer> players;
    private MediaView view;
    private final AnchorPane helmetChooseMenu;
    private String helmetChosen;
    View_Manager(Stage stage) {
        this.helmetChosen = "Panda";
        helmetChooseMenu = HelmetChoices();
        players = new ArrayList<>();
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane,800,600);
        if(stage==null){
            mainStage = new Stage();
        }
        else{
            mainStage = stage;
        }
        mainStage.setScene(mainScene);
        String pathforgifbg = "src/main/resources/com/example/game/videos/backgroundasgif.gif";
        String path = "src/main/resources/com/example/game/videos/openingBackground.mp4";
        String pathforbgaudio = "src/main/resources/com/example/game/audios/mainscreenbackgroundaudio.mp3";
        Image img = new Image(new File(pathforgifbg).toURI().toString());
        ImageView backgroundasgif = new ImageView(img);
        backgroundasgif.setFitHeight(600);
        backgroundasgif.setFitWidth(800);
        mainPane.getChildren().add(backgroundasgif);
        Media media = new Media(new File(pathforbgaudio).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        MediaView mediaView = new MediaView(mediaPlayer);
        mediaPlayer.setAutoPlay(true);
        mainPane.getChildren().add(mediaView);
        createButtons(mediaPlayer);
        players.add(mediaPlayer);
    }

    private void createButtons(MediaPlayer player){
        //Image of Gradient to stop video showing extra buttons
        String gradient = "src/main/resources/com/example/game/images/GradientBackground.jpg";
        ImageView grad = new ImageView(new File(gradient).toURI().toString());
        grad.setFitHeight(147);
        grad.setFitWidth(65);
        grad.setLayoutX(369);
        grad.setLayoutY(390);
        mainPane.getChildren().add(grad);
        //Start button
        StartButton button = new StartButton(this, helmetChosen);
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
        MusicButton button3 = new MusicButton(players);
        button3.setLayoutX(625);
        button3.setLayoutY(50);
        mainPane.getChildren().add(button3);
        Exit_Button exit = new Exit_Button(this.mainStage);
        exit.setLayoutX(700);
        exit.setLayoutY(50);
        mainPane.getChildren().add(exit);
        button2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mainPane.getChildren().add(helmetChooseMenu);
            }
        });
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
        for(MediaPlayer player : players ){
            mainPane.getChildren().remove(player);
            player.setMute(true);
        }

        mainStage.setScene(scene);
        mainStage.show();
    }

    public void removevideo() {
        for(MediaPlayer player : players ){
            mainPane.getChildren().remove(player);
            player.setMute(true);
        }
        mainPane.getChildren().remove(view);
        mainStage.show();
    }

    public void openMenu(GoogleButton googleButton){

    }

    private AnchorPane HelmetChoices() {
        AnchorPane HelmetChoices = new AnchorPane();
        HelmetChoices.setPrefHeight(400);
        HelmetChoices.setPrefWidth(400);
        HelmetChoices.setLayoutX(250);
        HelmetChoices.setLayoutY(150);
        String bg1 = "src/main/resources/com/example/game/images/resultmenu.png";
        Image bg = new Image(new File(bg1).toURI().toString(),300,300,false,true);
        BackgroundImage image = new BackgroundImage(bg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,null);
        HelmetChoices.setBackground(new Background(image));
        //.................................................
        Text sometext = new Text();
        sometext.setText("Choose Your Helmet");
        sometext.setFont(Font.font ("Verdana", 20));
        sometext.setFill(Color.WHITE);
        sometext.setX(75);
        sometext.setY(100);
        HelmetChoices.getChildren().add(sometext);
        //
        HelmetButton Loki = new HelmetButton("Loki", 85, 100);
        HelmetButton Angel = new HelmetButton("Angel", 175, 100);
        HelmetButton Panda = new HelmetButton("Panda", 85, 170);
        HelmetButton Jotun = new HelmetButton("Jotun", 175, 170);
        HelmetChoices.getChildren().add(Loki);
        HelmetChoices.getChildren().add(Angel);
        HelmetChoices.getChildren().add(Panda);
        HelmetChoices.getChildren().add(Jotun);
        Loki.setOnMouseClicked(mouseEvent -> {
            this.helmetChosen = "Loki";
            Loki.setactive();
            Angel.setinactive();
            Panda.setinactive();
            Jotun.setinactive();
        });
        Angel.setOnMouseClicked(mouseEvent -> {
            this.helmetChosen = "Angel";
            Angel.setactive();
            Loki.setinactive();
            Panda.setinactive();
            Jotun.setinactive();
        });
        Panda.setOnMouseClicked(mouseEvent -> {
            this.helmetChosen = "Panda";
            Panda.setactive();
            Loki.setinactive();
            Angel.setinactive();
            Jotun.setinactive();
        });
        Jotun.setOnMouseClicked(mouseEvent -> {
            this.helmetChosen = "Jotun";
            Jotun.setactive();
            Loki.setinactive();
            Panda.setinactive();
            Angel.setinactive();
        });
        CloseMenuButton closeMenuButton = new CloseMenuButton();
        closeMenuButton.setLayoutX(150);
        closeMenuButton.setLayoutY(50);
        closeMenuButton.setOnMouseClicked(mouseEvent -> {
            mainPane.getChildren().remove(helmetChooseMenu);
        });
        HelmetChoices.getChildren().add(closeMenuButton);
        return HelmetChoices;
    }
}
