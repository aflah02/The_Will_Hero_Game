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
    private String image = "src/main/resources/com/example/game/images/bg2.jpg";
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
    private ArrayList<Island> islands;
    private double pos;
    private Coin_Chest chest;
    LoadPage(Stage stage) {
        this.stage = stage;
        xSpeed = 1;
        yspeed = 2;
        newpane = null;
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane,800,600);
        islands = new ArrayList<>();
        newpane = pausemenu();
        addobjects(null);
        this.hero = new Hero(mainPane, new Position(75,300-50), 50, 50 ,1.2 );


        //hero = new Panda_Helmet();

    }
    private AnchorPane pausemenu(){
        ImageView background = new ImageView(new File(image).toURI().toString());
        background.setFitHeight(600);
        background.setFitWidth(800);
        mainPane.getChildren().add(background);
        PauseButton pause = new PauseButton();
        pause.setLayoutX(400);
        pause.setLayoutY(20);
        mainPane.getChildren().add(pause);
        AnchorPane menu = new AnchorPane();
        menu.setPrefHeight(200);
        menu.setPrefWidth(200);
        menu.setLayoutX(300);
        menu.setLayoutY(150);
        Image bg = new Image(new File(this.bg).toURI().toString(),200,200,false,true);
        BackgroundImage image = new BackgroundImage(bg, BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,null);
        menu.setBackground(new Background(image));
        ResumeButton resume = new ResumeButton();
        resume.setLayoutX(70);
        resume.setLayoutY(80);
        menu.getChildren().add(resume);
        pausegame(pause);
        startgame(resume,pause);
        Homebutton home = new Homebutton(stage);
        home.setLayoutX(20);
        home.setLayoutY(35);
        menu.getChildren().add(home);
        RestartButton restart = new RestartButton(stage);
        restart.setLayoutX(120);
        restart.setLayoutY(35);
        menu.getChildren().add(restart);
        MusicButton music = new MusicButton(null);
        music.setLayoutX(20);
        music.setLayoutY(125);
        menu.getChildren().add(music);
        SaveButton save = new SaveButton(this.stage);
        save.setLayoutX(120);
        save.setLayoutY(125);
        menu.getChildren().add(save);
        return menu;
    }

    private void addobjects(MediaPlayer player){
        Island smallIsland1 = new Island(islandSmall, mainPane, new Position(75,350), 200, 100,0);
//        Island mediumIsland1 = new Island(islandMedium, mainPane, new Position(500,500), 200, 50);
        Island largeIsland1 = new Island(islandLarge, mainPane, new Position(325,350), 450, 150 , 0.3);
        Standard_Green_Orc green_orc1 = new Standard_Green_Orc(mainPane, new Position(350,350-50), 60, 50,0.8, largeIsland1);
        this.game_objects.add(green_orc1);
        Standard_Red_Orc red_orc1 = new Standard_Red_Orc(mainPane, new Position(550,350-50), 50, 50,1, largeIsland1);
        TNT tnt1 = new TNT(mainPane, new Position(600,300-75),75,75, 0.4, largeIsland1);
        this.game_objects.add(red_orc1);
        this.game_objects.add(tnt1);
        islands.add(smallIsland1);
        islands.add(largeIsland1);
        this.pos = 250;
        this.chest = new Coin_Chest(mainPane,new Position(190,350-40),50,40);
    }
    public void start(){
        stage.setScene(this.mainScene);
        stage.show();
        KeyFrame frame = new KeyFrame(Duration.millis(10), e->{
            moveHero(this.hero);
            moveIsland(islands.get(1));
            for (Game_Objects game_object: this.game_objects){
                if (game_object instanceof Orc){
                    moveOrc((Orc) game_object , ((Orc) game_object).getIslandofResidence());
                }
                else if (game_object instanceof gameObstacles){
                    moveTNT((TNT)game_object, ((TNT)game_object).getIslandofResidence());
                }
            }
        }
        );
        this.time = new Timeline(frame);
        time.setCycleCount(Timeline.INDEFINITE);
        time.play();
    }

    private void moveIsland(Island island) {
        //Island largeIsland1 = new Island(islandLarge, mainPane, new Position(325,300), 450, 150 , 0.5);
        //hero.getHero().setY(hero.getHero().getY()-hero.getSpeed());
        island.setPositionY(island.getPosition().getY()-island.getSpeed());
        if(island.getPosition().getY()>=370 || island.getPosition().getY()<=270){
            double speed = island.getSpeed();
            island.setSpeed(-speed);
        }

    }
    private void moveHero(Hero hero){
        hero.getHero().setY(hero.getHero().getY()-hero.getSpeed());
        if(hero.getHero().getY()>=350-50 || hero.getHero().getY()<=250-50){
            double speed = hero.getSpeed();
            hero.setSpeed(-speed);
        }
    }

    private void moveOrc(Orc orc , Island island){
        //Island largeIsland1 = new Island(islandLarge, mainPane, new Position(325,300), 450, 150 , 0.4);
        //Standard_Red_Orc red_orc1 = new Standard_Red_Orc(mainPane, new Position(550,300-50), 50, 50,2);
        if(orc.getOrc().getY()-orc.getSpeed()>=island.getPosition().getY()-50){
            orc.getOrc().setY(island.getPosition().getY()-50);
        }
        if(orc.getOrc().getY()-orc.getSpeed()<=island.getPosition().getY()-100){
            orc.getOrc().setY(island.getPosition().getY()-100);
        }
        else{
            orc.getOrc().setY(orc.getOrc().getY()-orc.getSpeed());
        }
        if(orc.getOrc().getY()>=island.getPosition().getY()-50 || orc.getOrc().getY()<=island.getPosition().getY()-100){
            double speed = orc.getSpeed();
            orc.setSpeed(-speed);
        }
        if(orc.getOrc().getY()>=island.getPosition().getY()-50){
            orc.setinitpos(island.getPosition().getY()-50);
        }
    }

    private void moveTNT(TNT tnt , Island island){
        tnt.setPositionY(island.getPosition().getY()-island.getSpeed()-70);
        if(tnt.getPosition().getY()>=350 || tnt.getPosition().getY()<=300){
            double speed = tnt.getSpeed();
            tnt.setSpeed(-speed);
        }
    }

    public void pausegame(PauseButton pause){
        pause.setOnAction(e ->{
            chest.setFlag(1);
            time.pause();
            mainPane.getChildren().remove(pause);
            mainPane.getChildren().add(newpane);
        });
    }
    public void startgame(Button button , PauseButton pause){
        button.setOnAction(e ->{
            chest.setFlag(0);
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
