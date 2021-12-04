package com.example.game;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
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
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;

public class LoadPage {
    private AnchorPane mainPane;
    private Scene mainScene;
    private String image = "src/main/resources/com/example/game/images/cloud2.jpeg";
    private String bg = "src/main/resources/com/example/game/images/background.png";
    private String islandSmall = "src/main/resources/com/example/game/images/T_Islands_07.png";
    private String islandMedium = "src/main/resources/com/example/game/images/T_Islands_09.png";
    private String islandLarge = "src/main/resources/com/example/game/images/T_Islands_01.png";
    private Timeline time;
    //private Panda_Helmet hero;
    private Circle ball , ball2;
    private int xSpeed,yspeed;
    private AnchorPane newpane;
    private Stage stage;
    private final ArrayList<Game_Objects> game_objects = new ArrayList<>();
    private Hero hero;
    LoadPage(Stage stage) {
        this.stage = stage;
        xSpeed = 1;
        yspeed = 2;
        newpane = null;
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane,800,600);
        newpane = pausemenu();
        addobjects(null);
        this.hero = new Hero(mainPane, new Position(75,300-50), 50, 50);
        //hero = new Panda_Helmet();

    }
    private AnchorPane pausemenu(){
        ImageView background = new ImageView(new File(image).toURI().toString());
        background.setFitHeight(600);
        background.setFitWidth(800);
        mainPane.getChildren().add(background);
        PauseButton pause = new PauseButton();
        pause.setLayoutX(400);
        pause.setLayoutY(0);
        mainPane.getChildren().add(pause);
        AnchorPane menu = new AnchorPane();
        menu.setPrefHeight(300);
        menu.setPrefWidth(300);
        menu.setLayoutX(250);
        menu.setLayoutY(150);
        Image bg = new Image(new File(this.bg).toURI().toString(),300,300,false,true);
        BackgroundImage image = new BackgroundImage(bg, BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,null);
        menu.setBackground(new Background(image));
        ResumeButton resume = new ResumeButton();
        resume.setLayoutX(50);
        resume.setLayoutY(50);
        menu.getChildren().add(resume);
        pausegame(pause);
        startgame(resume,pause);
        Homebutton home = new Homebutton(stage);
        home.setLayoutX(100);
        home.setLayoutY(100);
        menu.getChildren().add(home);
        return menu;
    }

    private void addobjects(MediaPlayer player){
        Standard_Green_Orc green_orc1 = new Standard_Green_Orc(mainPane, new Position(350,300-50), 50, 50);
        this.game_objects.add(green_orc1);
        Standard_Red_Orc red_orc1 = new Standard_Red_Orc(mainPane, new Position(550,300-50), 50, 50);
        this.game_objects.add(red_orc1);
        Island smallIsland1 = new Island(islandSmall, mainPane, new Position(75,300), 200, 100);
//        Island mediumIsland1 = new Island(islandMedium, mainPane, new Position(500,500), 200, 50);
        Island largeIsland1 = new Island(islandLarge, mainPane, new Position(325,300), 450, 150);
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
    public void start(){
//        ball = new Circle(20);
//        ball.setFill(Color.AQUA);
//        ball.setCenterX(300);
//        ball.setCenterY(400);
//        mainPane.getChildren().add(ball);
//        ball2 = new Circle(20);
//        ball2.setFill(Color.AQUA);
//        ball2.setCenterX(500);
//        ball2.setCenterY(400);
//        mainPane.getChildren().add(ball2);
        stage.setScene(this.mainScene);
        stage.show();

        KeyFrame frame = new KeyFrame(Duration.millis(10), e->{
            moveHero(this.hero);
            for (Game_Objects game_object: this.game_objects){
                if (game_object instanceof Orc){
                    moveOrc((Orc) game_object);
                }
            }
        }
        );
        this.time = new Timeline(frame);
        time.setCycleCount(Timeline.INDEFINITE);
        time.play();
    }

//    private void moveBall(){
//
//        ball.setCenterY(ball.getCenterY()-yspeed);
//        if(ball.getCenterY()>=300 || ball.getCenterY()<=200){
//            yspeed=-yspeed;
//        }
//
//        ball2.setCenterY(ball2.getCenterY()-xSpeed);
//        if(ball2.getCenterY()>=400 || ball2.getCenterY()<=200){
//            xSpeed=-xSpeed;
//        }
//    }

    private void moveHero(Hero hero){
        hero.getHero().setY(hero.getHero().getY()-yspeed);
        if(hero.getHero().getY()>=300-50 || hero.getHero().getY()<=200-50){
            yspeed=-yspeed;
        }
    }

    private void moveOrc(Orc orc){
        orc.getOrc().setY(orc.getOrc().getY()-yspeed);
        if(hero.getHero().getY()>=300-50 || hero.getHero().getY()<=200-50){
            yspeed=-yspeed;
        }
    }

    public void pausegame(PauseButton pause){
        pause.setOnAction(e ->{
            time.pause();
            mainPane.getChildren().remove(pause);
            mainPane.getChildren().add(newpane);
        });
    }
    public void startgame(Button button , PauseButton pause){
        button.setOnAction(e ->{
            time.play();
            mainPane.getChildren().add(pause);
            mainPane.getChildren().remove(newpane);
        });
    }


    public AnchorPane getMainPane() {
        return mainPane;
    }

    public Scene getMainScene() {
        return mainScene;
    }





}
