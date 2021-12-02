package com.example.game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import java.io.File;
import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("startscene.fxml"));
        String path = "src/main/resources/com/example/game/videos/openingBackground.mp4";
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);
        mediaPlayer.setAutoPlay(true);
        Group root = new Group();
        root.getChildren().add(mediaView);
        root.getChildren().add(fxmlLoader.load());
        Scene scene = new Scene(root,800,600);
        stage.setScene(scene);
        stage.setTitle("Playing video");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}