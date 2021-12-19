package com.example.game;

import javafx.animation.KeyFrame;
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
import java.nio.file.attribute.PosixFileAttributes;
import java.util.ArrayList;
import java.util.Random;

public class LoadPage {
    static final int islandLocationfromTopofScreen = 450;
    private final AnchorPane mainPane;
    private final Scene mainScene;
    private final String spbutton = "src/main/resources/com/example/game/images/spearbutton.png";
    private final String swbutton = "src/main/resources/com/example/game/images/swordbutton.png";
    private Timeline time;
    private AnchorPane newpane;
    private AnchorPane abyssPane;
    private final Stage stage;
    private final ArrayList<Game_Objects> gameObjects;
    private final Hero hero;
    private final ArrayList<Island> islands;
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
        abyssPane = reviveMenu();
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
        moveScreenButton moveScreenButton = new moveScreenButton(500, 50, islands, gameObjects,hero);
        mainPane.getChildren().add(moveScreenButton);
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

    private AnchorPane reviveMenu(){
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
            Island smallIsland = new Island("Small", mainPane, new Position(75 + 1600*i,islandLocationfromTopofScreen), 200, 100,0.3);
            Island mediumIsland = new Island("Medium", mainPane, new Position(500 + 1600*i,islandLocationfromTopofScreen), 350, 125, 0.3);
            Island largeIsland = new Island("Large", mainPane, new Position(1000 + 1600*i,islandLocationfromTopofScreen), 450, 150 , 0.3);
            islands.add(smallIsland);
            islands.add(mediumIsland);
            islands.add(largeIsland);
        }
        int count = 0;
//        for (Island island: islands){
//            System.out.println(island.getIslandType());
//        }
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
                    TNT tnt = new TNT(mainPane, new Position(islandPosition.getX() + 50 + placedSoFar * 150, islandPosition.getY() - 50), 50, 50, 0.4, island);
                    placedSoFar++;
                    this.gameObjects.add(tnt);
                }
                case "CoinChest" -> {
                    Coin_Chest chest = new Coin_Chest(mainPane, new Position(islandPosition.getX() + 50 + placedSoFar * 150, islandPosition.getY() - 40), 50, 40, island);
                    placedSoFar++;
                    this.gameObjects.add(chest);
                }
                case "Standard_Green_Orc" -> {
                    Standard_Green_Orc greenOrc = new Standard_Green_Orc(mainPane, new Position(islandPosition.getX() + 50 + placedSoFar * 150, islandPosition.getY() - 50), 60, 50, 0.8, island);
                    placedSoFar++;
                    this.gameObjects.add(greenOrc);
                }
                case "Standard_Red_Orc" -> {
                    Standard_Red_Orc redOrc = new Standard_Red_Orc(mainPane, new Position(islandPosition.getX() + 50 + placedSoFar * 150, islandPosition.getY() - 50), 50, 50, 1, island);
                    placedSoFar++;
                    this.gameObjects.add(redOrc);
                }
                case "WeaponChestLance" -> {
                    Weapon_Chest chest = new Weapon_Chest(mainPane, new Position(islandPosition.getX() + 50 + placedSoFar * 150, islandPosition.getY() - 40), 50, 40, "Lance", island);
                    placedSoFar++;
                    this.gameObjects.add(chest);
                }
                case "WeaponChestSword" -> {
                    Weapon_Chest chest = new Weapon_Chest(mainPane, new Position(islandPosition.getX() + 50 + placedSoFar * 150, islandPosition.getY() - 40), 50, 40, "Sword", island);
                    placedSoFar++;
                    this.gameObjects.add(chest);
                }
            }
