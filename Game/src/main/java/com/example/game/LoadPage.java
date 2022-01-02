package com.example.game;

import javafx.animation.AnimationTimer;
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
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class LoadPage {
    private int OrcEncounterCount = 0;
    private int OrcKillCount = 0;
    private int TNTBurstCount = 0;
    private int SwordsCollected = 0;
    private int SpearsCollected = 0;
    private int CoinChestsOpened = 0;
    final int islandLocationfromTopofScreen = 450;
    private final double MAX_FALLING_HEIGHT = 600;
    private int ISLAND_COUNT;
    private int GAME_OBJECT_COUNT;
    private transient PauseButton pauseButton;
    private final transient AnchorPane mainPane;
    private final Scene mainScene;
    private final String spbutton = "src/main/resources/com/example/game/images/spearbutton.png";
    private final String swbutton = "src/main/resources/com/example/game/images/swordbutton.png";
    private transient Timeline time;
    private transient AnchorPane newpane;
    private transient AnchorPane abyssPane;
    private final Stage stage;
    private final ArrayList<Game_Objects> gameObjects;
    private final Player player;
    private final Hero hero;
    private final ArrayList<Island> islands;
    private transient ImageView sword,lance;
    private transient final ArrayList<MediaPlayer> players;
    private transient final MediaPlayer orcjump,orcdie;
    private transient final MediaPlayer herojump,herodie,herorevive;
    private transient final MediaPlayer tntburst , weaponchestsound , coincchestsound;
    private int score;
    private Button pause,start,move;
    private transient WeaponButton swordbutton,lancebutton;
    static int RecordingLength;
    private Long startTime;
    private String HelmetName;
    private int orcflag,orccounter;
    private int shadowflag;
    private Shadow shadow;

    LoadPage(Stage stage, SaveFileReturn saveFileReturn, AnchorPane anchorPane) throws IOException {
        orccounter = 0;
        this.shadow = null;
        orcflag = 0;
        shadowflag =0;
        PrintWriter writer = new PrintWriter("src\\main\\java\\com\\example\\game\\heroLocations.txt");
        writer.print("");
        writer.close();
        this.player = saveFileReturn.getPlayer();
        this.swordbutton = saveFileReturn.getSwordButton();
        this.lancebutton = saveFileReturn.getLanceButton();
        RecordingLength = 5;
//        String[] cmd = {"src\\main\\java\\com\\example\\game\\exec.bat", "Will Hero"};
//        Process p = Runtime.getRuntime().exec(cmd);
//        System.out.println(p);
        System.out.println("hello");
        this.startTime = java.time.Instant.now().getEpochSecond();
        players = new ArrayList<>();
        String heroJumpingAudioPath = "src/main/resources/com/example/game/audios/jump.mp3";
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
        mainPane = anchorPane;
        //............................
        String audiopath1 = "src/main/resources/com/example/game/audios/chestcollect.mp3";
        Media  media1= new Media(new File(audiopath1).toURI().toString());
        MediaPlayer player1 = new MediaPlayer(media1);
        MediaView coinchestsound = new MediaView(player1);
        player1.setVolume(0.4);
        player1.setCycleCount(1);
        this.coincchestsound = player1;
        mainPane.getChildren().add(coinchestsound);
        String audiopath2 = "src/main/resources/com/example/game/audios/chestopen.mp3";
        Media  media2= new Media(new File(audiopath2).toURI().toString());
        MediaPlayer player2 = new MediaPlayer(media2);
        this.weaponchestsound = player2;
        MediaView weaponchestsound = new MediaView(player2);
        player2.setVolume(0.4);
        player2.setCycleCount(1);
        mainPane.getChildren().add(weaponchestsound);
        String audiopath3 = "src/main/resources/com/example/game/audios/herodie.mp3";
        Media  media3= new Media(new File(audiopath3).toURI().toString());
        MediaPlayer player3 = new MediaPlayer(media3);
        this.herodie = player3;
        MediaView herodiesound = new MediaView(player3);
        player3.setVolume(0.4);
        player3.setCycleCount(1);
        mainPane.getChildren().add(herodiesound);
        String audiopath4 = "src/main/resources/com/example/game/audios/herorevive.mp3";
        Media  media4= new Media(new File(audiopath4).toURI().toString());
        MediaPlayer player4 = new MediaPlayer(media4);
        this.herorevive = player4;
        MediaView herorevivesound = new MediaView(player4);
        player4.setVolume(0.4);
        player4.setCycleCount(1);
        mainPane.getChildren().add(herorevivesound);
        String audiopath5 = "src/main/resources/com/example/game/audios/orcdie.mp3";
        Media  media5= new Media(new File(audiopath5).toURI().toString());
        MediaPlayer player5 = new MediaPlayer(media5);
        this.orcdie = player5;
        MediaView orcdiesound = new MediaView(player5);
        player5.setVolume(0.4);
        player5.setCycleCount(1);
        mainPane.getChildren().add(orcdiesound);
        String audiopath6 = "src/main/resources/com/example/game/audios/tntburst.mp3";
        Media  media6= new Media(new File(audiopath6).toURI().toString());
        MediaPlayer player6 = new MediaPlayer(media6);
        MediaView tntburstsound = new MediaView(player6);
        player6.setVolume(0.4);
        player6.setCycleCount(1);
        this.tntburst = player6;
        mainPane.getChildren().add(tntburstsound);
        players.add(tntburst);
        players.add(herodie);
        players.add(herorevive);
        players.add(orcdie);
        players.add(this.weaponchestsound);
        players.add(coincchestsound);
        //............................
        mainScene = new Scene(mainPane,800,600);
        mainPane.getChildren().add(herojumpview);
        mainPane.getChildren().add(orcjumpview);
        islands = saveFileReturn.islandArrayList;
        gameObjects = saveFileReturn.gameObjectsArrayList;
        this.hero = this.player.getHero();
        Text lancet = this.hero.getLanceText();
        Text swordt = this.hero.getSwordText();
//        this.swordbutton = new WeaponButton("Sword",25,525,hero);
//        this.lancebutton = new WeaponButton("Lance",100,525,hero);
//        swordbutton.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//                swordbutton.setactive();
//                lancebutton.setinactive();
//
//            }
//        });
//        lancebutton.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//                lancebutton.setactive();
//                swordbutton.setinactive();
//
//            }
//        });


        players.add(herojump);
        players.add(orcjump);
        newpane = pauseMenu();
        abyssPane = reviveMenu();
//        String score = Integer.toString(0);
//        lancet.setText(score);
        lancet.setFont(Font.font ("Verdana", 10));
        lancet.setFill(Color.YELLOW);
        lancet.setX(135);
        lancet.setY(570);

//        score = Integer.toString(0);
//        swordt.setText(score);
        swordt.setFont(Font.font ("Verdana", 10));
        swordt.setFill(Color.YELLOW);
        swordt.setX(60);
        swordt.setY(570);
        moveScreenButton moveScreenButton = new moveScreenButton(0, 0, islands, gameObjects, hero, startTime);
        this.move = moveScreenButton;
        mainPane.getChildren().remove(this.hero.getHero());
        if (this.hero.getHero() == null){
            System.out.println("Null hero");
        }
        else{
            mainPane.getChildren().add(this.hero.getHero());
        }

        mainPane.getChildren().add(move);
        mainPane.getChildren().add(pause);
        mainPane.getChildren().add(swordbutton);
        mainPane.getChildren().add(lancebutton);
        mainPane.getChildren().add(lancet);
        mainPane.getChildren().add(swordt);
    }
    LoadPage(Stage stage, String HelmetName) throws IOException, InterruptedException {
        shadowflag = 1;
        PrintWriter writer = new PrintWriter("src\\main\\java\\com\\example\\game\\heroLocations.txt");
        writer.print("");
        writer.close();
        this.HelmetName = HelmetName;
        RecordingLength = 5;
        String[] cmd = {"src\\main\\java\\com\\example\\game\\exec.bat", "Will Hero"};
        Process p = Runtime.getRuntime().exec(cmd);
        System.out.println(p);
        System.out.println("hello");
        this.startTime = java.time.Instant.now().getEpochSecond();
        players = new ArrayList<>();
        String heroJumpingAudioPath = "src/main/resources/com/example/game/audios/jump.mp3";
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
        //............................
        String audiopath1 = "src/main/resources/com/example/game/audios/chestcollect.mp3";
        Media  media1= new Media(new File(audiopath1).toURI().toString());
        MediaPlayer player1 = new MediaPlayer(media1);
        MediaView coinchestsound = new MediaView(player1);
        player1.setVolume(0.4);
        player1.setCycleCount(1);
        this.coincchestsound = player1;
        mainPane.getChildren().add(coinchestsound);
        String audiopath2 = "src/main/resources/com/example/game/audios/chestopen.mp3";
        Media  media2= new Media(new File(audiopath2).toURI().toString());
        MediaPlayer player2 = new MediaPlayer(media2);
        this.weaponchestsound = player2;
        MediaView weaponchestsound = new MediaView(player2);
        player2.setVolume(0.4);
        player2.setCycleCount(1);
        mainPane.getChildren().add(weaponchestsound);
        String audiopath3 = "src/main/resources/com/example/game/audios/herodie.mp3";
        Media  media3= new Media(new File(audiopath3).toURI().toString());
        MediaPlayer player3 = new MediaPlayer(media3);
        this.herodie = player3;
        MediaView herodiesound = new MediaView(player3);
        player3.setVolume(0.4);
        player3.setCycleCount(1);
        mainPane.getChildren().add(herodiesound);
        String audiopath4 = "src/main/resources/com/example/game/audios/herorevive.mp3";
        Media  media4= new Media(new File(audiopath4).toURI().toString());
        MediaPlayer player4 = new MediaPlayer(media4);
        this.herorevive = player4;
        MediaView herorevivesound = new MediaView(player4);
        player4.setVolume(0.4);
        player4.setCycleCount(1);
        mainPane.getChildren().add(herorevivesound);
        String audiopath5 = "src/main/resources/com/example/game/audios/orcdie.mp3";
        Media  media5= new Media(new File(audiopath5).toURI().toString());
        MediaPlayer player5 = new MediaPlayer(media5);
        this.orcdie = player5;
        MediaView orcdiesound = new MediaView(player5);
        player5.setVolume(0.4);
        player5.setCycleCount(1);
        mainPane.getChildren().add(orcdiesound);
        String audiopath6 = "src/main/resources/com/example/game/audios/tntburst.mp3";
        Media  media6= new Media(new File(audiopath6).toURI().toString());
        MediaPlayer player6 = new MediaPlayer(media6);
        MediaView tntburstsound = new MediaView(player6);
        player6.setVolume(0.4);
        player6.setCycleCount(1);
        this.tntburst = player6;
        mainPane.getChildren().add(tntburstsound);
        players.add(tntburst);
        players.add(herodie);
        players.add(herorevive);
        players.add(orcdie);
        players.add(this.weaponchestsound);
        players.add(coincchestsound);
        //............................
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
        Text lancet = new Text();
        Text swordt = new Text();
        this.hero = new Hero(mainPane, new Position(75,300-50), 60, 60 ,1.2, swordt, lancet, HelmetName, 0, null, null);
        this.player = new Player(this.hero);
        this.swordbutton = new WeaponButton("Sword",25,525,hero);
        this.lancebutton = new WeaponButton("Lance",100,525,hero);
        swordbutton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                swordbutton.setactive();
                lancebutton.setinactive();

            }
        });
        lancebutton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                lancebutton.setactive();
                swordbutton.setinactive();

            }
        });

        addObjectsonScreen();
        players.add(herojump);
        players.add(orcjump);
        newpane = pauseMenu();
        abyssPane = reviveMenu();
        String score = Integer.toString(0);
        lancet.setText(score);
        lancet.setFont(Font.font ("Verdana", 10));
        lancet.setFill(Color.YELLOW);
        lancet.setX(60);
        lancet.setY(570);

        score = Integer.toString(0);
        swordt.setText(score);
        swordt.setFont(Font.font ("Verdana", 10));
        swordt.setFill(Color.YELLOW);
        swordt.setX(135);
        swordt.setY(570);
        moveScreenButton moveScreenButton = new moveScreenButton(0, 0, islands, gameObjects, hero, startTime);
        this.move = moveScreenButton;
        Shadow shadow = new Shadow(new Position(75,hero.getHero().getY()), hero.getWidth(), hero.getHeight());
        mainPane.getChildren().add(shadow.getshadow());
        this.shadow = shadow;
        mainPane.getChildren().remove(hero.getHero());
        mainPane.getChildren().add(hero.getHero());
        //saveGameDataToFile(new File("SaveFiles/save.ser"));
        mainPane.getChildren().add(move);
        mainPane.getChildren().add(pause);
        mainPane.getChildren().add(swordbutton);
        mainPane.getChildren().add(lancebutton);
        mainPane.getChildren().add(lancet);
        mainPane.getChildren().add(swordt);
    }
    private AnchorPane pauseMenu(){
        pauseButton = new PauseButton();
        this.pause = pauseButton;
        pauseButton.setLayoutX(400);
        pauseButton.setLayoutY(20);
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
        this.start = resume;
        resume.setLayoutX(65);
        resume.setLayoutY(100);
        menu.getChildren().add(resume);
        pausegame(pauseButton);
        startgame(resume,pauseButton);
        Homebutton home = new Homebutton(stage);
        home.setLayoutX(10);
        home.setLayoutY(60);
        menu.getChildren().add(home);
        RestartButton restart = new RestartButton(stage, HelmetName);
        restart.setLayoutX(120);
        restart.setLayoutY(60);
        menu.getChildren().add(restart);
        MusicButton music = new MusicButton(players);
        music.setLayoutX(10);
        music.setLayoutY(135);
        menu.getChildren().add(music);
        SaveButton save = new SaveButton(this.stage);
        save.setLayoutX(120);
        save.setLayoutY(135);
        menu.getChildren().add(save);
        save.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override

            public void handle(MouseEvent mouseEvent) {
                System.out.println("Saved");
                File saveFile = new File("SaveFiles/save.ser");
                saveGameDataToFile(saveFile);
            }
        });
        return menu;
    }

    private AnchorPane reviveMenu(){
        AnchorPane Menu = new AnchorPane();
        Menu.setPrefHeight(100);
        Menu.setPrefWidth(200);
        Menu.setLayoutX(300);
        Menu.setLayoutY(200);
        String bg1 = "src/main/resources/com/example/game/images/saveme.png";
        Image bg = new Image(new File(bg1).toURI().toString());
        ImageView background = new ImageView(bg);
        background.setFitHeight(100);
        background.setFitWidth(200);
        background.setX(0);
        background.setY(0);
        Menu.getChildren().add(background);
        Button end_button = new Button();
        end_button.setMinHeight(30);
        end_button.setMinWidth(50);
        String Styleend = "-fx-background-color:RED; -fx-background-size: cover;-fx-border-color: white; -fx-border-style: solid; -fx-border-width: 3;";
        String buttonimg = "src/main/resources/com/example/game/images/danger.png";
        end_button.setStyle(Styleend);
        ImageView buttonimage = new ImageView(new File(buttonimg).toURI().toString());
        buttonimage.setFitWidth(50);
        buttonimage.setFitHeight(30);
        end_button.setGraphic(buttonimage);
        end_button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                hero.revivehero();
                mainPane.getChildren().remove(Menu);
                mainPane.getChildren().add(resultmenu());
            }
        });
        end_button.setLayoutX(20);
        end_button.setLayoutY(50);
        //New Button Revive_Button to be made TT_TT;
        Button revive_button = new Button();
        revive_button.setMinHeight(30);
        revive_button.setMinWidth(50);
        String Stylerevive = "-fx-background-color:#9396FF; -fx-background-size: cover;-fx-border-color: white; -fx-border-style: solid; -fx-border-width: 3;";
        String buttonimg2 = "src/main/resources/com/example/game/images/revivebutton.png";
        revive_button.setStyle(Stylerevive);
        ImageView buttonimage2 = new ImageView(new File(buttonimg2).toURI().toString());
        buttonimage2.setFitWidth(50);
        buttonimage2.setFitHeight(30);
        revive_button.setGraphic(buttonimage2);
        revive_button.setLayoutX(110);
        revive_button.setLayoutY(50);
        revive_button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                pause.setDisable(false);
                start.setDisable(false);
                move.setDisable(false);
                lancebutton.setDisable(false);
                swordbutton.setDisable(false);
                mainPane.getChildren().remove(Menu);
                Position heroposition = new Position(75,hero.getHero().getY());
                Island safeIsland = getsafeisland(heroposition,islands,hero.getHero().getFitHeight(), hero.getHero().getFitWidth());
                if(safeIsland==null){
                    mainPane.getChildren().add(resultmenu());
                }
                else{

                    hero.removecoins(10);
                    hero.setRevived(true);
                    hero.revivehero();
                    revive(safeIsland);
                    time.play();
                }
            }
        });

        Menu.getChildren().add(end_button);
        Menu.getChildren().add(revive_button);
        return Menu;
    }

    private AnchorPane resultmenu() {
        AnchorPane Result_Menu = new AnchorPane();
        Result_Menu.setPrefHeight(400);
        Result_Menu.setPrefWidth(400);
        Result_Menu.setLayoutX(250);
        Result_Menu.setLayoutY(150);
        String bg1 = "src/main/resources/com/example/game/images/resultmenu.png";
        Image bg = new Image(new File(bg1).toURI().toString(),300,300,false,true);
        BackgroundImage image = new BackgroundImage(bg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,null);
        Result_Menu.setBackground(new Background(image));
        //.................................................
        Text sometext = new Text();
        sometext.setText("Your Score");
        sometext.setFont(Font.font ("Verdana", 20));
        sometext.setFill(Color.WHITE);
        sometext.setX(100);
        sometext.setY(100);
        Text scoreboard = new Text();
        String heroscore = hero.getscore();
        scoreboard.setText(heroscore);
        scoreboard.setFont(Font.font ("Verdana", 20));
        scoreboard.setFill(Color.WHITE);
        scoreboard.setX(130);
        scoreboard.setY(130);
        Result_Menu.getChildren().add(sometext);
        Result_Menu.getChildren().add(scoreboard);
        //........................................
        sometext = new Text();
        sometext.setText("Coins Won");
        sometext.setFont(Font.font ("Verdana", 20));
        sometext.setFill(Color.GOLD);
        sometext.setX(100);
        sometext.setY(160);
        scoreboard = new Text();
        heroscore = Integer.toString(hero.getCurrCoins().size());
        scoreboard.setText(heroscore);
        scoreboard.setFont(Font.font ("Verdana", 20));
        scoreboard.setFill(Color.GOLD);
        scoreboard.setX(130);
        scoreboard.setY(190);
        Result_Menu.getChildren().add(sometext);
        Result_Menu.getChildren().add(scoreboard);
        //................................................
        Exit_Button end_button = new Exit_Button(this.stage);
        end_button.setLayoutX(50);
        end_button.setLayoutY(225);
        //...................................
        Homebutton home = new Homebutton(stage);
        home.setLayoutX(190);
        home.setLayoutY(225);
        Result_Menu.getChildren().add(home);

        //////////////////////////
        Result_Menu.getChildren().add(end_button);
        New_Game_Button New_Game = new New_Game_Button(this.stage, this.HelmetName);
        New_Game.setLayoutX(115);
        New_Game.setLayoutY(220);
        Result_Menu.getChildren().add(New_Game);
        return Result_Menu;
    }

    private void addObjectsonScreen(){
//        for(int i=0;i<5;i++){
//            Floor_Loot_Coin coinx = new Floor_Loot_Coin(new Position(75+2000*i , 350));
//            this.gameObjects.add(coinx);
//            mainPane.getChildren().add(coinx.getImage());
//        }
        for (int i = 0; i < 5; i++){
            if (i == 4){
                Island largeIsland = new Island("Large", mainPane, new Position(8250,islandLocationfromTopofScreen), 450, 150 , 0.3, this.ISLAND_COUNT);
                islands.add(largeIsland);
                this.ISLAND_COUNT+=1;
                Island largeIsland1 = new Island("Large", mainPane, new Position(8250+550,islandLocationfromTopofScreen), 450, 150 , 0.3, this.ISLAND_COUNT);
                islands.add(largeIsland1);
                this.ISLAND_COUNT+=1;
                Island largeIsland2 = new Island("Large", mainPane, new Position(8250+1100,islandLocationfromTopofScreen), 450, 150 , 0.3, this.ISLAND_COUNT);
                islands.add(largeIsland2);
                this.ISLAND_COUNT+=1;
            }
            else{
                Island smallIsland1 = new Island("Small", mainPane, new Position(75 + 2050*i,islandLocationfromTopofScreen), 195, 100,0.5, this.ISLAND_COUNT);
                this.ISLAND_COUNT+=1;
                Island smallIsland2 = new Island("Small", mainPane, new Position(350 + 2050*i,islandLocationfromTopofScreen - 100), 195, 100,0.4, this.ISLAND_COUNT);
                this.ISLAND_COUNT+=1;
                Island mediumIsland1 = new Island("Medium", mainPane, new Position(625 + 2050*i,islandLocationfromTopofScreen), 350, 125, 0.5, this.ISLAND_COUNT);
                this.ISLAND_COUNT+=1;
                Island largeIsland = new Island("Large", mainPane, new Position(1075 + 2050*i,islandLocationfromTopofScreen), 450, 150 , 0.3, this.ISLAND_COUNT);
                this.ISLAND_COUNT+=1;
                Island mediumIsland2 = new Island("Medium", mainPane, new Position(1625 + 2050*i,islandLocationfromTopofScreen - 75), 350, 125, 0.2, this.ISLAND_COUNT);
                this.ISLAND_COUNT+=1;
                islands.add(smallIsland1);
                islands.add(smallIsland2);
                islands.add(mediumIsland1);
                islands.add(largeIsland);
                islands.add(mediumIsland2);
            }
        }
        int count = 0;
        for (Island island: islands){
            if (count == 0){
                count++;
                continue;
            }
            if (count == islands.size()-1){
                this.GAME_OBJECT_COUNT+=1;
                Position islandPosition = island.getPosition();
                Boss_Orc boss_orc = new Boss_Orc(mainPane, new Position(islandPosition.getX() + 100, islandPosition.getY()-100), 100, 100, ((Math.random()*(0.5)) + 0.7), island,orcdie,10000);
                this.gameObjects.add(boss_orc);
                continue;

            }
            Position islandPosition = island.getPosition();
            if (island.getIslandType().equals("Small")){
                this.GAME_OBJECT_COUNT+=1;
                generateIslandObjects(island, islandPosition, 1);
            }
            else if (island.getIslandType().equals("Medium")){
                this.GAME_OBJECT_COUNT+=2;
                generateIslandObjects(island, islandPosition, 2);
            }
            else{
                this.GAME_OBJECT_COUNT+=3;
                generateIslandObjects(island, islandPosition, 3);
            }
            count++;
        }
    }

    private void generateIslandObjects(Island island, Position islandPosition, int maxQuantityObjectsOnIsland){
        String[] gameObjects = {"TNT", "CoinChest", "CoinChest", "Standard_Green_Orc", "Standard_Green_Orc", "Standard_Red_Orc", "Standard_Red_Orc", "WeaponChestLance", "WeaponChestSword"};
        int placedSoFar = 0;
        int orcid =0;
        for (int i = 0; i < maxQuantityObjectsOnIsland; i++){
            Random rand = new Random();
            String objectChosen = gameObjects[rand.nextInt(gameObjects.length)];
            switch (objectChosen) {
                case "TNT" -> {
                    TNT tnt = new TNT(mainPane, new Position(islandPosition.getX() + 50 + placedSoFar * 150, islandPosition.getY() - 50), 70, 70, 0.4, island,tntburst);
                    placedSoFar++;
                    this.gameObjects.add(tnt);
                }
                case "CoinChest" -> {
                    Coin_Chest chest = new Coin_Chest(mainPane, new Position(islandPosition.getX() + 50 + placedSoFar * 150, islandPosition.getY() - 40), 100, 75, island,coincchestsound, false);
                    placedSoFar++;
                    this.gameObjects.add(chest);
                }
                case "Standard_Green_Orc" -> {
                    Standard_Green_Orc greenOrc = new Standard_Green_Orc(mainPane, new Position(islandPosition.getX() + 50 + placedSoFar * 150, islandPosition.getY() - 100), 70, 60, ((Math.random()*(0.5)) + 0.7), island,orcdie,orcid);
                    placedSoFar++;
                    orcid++;
                    this.gameObjects.add(greenOrc);
                }
                case "Standard_Red_Orc" -> {
                    Standard_Red_Orc redOrc = new Standard_Red_Orc(mainPane, new Position(islandPosition.getX() + 50 + placedSoFar * 150, islandPosition.getY() -100), 60, 60, ((Math.random()*(0.5)) + 0.7), island,orcdie,orcid);
                    orcid++;
                    placedSoFar++;
                    this.gameObjects.add(redOrc);
                }
                case "WeaponChestLance" -> {
                    Weapon_Chest chest = new Weapon_Chest(mainPane, new Position(islandPosition.getX() + 50 + placedSoFar * 150, islandPosition.getY() - 40), 100, 75, "Lance", island,this.lancebutton,this.swordbutton,weaponchestsound,false);
                    placedSoFar++;
                    this.gameObjects.add(chest);
                }
                case "WeaponChestSword" -> {
                    Weapon_Chest chest = new Weapon_Chest(mainPane, new Position(islandPosition.getX() + 50 + placedSoFar * 150, islandPosition.getY() - 40), 100, 75, "Sword", island,this.swordbutton,this.lancebutton,weaponchestsound,false);
                    placedSoFar++;
                    this.gameObjects.add(chest);
                }
            }
        }
    }

    public void start() throws FileNotFoundException {
        stage.setScene(this.mainScene);
        stage.show();

        KeyFrame frame = new KeyFrame(Duration.millis(10), e->{
            for (Game_Objects game_object: this.gameObjects){
                if (game_object instanceof Orc){
                    ((Orc) game_object).setIslandofResidence(getIslandforOrc(((Orc) game_object), this.islands,
                            game_object.getImageViewHeight(), game_object.getImageViewWidth()));
                    //System.out.println(((Orc) game_object).getIslandofResidence());
                    moveOrc((Orc) game_object , ((Orc) game_object).getIslandofResidence());
//                    System.out.println();
                }
                else if (game_object instanceof gameObstacles){
                    moveTNT((TNT)game_object, ((TNT)game_object).getIslandofResidence());
                }
                else if (game_object instanceof Chest){
                    moveChest((Chest) game_object, ((Chest) game_object).getIslandofResidence());
                }
            }
        }
        );
        File file = new File("src\\main\\java\\com\\example\\game\\previousheroLocations.txt");
        Scanner sc = new Scanner(file);
        KeyFrame frame2 = new KeyFrame(Duration.millis(10), e->{
            for (Island island: this.islands){
                moveIsland(island);
            }
            try {
                moveHero(this.hero);
                tntkill();
                orkkill();
                removecoin();
                moveshadow(sc);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
        );
        this.time = new Timeline(frame2,frame);
        time.setCycleCount(Timeline.INDEFINITE);
        time.play();
    }

    private void tntbursting(TNT game_object) throws Exception {
        double TNT_start_X_range = game_object.getImage().getX() -20 ;
        double TNT_start_Y_range = game_object.getImage().getY() -20 ;
        double TNT_end_Y_range = TNT_start_Y_range + game_object.getImageViewHeight() + 20;
        double TNT_end_X_range = TNT_start_X_range + game_object.getImageViewWidth() + 20;
        if(hero.getHero().getX() + hero.getHero().getFitWidth() > TNT_start_X_range && hero.getHero().getX() + hero.getHero().getFitWidth() < TNT_end_X_range){
            if(hero.getHero().getY() + hero.getHero().getFitHeight() > TNT_start_Y_range && hero.getHero().getY() + hero.getHero().getFitHeight() < TNT_end_Y_range){
                killhero(false);
            }
            else if(hero.getHero().getY() > TNT_start_Y_range && hero.getHero().getY() < TNT_end_Y_range){
                killhero(false);
            }
        }
        else if(hero.getHero().getX() > TNT_start_X_range && hero.getHero().getX()  < TNT_end_X_range){
            if(hero.getHero().getY() + hero.getHero().getFitHeight() > TNT_start_Y_range && hero.getHero().getY() + hero.getHero().getFitHeight() < TNT_end_Y_range){
                killhero(false);
            }
            else if(hero.getHero().getY() > TNT_start_Y_range && hero.getHero().getY() < TNT_end_Y_range){
                killhero(false);
            }
        }
        for(Game_Objects object : this.gameObjects){
            if(object instanceof Orc){
                Orc orc = (Orc) object;
                if(orc.getOrc().getX() + orc.getOrc().getFitWidth() > TNT_start_X_range && orc.getOrc().getX() + orc.getOrc().getFitWidth() < TNT_end_X_range) {
                    if (orc.getOrc().getY() + orc.getOrc().getFitHeight() > TNT_start_Y_range && orc.getOrc().getY() + orc.getOrc().getFitHeight() < TNT_end_Y_range) {
                        orc.animate();
                    } else if (orc.getOrc().getY() > TNT_start_Y_range && orc.getOrc().getY() < TNT_end_Y_range) {
                        orc.animate();
                    }
                }
                else if(orc.getOrc().getX() > TNT_start_X_range && orc.getOrc().getX()  < TNT_end_X_range){
                    if(orc.getOrc().getY() + orc.getOrc().getFitHeight() > TNT_start_Y_range && orc.getOrc().getY() + orc.getOrc().getFitHeight() < TNT_end_Y_range){
                        orc.animate();
                    }
                    else if(orc.getOrc().getY() > TNT_start_Y_range && orc.getOrc().getY() < TNT_end_Y_range){
                        orc.animate();
                    }
                }
            }
        }
    }
    private void tntkill() throws Exception {
        for (Game_Objects game_object: this.gameObjects){
            if(game_object instanceof TNT){
                if(((TNT) game_object).getBurst()){
                    gameObjects.remove(game_object);
                    tntbursting((TNT) game_object);
                    break;
                }
            }
        }
    }
    private void orkkill() throws Exception {
        for (Game_Objects game_object: this.gameObjects){
            if(game_object instanceof Orc){
                if(((Orc) game_object).isDead()){
                    if (game_object instanceof Boss_Orc){
                        hero.setRevived(true);
                        killhero(true);
                    }
                    mainPane.getChildren().remove(((Orc) game_object).getOrc());
                    gameObjects.remove(game_object);
                    break;
                }
            }
        }
    }

    private void removecoin(){
        for (Game_Objects game_object: this.gameObjects){
            if(game_object instanceof Floor_Loot_Coin){
                if(((Floor_Loot_Coin) game_object).getIsCollected()){
                    mainPane.getChildren().remove(game_object.getImage());
                    gameObjects.remove(game_object);
                    break;
                }
            }
        }
    }


    private void moveChest(Chest game_object, Island island) {
        double x,y;
        x = game_object.getImage().getX();
        y = game_object.getImage().getY();
        if(!(island.getSpeed() ==0)){
            game_object.setPositionY(island.getPosition().getY()-island.getSpeed()-game_object.getImageViewHeight());
        }
        if(y + game_object.getHeight() > island.getPosition().getY()){
            game_object.setPositionY(island.getPosition().getY() - game_object.getHeight());
        }
    }

    private Island getisland(Position pos , ArrayList<Island> islands, double height, double width){
        double player_starting_x_coordinate = pos.getX();
        double player_starting_y_coordinate = pos.getY();
        double player_ending_x_coordinate = player_starting_x_coordinate + width;
        double player_ending_y_coordinate = player_starting_y_coordinate + height;
        Island ansisland = null;
        int count = 0;
        for(Island island :islands){
            count++;
            double island_h = island.getIsland().getFitHeight();
            double island_w = island.getIsland().getFitWidth();
            double island_starting_x_coordinate = island.getIsland().getX();
            double island_starting_y_coordinate = island.getIsland().getY();
            double island_ending_x_coordinate = island_starting_x_coordinate + island_w;
            double island_ending_y_coordinate = island_starting_y_coordinate + island_h;
            // The 10 over here is so that hero doesn't fall immediately
            //Can change the value
            if(island_starting_y_coordinate + 10 > player_ending_y_coordinate){
                if(island_starting_x_coordinate <=player_ending_x_coordinate-10 && island_ending_x_coordinate>=player_starting_x_coordinate+10){
                    ansisland = island;
                }
            }
        }
        /*
        if(ansisland==null){
            System.out.println("Setting this island null");
        }
        */
        return ansisland;
    }

    private Game_Objects getobject(Position pos , ArrayList<Game_Objects> gameObjects, double height, double width){
        double player_starting_x_coordinate = pos.getX();
        double player_starting_y_coordinate = pos.getY();
        double player_ending_x_coordinate = player_starting_x_coordinate + width;
        double player_ending_y_coordinate = player_starting_y_coordinate + height;
        Game_Objects ansobject = null;
        for(Game_Objects object : gameObjects){
            if(object instanceof Orc){
                Orc orcobj = (Orc) object;
                double Orc_h = orcobj.getOrc().getFitHeight();
                double Orc_w = orcobj.getOrc().getFitWidth();
                double Orc_starting_x_coordinate = orcobj.getOrc().getX();
                double Orc_starting_y_coordinate = orcobj.getOrc().getY();
                double Orc_ending_x_coordinate = Orc_starting_x_coordinate + Orc_w;
                double Orc_ending_y_coordinate = Orc_starting_y_coordinate + Orc_h;
                if(Orc_starting_y_coordinate + 10 > player_ending_y_coordinate){
                    if(Orc_starting_x_coordinate <=player_ending_x_coordinate-10 && Orc_ending_x_coordinate>=player_starting_x_coordinate+10){
                        ansobject = orcobj;
                    }
                }
            }

        }
        /*
        if(ansisland==null){
            System.out.println("Setting this island null");
        }
        */
        return ansobject;
    }

    private Island getIslandforOrc(Orc orc ,ArrayList<Island> islands, double height, double width){
        double orc_starting_x_coordinate = orc.getOrc().getX();
        double orc_starting_y_coordinate = orc.getOrc().getY();
        double orc_ending_x_coordinate = orc_starting_x_coordinate + width;
        double orc_ending_y_coordinate = orc_starting_y_coordinate + height;
        Island ansisland = null;
        int count = 0;
        for(Island island :islands){
            count++;
            double island_h = island.getIsland().getFitHeight();
            double island_w = island.getIsland().getFitWidth();
            double island_starting_x_coordinate = island.getIsland().getX();
            double island_starting_y_coordinate = island.getIsland().getY();
            double island_ending_x_coordinate = island_starting_x_coordinate + island_w;
            double island_ending_y_coordinate = island_starting_y_coordinate + island_h;
            // The 10 over here is so that hero doesn't fall immediately
            //Can change the value
            if(island_starting_y_coordinate + 10 > orc_ending_y_coordinate){
                if(island_starting_x_coordinate <=  orc_ending_x_coordinate-10 && island_ending_x_coordinate>=orc_starting_x_coordinate+10){
                    ansisland = island;
                }
            }
        }
        /*
        if(ansisland==null){
            System.out.println("Setting this island null");
        }
        */
        return ansisland;
    }

    public void killhero(Boolean winning) throws Exception {
        pause.setDisable(true);
        start.setDisable(true);
        move.setDisable(true);
        lancebutton.setDisable(true);
        swordbutton.setDisable(true);
        if(!winning){
            herodie.play();
            herodie.seek(Duration.ZERO);
        }
        hero.die(mainPane,abyssPane,resultmenu(),time,OrcKillCount,
                TNTBurstCount,startTime,
                OrcEncounterCount, SwordsCollected, SpearsCollected, CoinChestsOpened);
    }

    private Island getsafeisland(Position pos ,ArrayList<Island> islands, double height, double width){
        double player_starting_x_coordinate = pos.getX();
        double player_starting_y_coordinate = pos.getY();
        double player_ending_x_coordinate = player_starting_x_coordinate + width;
        double player_ending_y_coordinate = player_starting_y_coordinate + height;
        Island ansisland = null;
        int count = 0;
        for(Island island :islands){
            count++;
            double island_h = island.getIsland().getFitHeight();
            double island_w = island.getIsland().getFitWidth();
            double island_starting_x_coordinate = island.getIsland().getX();
            double island_starting_y_coordinate = island.getIsland().getY();
            double island_ending_x_coordinate = island_starting_x_coordinate + island_w;
            double island_ending_y_coordinate = island_starting_y_coordinate + island_h;
            // The 10 over here is so that hero doesn't fall immediately
            //Can change the value
            if(island_ending_x_coordinate < player_starting_x_coordinate){
                ansisland = island;
            }
            else{
                break;
            }
        }
        if(ansisland==null){
            System.out.println("This shouldn't be possible");
        }
        return ansisland;
    }

    private void revive(Island safeIsland) {
        herorevive.play();
        herorevive.seek(Duration.ZERO);
        double xdiff = 75 - safeIsland.getIsland().getX();
        hero.setCounter(hero.getCounter() +(int) xdiff);
        for(Island island: islands){
            island.setPosition(new Position(island.getPosition().getX()+xdiff, island.getPosition().getY()));
        }
        for (Game_Objects game_objects: gameObjects) {
            game_objects.setPosition(new Position(game_objects.getPosition().getX() + xdiff, game_objects.getPosition().getY()));
        }
        hero.getHero().setY(safeIsland.getIsland().getY() - hero.getHero().getFitHeight());
        hero.setPosition(new Position(hero.getHero().getX(), hero.getHero().getY()));
    }


    private void moveIsland(Island island) {
        island.setPositionY(island.getPosition().getY()-island.getSpeed());
        if(island.getPosition().getY()>= island.getinitial().getY() + 20 || island.getPosition().getY()<= island.getinitial().getY() - 30){
            double speed = island.getSpeed();
            island.setSpeed(-speed);
        }
    }

    private void moveHero(Hero hero) throws Exception {
        appendToFile("src\\main\\java\\com\\example\\game\\heroLocations.txt", "Time " + (java.time.Instant.now().getEpochSecond() - this.startTime) + " Score " + Integer.parseInt(hero.getscore()) + " PositionX " + (int) (75 + hero.getCounter()) +
                " PositionY " + (int) hero.getPosition().getY());
        double hero_height = hero.getHero().getFitHeight();
        double hero_width = hero.getHero().getFitWidth();
        hero.setPosition(new Position(hero.getHero().getX(), hero.getHero().getY()));
        if(hero.getHero().getY()>600){
             killhero(false);
        }
        Island residence = getisland(hero.getPosition(), islands, hero_height, hero_width);
        //        if (hero.getPosition().getY() > islandLocationfromTopofScreen+25){
//            time.pause();
//            mainPane.getChildren().add(abyssPane);
//        }
        if (residence == null){
            double speed = Math.abs(hero.getSpeed())/2;
            hero.getHero().setY(hero.getHero().getY() + speed);
            hero.setPosition(new Position(hero.getHero().getX(), hero.getHero().getY()));
//            mainPane.getChildren().add(abyssPane);
        }
        else {
            Game_Objects orcbelow = getobject(hero.getPosition(), gameObjects, hero_height, hero_width);
            if(orcbelow ==null){
                double x,y;
                double island_height;
                double island_width,jump;
                x = residence.getIsland().getX();
                y = residence.getIsland().getY();
                island_height = residence.getIsland().getFitHeight();
                island_width = residence.getIsland().getFitWidth();
                jump = 125;
                if (hero.getHero().getY() - hero.getSpeed() + hero_height  >= y) {
                    hero.getHero().setY(y - hero_height);
                    double speed = hero.getSpeed();
                    hero.setSpeed(-speed);
                    herojump.play();
                    herojump.seek(Duration.ZERO);
                }
                else if (hero.getHero().getY() - hero.getSpeed() + hero_height <= y - jump) {
                    hero.getHero().setY(y - jump - hero_height);
                    double speed = hero.getSpeed();
                    hero.setSpeed(-speed);
                }
                else {
                    hero.getHero().setY(hero.getHero().getY() - hero.getSpeed());
                }
            }
            else{
                double x,y;
                double island_height;
                double island_width,jump;
                x = orcbelow.getImage().getX();
                y = orcbelow.getImage().getY();
                jump = 70;
                if (hero.getHero().getY() - hero.getSpeed() + hero_height  >= y) {
                    hero.getHero().setY(y - hero_height);
                    double speed = hero.getSpeed();
                    hero.setSpeed(-speed);
                    herojump.play();
                    herojump.seek(Duration.ZERO);
                }
                else if (hero.getHero().getY() - hero.getSpeed() + hero_height <= y - jump) {
                    hero.getHero().setY(y - jump - hero_height);
                    double speed = hero.getSpeed();
                    hero.setSpeed(-speed);
                }
                else {
                    hero.getHero().setY(hero.getHero().getY() - hero.getSpeed());
                }
            }

            hero.setPosition(new Position(hero.getHero().getX(), hero.getHero().getY()));
            for(Game_Objects gameobject : gameObjects){
                if(check_collision(hero,gameobject)){
                    if (gameobject instanceof Orc){
                        orcollision(hero,(Orc)gameobject);
                        Island orcisland = getisland(((Orc) gameobject).getPosition(), this.islands,
                                ((Orc) gameobject).getImageViewHeight(), ((Orc) gameobject).getImageViewWidth());
                        ((Orc) gameobject).setIslandofResidence(orcisland);
                        if (orcisland == null){
                            ((Orc) gameobject).setAboveIsland(false);
                        }
                        OrcEncounterCount++;
                    }
                    else if(gameobject instanceof Floor_Loot_Coin){
                        gameobject.collide(hero);
                    }
                    else if(gameobject instanceof TNT){
                        gameobject.collide(hero);
                        Island residenceTNT = getisland(gameobject.getPosition(), islands, gameobject.getImageViewHeight(), gameobject.getImageViewWidth());
                        ((TNT) gameobject).setIslandofResidence(residenceTNT);
                        TNTBurstCount++;
                    }
                    else if (gameobject instanceof Coin_Chest){
                        gameobject.collide(hero);
                        CoinChestsOpened++;
                    }
                    else if (gameobject instanceof Weapon_Chest){
                        gameobject.collide(hero);
                        if (((Weapon_Chest)gameobject).getName().equals("Weapon Chest Sword")){
                            SwordsCollected++;
                        }
                        else{
                            SpearsCollected++;
                        }
                    }
                }
            }
        }
    }

    private void moveshadow(Scanner sc) throws Exception {
        if(shadowflag==1){
            if(sc.hasNextLine()){
                String line = sc.nextLine();
                String[] buffers = line.split(" ");
                String score = buffers[3];
                String xpos = buffers[5];
                String ypos = buffers[7];
                int xp,yp;
                xp = Integer.parseInt(xpos);
                yp = Integer.parseInt(ypos);
                xp = xp  - hero.getCounter();
                double original_x = this.shadow.getposition().getX();
                final double[] a = {0};
                int finalXp = xp;
                AnimationTimer shadow_timer = new AnimationTimer() {
                    double end_x = finalXp;
                    @Override
                    public void handle(long l) {
                        a[0] = a[0] +1;
                        shadow.setposition(new Position(original_x + a[0],yp));
                        if(a[0] >= end_x){
                            this.stop();
                        }
                    }
                };
                shadow_timer.start();
            }
            else{
                mainPane.getChildren().remove(this.shadow.getshadow());
            }
        }
    }




    private void orcollision(Hero hero, Orc gameobject) throws Exception {
        double hero_height = hero.getHero().getFitHeight();
        double hero_width = hero.getHero().getFitWidth();
        double obj_height = gameobject.getImage().getFitHeight();
        double obj_width = gameobject.getImage().getFitWidth();
        double hero_start_X , hero_end_X , hero_start_Y, hero_end_Y;
        double obj_start_X , obj_end_X , obj_start_Y, obj_end_Y;
        hero_start_X = hero.getPosition().getX();
        hero_end_X = hero_start_X + hero_width;
        hero_start_Y = hero.getHero().getY();
        hero_end_Y = hero_start_Y + hero_height;
        obj_start_X = gameobject.getImage().getX();
        obj_end_X = obj_start_X + obj_width;
        obj_start_Y = gameobject.getImage().getY();
        obj_end_Y = obj_start_Y + obj_height;
        if(!gameobject.getDying() && ((obj_start_Y + ((obj_height*3)/4) <= hero_start_Y) && (obj_end_Y >= hero_start_Y))){
            if(hero_end_X <= obj_end_X -5 && hero_end_X >= obj_start_X + 5){
                killhero(false);
            }
        }
        else if(!gameobject.getDying() && (hero_start_Y + (hero_height*3/4) >= obj_start_Y) && hero_start_X<=obj_start_X + obj_width/2){
            gameobject.collide(hero);
            if (gameobject instanceof Boss_Orc){
                collidingorc(gameobject,10);
            }
            else{
                collidingorc(gameobject,100);
            }

        }
    }

    private void collidingorc(Orc gameobject,int run) {
        if(!gameobject.getIscolliding()){
            gameobject.setIscolliding(true);
            AnimationTimer timer = new AnimationTimer() {
                int orccounter = 0;
                int orcflag = 0;
                @Override
                public void handle(long l) {
                    orccounter = orccounter + 1;
                    gameobject.setPosition(new Position(gameobject.getOrc().getX() + 1, gameobject.getOrc().getY()));
                    gameobject.getOrc().setX(gameobject.getOrc().getX() + 1);
                    for(Game_Objects object : gameObjects){
                        if(object instanceof Orc && gameobject.getId()!=((Orc) object).getId()){
                            if(!gameobject.getDying() && checkorccollision(gameobject,(Orc)object)){
                                collidingorc((Orc) object,50);
                                orcflag = 1;
                            }
                        }
                    }
                    if (gameobject.isDead() || orccounter >= run || orcflag==1) {
                        gameobject.setIscolliding(false);
                        stop();
                    }
                }
            };
            timer.start();
        }
    }

    private boolean checkorccollision(Orc Orc1, Orc Orc2) {

        double Orc1_height = Orc1.getOrc().getFitHeight();
        double Orc1_width = Orc1.getOrc().getFitWidth();

        double Orc2_height = Orc2.getOrc().getFitHeight();
        double Orc2_width = Orc2.getOrc().getFitWidth();

        double Orc1_start_X , Orc1_start_Y, Orc2_start_X , Orc2_start_Y;
        double Orc1_end_X , Orc1_end_Y, Orc2_end_X , Orc2_end_Y;

        Orc1_start_X = Orc1.getOrc().getX();
        Orc1_start_Y = Orc1.getOrc().getY();
        Orc1_end_X = Orc1_start_X + Orc1_width;
        Orc1_end_Y = Orc1_start_Y + Orc1_height;

        Orc2_start_X = Orc2.getOrc().getX();
        Orc2_start_Y = Orc2.getOrc().getY();
        Orc2_end_X = Orc2_start_X + Orc2_width;
        Orc2_end_Y = Orc2_start_Y + Orc2_height;

        if(Orc1_start_Y >= Orc2_end_Y -10 ){
            // here the 3 is to avoid just on edge contact and the 10 above is also to give a bit of margin
            if(Orc1_end_X <= Orc2_end_X - 3 && Orc1_end_X >= Orc2_start_X + 3){
                return true;
            }
        }
        return false;
    }


    private boolean check_collision(Hero hero, Game_Objects game_objects) {
        double hero_height = hero.getHero().getFitHeight();
        double hero_width = hero.getHero().getFitWidth();
        double obj_height = game_objects.getImage().getFitHeight();
        double obj_width = game_objects.getImage().getFitWidth();
        double hero_start_X , hero_end_X , hero_start_Y, hero_end_Y;
        double obj_start_X , obj_end_X , obj_start_Y, obj_end_Y;
        hero_start_X = hero.getHero().getX();
        hero_end_X = hero_start_X + hero_width;
        hero_start_Y = hero.getHero().getY();
        hero_end_Y = hero_start_Y + hero_height;
        obj_start_X = game_objects.getImage().getX();
        obj_end_X = obj_start_X + obj_width;
        obj_start_Y = game_objects.getImage().getY();
        obj_end_Y = obj_start_Y + obj_height;
        if((hero_start_Y <= obj_end_Y && hero_start_Y  >= obj_start_Y) || (hero_end_Y <= obj_end_Y && hero_end_Y >=obj_start_Y)){
            if((hero_start_X <= obj_end_X && hero_start_X >=obj_start_X) || (hero_end_X <= obj_end_X && hero_end_X >=obj_start_X)){
                return true;
            }
        }
        return false;
    }

    private void moveOrc(Orc orc , Island island){
            if(island==null){
                double speed = Math.abs(orc.getSpeed());
                orc.getOrc().setY(orc.getOrc().getY() + speed);
                orc.setPosition(new Position(orc.getOrc().getX(), orc.getOrc().getY()));
                if (orc.getOrc().getY()>=MAX_FALLING_HEIGHT-100){
                    orc.animate();
                }
            }
            else{
                Game_Objects orcb = getobject(new Position(orc.getOrc().getX(), orc.getOrc().getY()), gameObjects, orc.getImageViewHeight(), orc.getImageViewWidth());
                Orc orcbelow = (Orc) orcb;
                if(orcbelow==null){
                    double jump = 150;
                    double x = island.getIsland().getX();
                    double y = island.getIsland().getY();
                    double orc_height = orc.getImageViewHeight();
                    if (orc.getOrc().getY() - orc.getSpeed() + orc_height  >= y) {
                        orc.getOrc().setY(y - orc_height);
                        double speed = orc.getSpeed();
                        orc.setSpeed(-speed);
                        orcjump.play();
                        orcjump.seek(Duration.ZERO);
                    }
                    else if (orc.getOrc().getY() - orc.getSpeed() + orc_height <= y - jump) {
                        orc.getOrc().setY(y - jump - orc_height);
                        double speed = orc.getSpeed();
                        orc.setSpeed(-speed);
                    }
                    else {
                        orc.getOrc().setY(orc.getOrc().getY() - orc.getSpeed());
                    }
                }
                else{
                    double jump = 50;
                    double x = orcbelow.getImage().getX();
                    double y = orcbelow.getImage().getY();
                    double orc_height = orc.getImageViewHeight();
                    if (orc.getOrc().getY() - orc.getSpeed() + orc_height  >= y) {
                        orc.getOrc().setY(y - orc_height);
                        double speed = orc.getSpeed();
                        orc.setSpeed(-speed);
                        orcjump.play();
                        orcjump.seek(Duration.ZERO);
                    }
                    else if (orc.getOrc().getY() - orc.getSpeed() + orc_height <= y - jump) {
                        orc.getOrc().setY(y - jump - orc_height);
                        double speed = orc.getSpeed();
                        orc.setSpeed(-speed);
                    }
                    else {
                        orc.getOrc().setY(orc.getOrc().getY() - orc.getSpeed());
                    }
                }

                orc.setPosition(new Position(orc.getOrc().getX(), orc.getOrc().getY()));
                /*
                if(orc.getOrc().getY()>=island.getPosition().getY()-orc.getImageViewHeight()){
                    orc.setInitialPosition(island.getPosition().getY()-orc.getImageViewHeight());
                }
                */
            }
    }

    private void moveTNT(TNT tnt , Island island){
        if(island==null){
            tnt.setPositionY(tnt.getImage().getY());
        }
        else{
            if (!(island.getSpeed() == 0)){
                tnt.setPositionY(island.getPosition().getY()-island.getSpeed()-tnt.getImageViewHeight());
            }
        }

    }

    public void pausegame(PauseButton pause){
        pause.setOnAction(e ->{
            time.pause();
            move.setDisable(true);
            lancebutton.setDisable(true);
            swordbutton.setDisable(true);
            mainPane.getChildren().remove(pause);
            mainPane.getChildren().add(this.newpane);
        });
    }

    public void startgame(Button button , PauseButton pause){
        button.setOnAction(e ->{
            time.play();
            move.setDisable(false);
            lancebutton.setDisable(false);
            swordbutton.setDisable(false);
            mainPane.getChildren().add(pause);
            mainPane.getChildren().remove(this.newpane);
        });
    }


    public AnchorPane getMainPane() {
        return mainPane;
    }

    public Scene getMainScene() {
        return mainScene;
    }

    public void saveGameDataToFile(File file) {
        try {
            FileOutputStream fileStream = new FileOutputStream(file);
            ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);
//            objectStream.writeObject(this.hero.getsword());
//            objectStream.writeObject(this.hero.getlance());
            objectStream.writeObject(this.hero);
            objectStream.writeObject(this.player);
            for (Island island: islands){
                objectStream.writeObject(island);
            }
            for (Game_Objects game_objects: gameObjects){
                objectStream.writeObject(game_objects);
            }
            objectStream.close();
            fileStream.close();
            System.out.println();
        } catch (Exception e) {
            System.out.println();
        }
    }
    static void appendToFile(String filePath, String content) {
        try (FileWriter fw = new FileWriter(filePath, true)) {
            fw.write(content + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Error in Logging Player High Score");
        }
    }
}
