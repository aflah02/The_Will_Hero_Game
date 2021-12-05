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
    private String image = "src/main/resources/com/example/game/images/bg.gif";
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
    private Chest_Coin chest;
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
        this.chest = new Chest_Coin(mainPane,new Position(200,300-50),50,50);

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
        RestartButton restart = new RestartButton(stage);
        restart.setLayoutX(200);
        restart.setLayoutY(200);
        menu.getChildren().add(restart);
        return menu;
    }

    private void addobjects(MediaPlayer player){
        Standard_Green_Orc green_orc1 = new Standard_Green_Orc(mainPane, new Position(350,300-50), 50, 50,0.75);
        this.game_objects.add(green_orc1);
        Standard_Red_Orc red_orc1 = new Standard_Red_Orc(mainPane, new Position(550,300-50), 50, 50,1);
        this.game_objects.add(red_orc1);
        Island smallIsland1 = new Island(islandSmall, mainPane, new Position(75,300), 200, 100,0);
//        Island mediumIsland1 = new Island(islandMedium, mainPane, new Position(500,500), 200, 50);
        Island largeIsland1 = new Island(islandLarge, mainPane, new Position(325,300), 450, 150 , 0.4);
        islands.add(smallIsland1);
        islands.add(largeIsland1);
        this.pos = 250;
    }
    public void start(){
        stage.setScene(this.mainScene);
        stage.show();

        KeyFrame frame = new KeyFrame(Duration.millis(10), e->{
            moveHero(this.hero);
            moveIsland(islands.get(1));
            this.chest.transition(mainPane);
            for (Game_Objects game_object: this.game_objects){
                if (game_object instanceof Orc){
                    moveOrc((Orc) game_object , islands.get(1));
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
        if(island.getPosition().getY()>=300 || island.getPosition().getY()<=250){
            double speed = island.getSpeed();
            island.setSpeed(-speed);
        }

    }
    private void moveHero(Hero hero){
        hero.getHero().setY(hero.getHero().getY()-hero.getSpeed());
        if(hero.getHero().getY()>=300-50 || hero.getHero().getY()<=200-50){
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
        else{
            orc.getOrc().setY(orc.getOrc().getY()-orc.getSpeed());
        }
        if(orc.getOrc().getY()>=island.getPosition().getY()-50 || orc.getOrc().getY()<=orc.getinitpos()-150){
            double speed = orc.getSpeed();
            orc.setSpeed(-speed);
        }
        if(orc.getOrc().getY()>=island.getPosition().getY()-50){
            orc.setinitpos(island.getPosition().getY()-50);
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
