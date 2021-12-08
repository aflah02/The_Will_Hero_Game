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
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;

public class LoadPage {
    private final AnchorPane mainPane;
    private final Scene mainScene;
    private String image = "src/main/resources/com/example/game/images/bg2.jpg";
    private String bg = "src/main/resources/com/example/game/images/menuupdated.png";
    private String islandSmall = "src/main/resources/com/example/game/images/T_Islands_07.png";
    private String islandMedium = "src/main/resources/com/example/game/images/T_Islands_09.png";
    private String islandLarge = "src/main/resources/com/example/game/images/T_Islands_01.png";
    private String spbutton = "src/main/resources/com/example/game/images/spearbutton.png";
    private String swbutton = "src/main/resources/com/example/game/images/swordbutton.png";
    private String image1 = "src/main/resources/com/example/game/images/Spruce1.png";
    private String image2 = "src/main/resources/com/example/game/images/ruins4.png";
    private String image4 = "src/main/resources/com/example/game/images/Tree7.png";
    private Timeline time;
    private AnchorPane newpane;
    private Stage stage;
    private final ArrayList<Game_Objects> game_objects = new ArrayList<>();
    private Hero hero;
    private ArrayList<Island> islands;
    private double pos;
    private Coin_Chest chest;
    private ImageView sword,lance;
    private MediaPlayer orcjump , herojump;
    private String heroj = "src/main/resources/com/example/game/audios/orcjump.mp3";
    private String orcj = "src/main/resources/com/example/game/audios/herojump.wav";
    int score;
    private TNT tnt;

