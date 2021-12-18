package com.example.game;

import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class LoadPage {
    private final AnchorPane mainPane;
    private final Scene mainScene;
    private final String spbutton = "src/main/resources/com/example/game/images/spearbutton.png";
    private final String swbutton = "src/main/resources/com/example/game/images/swordbutton.png";
    private Timeline time;
    private AnchorPane newpane;
    private final Stage stage;
    private final ArrayList<Game_Objects> gameObjects;
    private final Hero hero;
    private final ArrayList<Island> islands;
//    private Coin_Chest chest;
    private ImageView sword,lance;
    private final MediaPlayer orcjump;
    private final MediaPlayer herojump;
    int score;

    LoadPage(Stage stage) {

        String heroJumpingAudioPath = "src/main/resources/com/example/game/audios/herojump.wav";
        Media heroJumpingAudio = new Media(new File(heroJumpingAudioPath).toURI().toString());
        herojump = new MediaPlayer(heroJumpingAudio);
        MediaView herojumpview = new MediaView(herojump);
        herojump.setVolume(0.4);
        herojump.setCycleCount(1);
        String orcJumpingAudioPath = "src/main/resources/com/example/game/audios/orcjump.mp3";
        Media orcJumpingAudio = new Media(new File(orcJumpingAudioPath).toURI().toString());
        orcjump = new MediaPlayer(orcJumpingAudio);
        MediaView orcjumpview = new MediaView(orcjump);
        orcjump.setVolume(0);
        orcjump.setCycleCount(1);
        this.stage = stage;
        newpane = null;
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane,800,600);
        mainPane.getChildren().add(herojumpview);
        mainPane.getChildren().add(orcjumpview);
        islands = new ArrayList<>();
        gameObjects = new ArrayList<>();
        String image = "src/main/resources/com/example/game/images/bg2.jpg";
        ImageView background = new ImageView(new File(image).toURI().toString());
        background.setFitHeight(600);
        background.setFitWidth(800);
        mainPane.getChildren().add(background);
        addObjectsonScreen();
        newpane = pauseMenu();
        this.hero = new Hero(mainPane, new Position(75,300-50), 50, 50 ,1.2);
        WeaponButton sword = new WeaponButton("Sword",25,525);
        WeaponButton lance = new WeaponButton("Lance",100,525);
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
        moveScreenButton moveScreenButton = new moveScreenButton(500, 50, islands, gameObjects);
        mainPane.getChildren().add(moveScreenButton);
        mainPane.getChildren().add(t);
    }
    private AnchorPane pauseMenu(){
        PauseButton pause = new PauseButton();
        pause.setLayoutX(400);
        pause.setLayoutY(20);
        mainPane.getChildren().add(pause);
        AnchorPane menu = new AnchorPane();
        menu.setPrefHeight(200);
        menu.setPrefWidth(200);
        menu.setLayoutX(300);
        menu.setLayoutY(150);
        String bg1 = "src/main/resources/com/example/game/images/menuupdated.png";
        Image bg = new Image(new File(bg1).toURI().toString(),200,200,false,true);
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

    private void addObjectsonScreen(){
        System.out.println("Inside Add Objects");
        for (int i = 0; i < 10; i++){
            Island smallIsland = new Island("Small", mainPane, new Position(75 + 1225*i,350), 200, 100,0);
            Island mediumIsland = new Island("Medium", mainPane, new Position(325+ 1225*i,350), 350, 125, 0);
            Island largeIsland = new Island("Large", mainPane, new Position(725+ 1225*i,350), 450, 150 , 0.3);
            islands.add(smallIsland);
            islands.add(mediumIsland);
            islands.add(largeIsland);
        }
        int count = 0;
        for (Island island: islands){
            System.out.println(island.getIslandType());
        }
        for (Island island: islands){
            if (count == 0){
                count++;
                continue;
            }
            Position islandPosition = island.getPosition();
            if (island.getIslandType().equals("Small")){
                generateIslandObjects(island, islandPosition, 1);
            }
            else if (island.getIslandType().equals("Medium")){
                generateIslandObjects(island, islandPosition, 1);
            }
            else{
                generateIslandObjects(island, islandPosition, 2);
            }

        }

    }

    private void generateIslandObjects(Island island, Position islandPosition, int maxQuantityObjectsOnIsland){
        String[] gameObjects = {"TNT", "CoinChest", "Standard_Green_Orc", "Standard_Red_Orc"};
        int placedSoFar = 0;
        for (int i = 0; i < maxQuantityObjectsOnIsland; i++){
            Random rand = new Random();
            String objectChosen = gameObjects[rand.nextInt(gameObjects.length)];
            if (objectChosen.equals("TNT")){
                TNT tnt = new TNT(mainPane, new Position(islandPosition.getX() + 50 + placedSoFar*150,islandPosition.getY()-50),50,50, 0.4, island);
                placedSoFar++;
                this.gameObjects.add(tnt);
            }
            else if (objectChosen.equals("CoinChest")){
                Coin_Chest chest = new Coin_Chest(mainPane,new Position(islandPosition.getX() + 50 + placedSoFar*150,islandPosition.getY()-40),50,40);
                placedSoFar++;
                this.gameObjects.add(chest);
            }
            else if (objectChosen.equals("Standard_Green_Orc")){
                Standard_Green_Orc greenOrc = new Standard_Green_Orc(mainPane, new Position(islandPosition.getX() + 50 + placedSoFar*150,islandPosition.getY()-50), 60, 50,0.8, island);
                placedSoFar++;
                this.gameObjects.add(greenOrc);
            }
            else if (objectChosen.equals("Standard_Red_Orc")){
                Standard_Red_Orc redOrc = new Standard_Red_Orc(mainPane, new Position(islandPosition.getX() + 50 + placedSoFar*150,islandPosition.getY()-50), 50, 50,1, island);
                placedSoFar++;
                this.gameObjects.add(redOrc);
            }
            System.out.println(gameObjects[rand.nextInt(gameObjects.length)]);
        }
    }

    public void start(){
        stage.setScene(this.mainScene);
        stage.show();
//        KeyFrame frame = new KeyFrame(Duration.millis(10), e->{
//            moveHero(this.hero);
//            moveIsland(islands.get(1));
//            for (gameObjects game_object: this.gameObjects){
//                if (game_object instanceof Orc){
//                    moveOrc((Orc) game_object , ((Orc) game_object).getIslandofResidence());
//                }
//                else if (game_object instanceof gameObstacles){
//                    moveTNT((TNT)game_object, ((TNT)game_object).getIslandofResidence());
//                }
//            }
//        }
//        );
//        this.time = new Timeline(frame);
//        time.setCycleCount(Timeline.INDEFINITE);
//        time.play();
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
        orc.getOrc().setY(Math.max(orc.getOrc().getY() - orc.getSpeed(), island.getPosition().getY() - 100));
        if(orc.getOrc().getY()>=island.getPosition().getY()-50 || orc.getOrc().getY()<=island.getPosition().getY()-100){
            double speed = orc.getSpeed();
            orc.setSpeed(-speed);
        }
        if(orc.getOrc().getY()>=island.getPosition().getY()-50){
            orc.setInitialPosition(island.getPosition().getY()-50);
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
//        pause.setOnAction(e ->{
//            chest.setFlag(1);
//            tnt.setFlag(1);
//            time.pause();
//            mainPane.getChildren().remove(pause);
//            mainPane.getChildren().add(newpane);
//        });
    }
    public void startgame(Button button , PauseButton pause){
//        button.setOnAction(e ->{
//            chest.setFlag(0);
//            tnt.setFlag(0);
//            time.play();
//            mainPane.getChildren().add(pause);
//            mainPane.getChildren().remove(newpane);
//        });
    }


    public AnchorPane getMainPane() {
        return mainPane;
    }

    public Scene getMainScene() {
        return mainScene;
    }





}
