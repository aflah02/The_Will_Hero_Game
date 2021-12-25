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
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class LoadPage {
    static final int islandLocationfromTopofScreen = 450;
    private static final double MAX_FALLING_HEIGHT = 800;
    private final AnchorPane mainPane;
    private final Scene mainScene;
    private final String spbutton = "src/main/resources/com/example/game/images/spearbutton.png";
    private final String swbutton = "src/main/resources/com/example/game/images/swordbutton.png";
    private Timeline time;
    private AnchorPane newpane;
    private AnchorPane abyssPane;
    private final Stage stage;
    private final ArrayList<Game_Objects> gameObjects;
    private final Player player;
    private final Hero hero;
    private final ArrayList<Island> islands;
    private ImageView sword,lance;
    private final ArrayList<MediaPlayer> players;
    private final MediaPlayer orcjump;
    private final MediaPlayer herojump;
    int score;
    private WeaponButton swordbutton,lancebutton;
    static int RecordingLength;
    Long startTime;
    LoadPage(Stage stage) throws IOException, InterruptedException {
        RecordingLength = 5;
        String[] cmd = {"src\\main\\java\\com\\example\\game\\exec.bat", "5"};
        Process p = Runtime.getRuntime().exec(cmd);
        System.out.println(p);
        System.out.println("hello");
        this.startTime = java.time.Instant.now().getEpochSecond();
        players = new ArrayList<>();
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
        Text lancet = new Text();
        Text swordt = new Text();
        this.hero = new Hero(mainPane, new Position(75,300-50), 60, 60 ,1.2,swordt,lancet);
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
        mainPane.getChildren().add(swordbutton);
        mainPane.getChildren().add(lancebutton);
        String score = Integer.toString(0);
        lancet.setText(score);
        lancet.setFont(Font.font ("Verdana", 10));
        lancet.setFill(Color.YELLOW);
        lancet.setX(60);
        lancet.setY(570);
        mainPane.getChildren().add(lancet);
        score = Integer.toString(0);
        swordt.setText(score);
        swordt.setFont(Font.font ("Verdana", 10));
        swordt.setFill(Color.YELLOW);
        swordt.setX(135);
        swordt.setY(570);
        mainPane.getChildren().add(swordt);
        moveScreenButton moveScreenButton = new moveScreenButton(500, 50, islands, gameObjects, hero, startTime);
        mainPane.getChildren().add(moveScreenButton);
        mainPane.getChildren().remove(hero.getHero());
        mainPane.getChildren().add(hero.getHero());
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
        MusicButton music = new MusicButton(players);
        music.setLayoutX(10);
        music.setLayoutY(135);
        menu.getChildren().add(music);
        SaveButton save = new SaveButton(this.stage);
        save.setLayoutX(120);
        save.setLayoutY(135);
        menu.getChildren().add(save);
        return menu;
    }

    private AnchorPane reviveMenu(){
        AnchorPane Menu = new AnchorPane();
        Menu.setPrefHeight(50);
        Menu.setPrefWidth(100);
        Menu.setLayoutX(300);
        Menu.setLayoutY(350);
        String bg1 = "src/main/resources/com/example/game/images/GradientBackground.jpg";
        Image bg = new Image(new File(bg1).toURI().toString(),100,50,false,true);
        BackgroundImage image = new BackgroundImage(bg, BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,null);
        Menu.setBackground(new Background(image));
        Button end_button = new Button();
        end_button.setText("Finish");
        end_button.setMinHeight(25);
        end_button.setMinWidth(50);
        String Styleend = "-fx-background-color:RED; -fx-background-size: cover;-fx-border-color: grey; -fx-border-style: solid; -fx-border-width: 2;";
        end_button.setStyle(Styleend);
        end_button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                hero.revivehero();
                mainPane.getChildren().remove(Menu);
                mainPane.getChildren().add(resultmenu());
            }
        });
        end_button.setLayoutX(0);
        end_button.setLayoutY(12.5);
        //New Button Revive_Button to be made TT_TT;
        Button revive_button = new Button();
        revive_button.setText("Revive Button");
        revive_button.setMinHeight(25);
        revive_button.setMinWidth(50);
        String Stylerevive = "-fx-background-color:GREEN; -fx-background-size: cover;-fx-border-color: grey; -fx-border-style: solid; -fx-border-width: 2;";
        revive_button.setStyle(Stylerevive);
        revive_button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println("Player Revive");
                mainPane.getChildren().remove(Menu);
                Position heroposition = new Position(75,hero.getHero().getY());
                Island safeIsland = getsafeisland(heroposition,islands,hero.getHero().getFitHeight(), hero.getHero().getFitWidth());
                if(safeIsland==null){
                    mainPane.getChildren().add(resultmenu());
                }
                else{
                    hero.setRevived(true);
                    hero.revivehero();
                    revive(safeIsland);
                    time.play();
                }
            }
        });
        revive_button.setLayoutX(50);
        revive_button.setLayoutY(12.5);
        Menu.getChildren().add(end_button);
        Menu.getChildren().add(revive_button);
        return Menu;
    }

    private AnchorPane resultmenu() {
        AnchorPane Result_Menu = new AnchorPane();
        Result_Menu.setPrefHeight(600);
        Result_Menu.setPrefWidth(600);
        Result_Menu.setLayoutX(100);
        Result_Menu.setLayoutY(0);
        String bg1 = "src/main/resources/com/example/game/images/resultmenu.png";
        Image bg = new Image(new File(bg1).toURI().toString(),600,600,false,true);
        BackgroundImage image = new BackgroundImage(bg, BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,null);
        Result_Menu.setBackground(new Background(image));
        //.................................................
        Text sometext = new Text();
        sometext.setText("Your Score");
        sometext.setFont(Font.font ("Verdana", 50));
        sometext.setFill(Color.WHITE);
        sometext.setX(175);
        sometext.setY(180);
        Text scoreboard = new Text();
        String heroscore = hero.getscore();
        scoreboard.setText(heroscore);
        scoreboard.setFont(Font.font ("Verdana", 60));
        scoreboard.setFill(Color.WHITE);
        scoreboard.setX(250);
        scoreboard.setY(250);
        Result_Menu.getChildren().add(sometext);
        Result_Menu.getChildren().add(scoreboard);
        //........................................
        sometext = new Text();
        sometext.setText("Coins Won");
        sometext.setFont(Font.font ("Verdana", 50));
        sometext.setFill(Color.GOLD);
        sometext.setX(175);
        sometext.setY(325);
        scoreboard = new Text();
        heroscore = Integer.toString(hero.getCurrCoins().size());
        scoreboard.setText(heroscore);
        scoreboard.setFont(Font.font ("Verdana", 60));
        scoreboard.setFill(Color.GOLD);
        scoreboard.setX(250);
        scoreboard.setY(400);
        Result_Menu.getChildren().add(sometext);
        Result_Menu.getChildren().add(scoreboard);
        //................................................
        Exit_Button end_button = new Exit_Button(this.stage);
        end_button.setLayoutX(400);
        end_button.setLayoutY(475);
        //...................................
        Homebutton home = new Homebutton(stage);
        home.setLayoutX(150);
        home.setLayoutY(475);
        Result_Menu.getChildren().add(home);

        //////////////////////////
        Result_Menu.getChildren().add(end_button);
        New_Game_Button New_Game = new New_Game_Button(this.stage);
        New_Game.setLayoutX(250);
        New_Game.setLayoutY(450);
        Result_Menu.getChildren().add(New_Game);
        return Result_Menu;
    }

    private void addObjectsonScreen(){
        for (int i = 0; i < 10; i++){
            Island smallIsland1 = new Island("Small", mainPane, new Position(75 + 2050*i,islandLocationfromTopofScreen), 195, 100,0.5);
            Island smallIsland2 = new Island("Small", mainPane, new Position(350 + 2050*i,islandLocationfromTopofScreen - 100), 195, 100,0.4);
            Island mediumIsland1 = new Island("Medium", mainPane, new Position(625 + 2050*i,islandLocationfromTopofScreen), 350, 125, 0.5);
            Island largeIsland = new Island("Large", mainPane, new Position(1075 + 2050*i,islandLocationfromTopofScreen), 450, 150 , 0.3);
            Island mediumIsland2 = new Island("Medium", mainPane, new Position(1625 + 2050*i,islandLocationfromTopofScreen - 75), 350, 125, 0.2);
            islands.add(smallIsland1);
            islands.add(smallIsland2);
            islands.add(mediumIsland1);
            islands.add(largeIsland);
            islands.add(mediumIsland2);
        }
        int count = 0;
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
                generateIslandObjects(island, islandPosition, 2);
            }
            else{
                generateIslandObjects(island, islandPosition, 3);
            }
        }

    }

    private void generateIslandObjects(Island island, Position islandPosition, int maxQuantityObjectsOnIsland){
        String[] gameObjects = {"TNT", "TNT", "CoinChest", "CoinChest", "Standard_Green_Orc", "Standard_Green_Orc", "Standard_Green_Orc", "Standard_Red_Orc", "Standard_Red_Orc", "Standard_Red_Orc", "WeaponChestLance", "WeaponChestSword"};
        int placedSoFar = 0;
        for (int i = 0; i < maxQuantityObjectsOnIsland; i++){
            Random rand = new Random();
            String objectChosen = gameObjects[rand.nextInt(gameObjects.length)];
            switch (objectChosen) {
                case "TNT" -> {
                    TNT tnt = new TNT(mainPane, new Position(islandPosition.getX() + 50 + placedSoFar * 150, islandPosition.getY() - 50), 70, 70, 0.4, island);
                    placedSoFar++;
                    this.gameObjects.add(tnt);
                }
                case "CoinChest" -> {
                    Coin_Chest chest = new Coin_Chest(mainPane, new Position(islandPosition.getX() + 50 + placedSoFar * 150, islandPosition.getY() - 40), 100, 75, island);
                    placedSoFar++;
                    this.gameObjects.add(chest);
                }
                case "Standard_Green_Orc" -> {
                    Standard_Green_Orc greenOrc = new Standard_Green_Orc(mainPane, new Position(islandPosition.getX() + 50 + placedSoFar * 150, islandPosition.getY() - 50), 70, 60, ((Math.random()*(0.5)) + 0.7), island);
                    placedSoFar++;
                    this.gameObjects.add(greenOrc);
                }
                case "Standard_Red_Orc" -> {
                    Standard_Red_Orc redOrc = new Standard_Red_Orc(mainPane, new Position(islandPosition.getX() + 50 + placedSoFar * 150, islandPosition.getY() - 50), 60, 60, ((Math.random()*(0.5)) + 0.7), island);
                    placedSoFar++;
                    this.gameObjects.add(redOrc);
                }
                case "WeaponChestLance" -> {
                    Weapon_Chest chest = new Weapon_Chest(mainPane, new Position(islandPosition.getX() + 50 + placedSoFar * 150, islandPosition.getY() - 40), 100, 75, "Lance", island,this.lancebutton,this.swordbutton);
                    placedSoFar++;
                    this.gameObjects.add(chest);
                }
                case "WeaponChestSword" -> {
                    Weapon_Chest chest = new Weapon_Chest(mainPane, new Position(islandPosition.getX() + 50 + placedSoFar * 150, islandPosition.getY() - 40), 100, 75, "Sword", island,this.swordbutton,this.lancebutton);
                    placedSoFar++;
                    this.gameObjects.add(chest);
                }
            }
        }
    }

    public void start(){
        stage.setScene(this.mainScene);
        stage.show();
        KeyFrame frame = new KeyFrame(Duration.millis(10), e->{
            for (Game_Objects game_object: this.gameObjects){
                if (game_object instanceof Orc){
//                    ((Orc) game_object).setIslandofResidence(getIslandforOrc(((Orc) game_object), this.islands,
//                            game_object.getImageViewHeight(), game_object.getImageViewWidth()));
//                    System.out.println(((Orc) game_object).getIslandofResidence());
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
        KeyFrame frame2 = new KeyFrame(Duration.millis(10), e->{
            for (Island island: this.islands){
                moveIsland(island);
            }
            moveHero(this.hero);
            tntkill();

        }
        );
        this.time = new Timeline(frame2,frame);
        time.setCycleCount(Timeline.INDEFINITE);
        time.play();
    }

    private void tntbursting(TNT game_object){
        double TNT_start_X_range = game_object.getPosition().getX();
        double TNT_start_Y_range = game_object.getPosition().getY();
        double TNT_end_Y_range = TNT_start_Y_range + game_object.getImageViewHeight();
        double TNT_end_X_range = TNT_start_X_range + game_object.getImageViewWidth();
        if(hero.getHero().getX() + hero.getHero().getFitWidth() > TNT_start_X_range && hero.getHero().getX() + hero.getHero().getFitWidth() < TNT_end_X_range){
            if(hero.getHero().getY() + hero.getHero().getFitHeight() > TNT_start_Y_range && hero.getHero().getY() + hero.getHero().getFitHeight() < TNT_end_Y_range){
                hero.die(mainPane,abyssPane,resultmenu(),time);
            }
            else if(hero.getHero().getY() > TNT_start_Y_range && hero.getHero().getY() < TNT_end_Y_range){
                hero.die(mainPane,abyssPane,resultmenu(),time);
            }
        }
        else if(hero.getHero().getX() > TNT_start_X_range && hero.getHero().getX()  < TNT_end_X_range){
            if(hero.getHero().getY() + hero.getHero().getFitHeight() > TNT_start_Y_range && hero.getHero().getY() + hero.getHero().getFitHeight() < TNT_end_Y_range){
                hero.die(mainPane,abyssPane,resultmenu(),time);
            }
            else if(hero.getHero().getY() > TNT_start_Y_range && hero.getHero().getY() < TNT_end_Y_range){
                hero.die(mainPane,abyssPane,resultmenu(),time);
            }
        }
        for(Game_Objects object : this.gameObjects){
            if(object instanceof Orc){
                //check Orc positions and kill it in similar fashion to Hero
            }
        }
    }
    private void tntkill() {
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


    private void moveChest(Chest game_object, Island island) {
        if (!(island.getSpeed() == 0)){
            game_object.setPositionY(island.getPosition().getY()-island.getSpeed()-game_object.getImageViewHeight());
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
        double xdiff = 75 - safeIsland.getIsland().getX();
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

    private void moveHero(Hero hero){
        double hero_height = hero.getHero().getFitHeight();
        double hero_width = hero.getHero().getFitWidth();
        hero.setPosition(new Position(hero.getHero().getX(), hero.getHero().getY()));
        if(hero.getHero().getY()>600){
            boolean isrevived = hero.die(mainPane,abyssPane,resultmenu(),time);
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
            hero.setPosition(new Position(hero.getHero().getX(), hero.getHero().getY()));
            for(Game_Objects gameobject : gameObjects){
                if(check_collision(hero,gameobject)){
                    gameobject.collide(hero);
                    if (gameobject instanceof Orc){
                        if (getisland(((Orc) gameobject).getPosition(), this.islands,
                                ((Orc) gameobject).getImageViewHeight(), ((Orc) gameobject).getImageViewWidth()) == null){
                            ((Orc) gameobject).setAboveIsland(false);
                        }
                    }
                    if(gameobject instanceof TNT){
                        Island residenceTNT = getisland(gameobject.getPosition(), islands, gameobject.getImageViewHeight(), gameobject.getImageViewWidth());
                        ((TNT) gameobject).setIslandofResidence(residenceTNT);
                    }
                }
            }
        }
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
                if (orc.getOrc().getY()>=MAX_FALLING_HEIGHT){
                    mainPane.getChildren().remove(orc.getImage());
                }
            }
            else{
                if(orc.getOrc().getY()-orc.getSpeed()>=island.getPosition().getY()-orc.getImageViewHeight()){
                    orc.getOrc().setY(island.getPosition().getY()-orc.getImageViewHeight());
                    orcjump.play();
                    orcjump.seek(Duration.ZERO);
                }
                orc.getOrc().setY(Math.max(orc.getOrc().getY() - orc.getSpeed(), island.getPosition().getY() - island.getIslandImageViewHeight()));
                if(orc.getOrc().getY()>=island.getPosition().getY()-orc.getImageViewHeight() || orc.getOrc().getY()<=island.getPosition().getY()- island.getIslandImageViewHeight()){
                    double speed = orc.getSpeed();
                    orc.setSpeed(-speed);
                }
                if(orc.getOrc().getY()>=island.getPosition().getY()-orc.getImageViewHeight()){
                    orc.setInitialPosition(island.getPosition().getY()-orc.getImageViewHeight());
                }
            }
//        else{
//            double speed = Math.abs(orc.getSpeed());
//            orc.getOrc().setY(orc.getOrc().getY() + speed);
//            if (orc.getOrc().getY()>=island.getPosition().getY()-orc.getImageViewHeight()+50){
//                mainPane.getChildren().remove(orc.getImage());
//            }
//        }
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
            mainPane.getChildren().remove(pause);
            mainPane.getChildren().add(this.newpane);
        });
    }
    public void startgame(Button button , PauseButton pause){
        button.setOnAction(e ->{
            time.play();
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
    public void loadGameDataFromFile(File file) throws ClassNotFoundException, IOException {

        FileInputStream fileStream = new FileInputStream(file);
        ObjectInputStream objectStream = new ObjectInputStream(fileStream);

//        svaedFlag = (int[][]) objectStream.readObject();
//        savedColor = (Color[][]) objectStream.readObject();
//        savedSnake = (Snake) objectStream.readObject();
//        savedFood = (Grid) objectStream.readObject();
//        savedScore = (Integer) objectStream.readObject();
//        savedBarriers =(Barriers) objectStream.readObject();
//        savedNeedToGenerateFood = (Boolean)objectStream.readObject();
//        savedNeedToGenerateBarrie = (Boolean)objectStream.readObject();
    }
}