//            System.out.println(gameObjects[rand.nextInt(gameObjects.length)]);
        }
    }

    public void start(){
        stage.setScene(this.mainScene);
        stage.show();
        KeyFrame frame = new KeyFrame(Duration.millis(10), e->{
            for (Game_Objects game_object: this.gameObjects){
                if (game_object instanceof Orc){
                    moveOrc((Orc) game_object , ((Orc) game_object).getIslandofResidence());
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

        }
        );
        this.time = new Timeline(frame2,frame);
        time.setCycleCount(Timeline.INDEFINITE);
        time.play();
    }

    private void moveChest(Chest game_object, Island island) {
        if (!(island.getSpeed() == 0)){
            game_object.setPositionY(island.getPosition().getY()-island.getSpeed()-game_object.getImageViewHeight());
            if(game_object.getPosition().getY()>=325 || game_object.getPosition().getY()<=275){
                double speed = game_object.getSpeed();
                game_object.setSpeed(-speed);
            }
        }
    }

    private Island getisland(Position pos ,ArrayList<Island> islands, double height, double width){
        Island ansisland = null;
        int count = 0;
        for(Island island :islands){
            count++;
            double h = island.getIsland().getFitHeight();
            double w = island.getIsland().getFitWidth();
            if((pos.getY())<(island.getIsland().getY()-h/2)){
                if (island.getIsland().getY() < pos.getY()){
                    ansisland = null;
                    return ansisland;
                }
                if((island.getIsland().getX()+w) >= (pos.getX() + width) && (island.getIsland().getX()-w)<= (pos.getX()-width)){
                    System.out.println(count);
                    ansisland = island;
                    return ansisland;
                }
            }
        }
        return ansisland;
    }


    private void moveIsland(Island island) {
        island.setPositionY(island.getPosition().getY()-island.getSpeed());
        if(island.getPosition().getY()>=islandLocationfromTopofScreen+20 || island.getPosition().getY()<=islandLocationfromTopofScreen-20){
            double speed = island.getSpeed();
            island.setSpeed(-speed);
        }

    }
    private void moveHero(Hero hero){
        double h = hero.getHero().getFitHeight();
        double w = hero.getHero().getFitWidth();
        Island residence = getisland(hero.getPosition(), islands, h, w);

//        if (hero.getPosition().getY() > islandLocationfromTopofScreen+25){
//            time.pause();
//            mainPane.getChildren().add(abyssPane);
//        }

        if (residence == null){
            double speed = Math.abs(hero.getSpeed());
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
            jump = 100;
            if (hero.getHero().getY() - hero.getSpeed() >= y - island_height/2) {
                hero.getHero().setY(y - island_height / 2 );
                double speed = hero.getSpeed();
                hero.setSpeed(-speed);
                herojump.play();
                herojump.seek(Duration.ZERO);
            }
            else if (hero.getHero().getY() - hero.getSpeed() <= y - island_height / 2 - jump) {
                hero.getHero().setY(y - island_height / 2 - jump);
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
                }
            }

        }
    }

    private boolean check_collision(Hero hero, Game_Objects game_objects) {
        double hero_height = hero.getHero().getFitHeight();
        double hero_width = hero.getHero().getFitWidth();
        double obj_height = game_objects.getImage().getFitHeight();
        double obj_width = game_objects.getImage().getFitWidth();
        if(hero.getHero().getY() + hero_height/2 <= game_objects.getImage().getY()+obj_height/2 && hero.getHero().getY() + hero_height/2 >= game_objects.getImage().getY() - obj_height/2){
            if(hero.getHero().getX()+hero_width/2 >= game_objects.getImage().getX()-obj_width/2 && hero.getHero().getX()+hero_width/2<=game_objects.getImage().getX()+obj_width/2) {
                return true;
            }
            else if(hero.getHero().getX()-hero_width/2 >= game_objects.getImage().getX()-obj_width/2 && hero.getHero().getX()-hero_width/2<=game_objects.getImage().getX()+obj_width/2) {
                return true;
            }
        }
        else if(hero.getHero().getY() - hero_height/2 <= game_objects.getImage().getY()+obj_height/2 && hero.getHero().getY() - hero_height/2 >= game_objects.getImage().getY() - obj_height/2 ){
            if(hero.getHero().getX()+hero_width/2 >= game_objects.getImage().getX()-obj_width/2 && hero.getHero().getX()+hero_width/2<=game_objects.getImage().getX()+obj_width/2) {
                return true;
            }
            else if(hero.getHero().getX()-hero_width/2 >= game_objects.getImage().getX()-obj_width/2 && hero.getHero().getX()-hero_width/2<=game_objects.getImage().getX()+obj_width/2) {
                return true;
            }
        }
        return false;
    }

    private void moveOrc(Orc orc , Island island){
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

    private void moveTNT(TNT tnt , Island island){
        if (!(island.getSpeed() == 0)){
            tnt.setPositionY(island.getPosition().getY()-island.getSpeed()-tnt.getImageViewHeight());
            if(tnt.getPosition().getY()>=325 || tnt.getPosition().getY()<=275){
                double speed = tnt.getSpeed();
                tnt.setSpeed(-speed);
            }
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
