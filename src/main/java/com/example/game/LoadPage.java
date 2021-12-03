package com.example.game;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;

public class LoadPage {
    private AnchorPane mainPane;
    private Scene mainScene;
    private String bg = "src/main/resources/com/example/game/images/cloud2.jpeg";
    private Timeline time;
    //private Panda_Helmet hero;
    private Circle ball;
    private int xSpeed;

    LoadPage() {
        xSpeed = 2;
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane,800,600);
        addobjects(null);

        //hero = new Panda_Helmet();

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
        ImageView background = new ImageView(new File(bg).toURI().toString());
        background.setFitHeight(600);
        background.setFitWidth(800);
        mainPane.getChildren().add(background);
        PauseButton pause = new PauseButton();
        pause.setLayoutX(400);
        pause.setLayoutY(0);
        Button button = new Button("start");
        button.setLayoutX(400);
        button.setLayoutY(50);

        mainPane.getChildren().add(pause);
        mainPane.getChildren().add(button);
    }
    public void start(Stage stage){
        ball = new Circle(20);
        ball.setFill(Color.AQUA);
        ball.setCenterX(300);
        ball.setCenterY(400);
        mainPane.getChildren().add(ball);

        KeyFrame frame = new KeyFrame(Duration.millis(10), e->{ moveBall(); });
        this.time = new Timeline(frame);
        time.setCycleCount(Timeline.INDEFINITE);
        System.out.println("Hello");
        stage.setScene(this.mainScene);
        stage.show();
    }

    private void moveBall(){
        ball.setCenterX(ball.getCenterX()+xSpeed);
        if(ball.getCenterX()>=800 || ball.getCenterX()<=0){
            xSpeed=-xSpeed;
        }
    }

    public void pausegame(PauseButton pause){
        pause.setOnAction(e ->{
            time.pause();
        });
    }
    public void startgame(Button button){
        button.setOnAction(e ->{
            time.play();
        });
    }



    public AnchorPane getMainPane() {
        return mainPane;
    }

    public Scene getMainScene() {
        return mainScene;
    }





}