    LoadPage(Stage stage) {
        Media herojum = new Media(new File(heroj).toURI().toString());
        herojump = new MediaPlayer(herojum);
        MediaView herojumpview = new MediaView(herojump);
        herojump.setCycleCount(1);
        Media orcjum = new Media(new File(orcj).toURI().toString());
        orcjump = new MediaPlayer(orcjum);
        MediaView orcjumpview = new MediaView(orcjump);
        orcjump.setCycleCount(1);
        this.stage = stage;
        newpane = null;
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane,800,600);
        mainPane.getChildren().add(herojumpview);
        mainPane.getChildren().add(orcjumpview);
        islands = new ArrayList<>();
        ImageView background = new ImageView(new File(image).toURI().toString());
        background.setFitHeight(600);
        background.setFitWidth(800);
        mainPane.getChildren().add(background);
        addobjects(null);
        newpane = pausemenu();
        this.hero = new Hero(mainPane, new Position(75,300-50), 50, 50 ,1.2 );
        /*
        sword = new ImageView(new File(swbutton).toURI().toString());
        sword.setFitHeight(50);
        sword.setFitWidth(50);
        sword.setLayoutX(25);
        sword.setLayoutY(525);
        mainPane.getChildren().add(sword);
        lance = new ImageView(new File(spbutton).toURI().toString());
        lance.setFitHeight(50);
        lance.setFitWidth(50);
        lance.setLayoutX(85);
        lance.setLayoutY(525);
        mainPane.getChildren().add(lance);
        */
        WeaponButton sword = new WeaponButton(0,25,525);
        WeaponButton lance = new WeaponButton(1,100,525);
        mainPane.getChildren().add(sword);
        mainPane.getChildren().add(lance);
        Text lancet = new Text();
        String score = Integer.toString(sword.getLevel());
        lancet.setText(score);
        lancet.setFont(Font.font ("Verdana", 10));
        lancet.setFill(Color.YELLOW);
        lancet.setX(60);
        lancet.setY(570);
        mainPane.getChildren().add(lancet);
        Text swordt = new Text();
        score = Integer.toString(lance.getLevel());
        swordt.setText(score);
        swordt.setFont(Font.font ("Verdana", 10));
        swordt.setFill(Color.YELLOW);
        swordt.setX(135);
        swordt.setY(570);
        mainPane.getChildren().add(swordt);
        this.score = 0;
        Text t = new Text();
        score = Integer.toString(this.score);
        t.setText(score);
        t.setFont(Font.font ("Verdana", 70));
        t.setFill(Color.WHITE);
        t.setX(410);
        t.setY(130);
        mainPane.getChildren().add(t);

    }
    private AnchorPane pausemenu(){

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
        resume.setLayoutX(65);
        resume.setLayoutY(100);
        menu.getChildren().add(resume);
        pausegame(pause);
        startgame(resume,pause);
        Homebutton home = new Homebutton(stage);
        home.setLayoutX(10);
        home.setLayoutY(60);
        menu.getChildren().add(home);
        RestartButton restart = new RestartButton(stage);
        restart.setLayoutX(120);
        restart.setLayoutY(60);
        menu.getChildren().add(restart);
        MusicButton music = new MusicButton(null);
        music.setLayoutX(10);
        music.setLayoutY(135);
        menu.getChildren().add(music);
        SaveButton save = new SaveButton(this.stage);
        save.setLayoutX(120);
        save.setLayoutY(135);
        menu.getChildren().add(save);
        return menu;
    }

    private void addobjects(MediaPlayer player){
        ArrayList<ImageView> island1 = new ArrayList<>();
        ArrayList<ImageView> island2 = new ArrayList<>();
        //Imageviews for islands
        ImageView first = new ImageView(new File(image4).toURI().toString());
        first.setX(725);
        first.setFitHeight(170);
        first.setFitWidth(50);
        ImageView second = new ImageView(new File(image2).toURI().toString());
        second.setX(240);
        second.setFitHeight(100);
        second.setFitWidth(30);
        island2.add(first);
        island1.add(second);
        ImageView fourth = new ImageView(new File(image1).toURI().toString());
        fourth.setX(475);
        fourth.setFitHeight(200);
        fourth.setFitWidth(50);
        island2.add(fourth);
        //Imageview for islands
        Island smallIsland1 = new Island(islandSmall, mainPane, new Position(75,350), 200, 100,0,island1);
//        Island mediumIsland1 = new Island(islandMedium, mainPane, new Position(500,500), 200, 50);
        Island largeIsland1 = new Island(islandLarge, mainPane, new Position(325,350), 450, 150 , 0.3,island2);
        Standard_Green_Orc green_orc1 = new Standard_Green_Orc(mainPane, new Position(350,350-50), 60, 50,0.8, largeIsland1);
        this.game_objects.add(green_orc1);
        Standard_Red_Orc red_orc1 = new Standard_Red_Orc(mainPane, new Position(550,350-50), 50, 50,1, largeIsland1);
        this.tnt = new TNT(mainPane, new Position(650,300-50),50,50, 0.4, largeIsland1);
        this.game_objects.add(red_orc1);
        this.game_objects.add(this.tnt);
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
        if(hero.getHero().getY()-hero.getSpeed()>=350-50){
            hero.getHero().setY(350-50);
            herojump.play();
            herojump.seek(Duration.ZERO);
        }
        else{
            hero.getHero().setY(hero.getHero().getY()-hero.getSpeed());
        }
        if(hero.getHero().getY()>=350-50 || hero.getHero().getY()<=275-50){
            double speed = hero.getSpeed();
            hero.setSpeed(-speed);
        }
    }

    private void moveOrc(Orc orc , Island island){
        //Island largeIsland1 = new Island(islandLarge, mainPane, new Position(325,300), 450, 150 , 0.4);
        //Standard_Red_Orc red_orc1 = new Standard_Red_Orc(mainPane, new Position(550,300-50), 50, 50,2);
        if(orc.getOrc().getY()-orc.getSpeed()>=island.getPosition().getY()-50){
            orc.getOrc().setY(island.getPosition().getY()-50);
            orcjump.play();
            orcjump.seek(Duration.ZERO);
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
        tnt.setPositionY(island.getPosition().getY()-island.getSpeed()-45);
        if(tnt.getPosition().getY()>=325 || tnt.getPosition().getY()<=275){
            double speed = tnt.getSpeed();
            tnt.setSpeed(-speed);
        }
    }

    public void pausegame(PauseButton pause){
        pause.setOnAction(e ->{
            chest.setFlag(1);
            tnt.setFlag(1);
            time.pause();
            mainPane.getChildren().remove(pause);
            mainPane.getChildren().add(newpane);
        });
    }
    public void startgame(Button button , PauseButton pause){
        button.setOnAction(e ->{
            chest.setFlag(0);
            tnt.setFlag(0);
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
