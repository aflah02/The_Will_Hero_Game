package com.example.game;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;

public class View_Manager {
    private final transient AnchorPane mainPane;
    private final transient Scene mainScene;
    private final transient Stage mainStage;
    private final transient ArrayList<MediaPlayer> players;
    private transient MediaView view;
    private final transient AnchorPane helmetChooseMenu;
    private String helmetChosen;
    View_Manager(Stage stage) {
        this.helmetChosen = "Panda";
        helmetChooseMenu = HelmetChoices();
        players = new ArrayList<>();
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane,800,600);
        if(stage==null){
            mainStage = new Stage();
        }
        else{
            mainStage = stage;
        }
        mainStage.setScene(mainScene);
        String pathforgifbg = "src/main/resources/com/example/game/videos/backgroundasgif.gif";
        String path = "src/main/resources/com/example/game/videos/openingBackground.mp4";
        String pathforbgaudio = "src/main/resources/com/example/game/audios/mainscreenbackgroundaudio.mp3";
        Image img = new Image(new File(pathforgifbg).toURI().toString());
        ImageView backgroundasgif = new ImageView(img);
        backgroundasgif.setFitHeight(600);
        backgroundasgif.setFitWidth(800);
        mainPane.getChildren().add(backgroundasgif);
        Media media = new Media(new File(pathforbgaudio).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        MediaView mediaView = new MediaView(mediaPlayer);
        mediaPlayer.setAutoPlay(true);
        mainPane.getChildren().add(mediaView);
        createButtons(mediaPlayer);
        players.add(mediaPlayer);
    }

    private void createButtons(MediaPlayer player){
        //Image of Gradient to stop video showing extra buttons
        String gradient = "src/main/resources/com/example/game/images/GradientBackground.jpg";
        ImageView grad = new ImageView(new File(gradient).toURI().toString());
        grad.setFitHeight(147);
        grad.setFitWidth(65);
        grad.setLayoutX(369);
        grad.setLayoutY(390);
        mainPane.getChildren().add(grad);
        //Start button
        StartButton button = new StartButton(this);
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    button.handler(helmetChosen);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        button.setLayoutX(340);
        button.setLayoutY(260);
        mainPane.getChildren().add(button);
        //Load Button
        LoadButton button1 = new LoadButton();
        button1.setLayoutX(425);
        button1.setLayoutY(380);
        mainPane.getChildren().add(button1);
        button1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override

            public void handle(MouseEvent mouseEvent) {
                try {
                    LoadGame();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        //Google Button
        GoogleButton button2 = new GoogleButton();
        button2.setLayoutX(270);
        button2.setLayoutY(380);
        mainPane.getChildren().add(button2);
        //Music Button
        MusicButton button3 = new MusicButton(players);
        button3.setLayoutX(625);
        button3.setLayoutY(50);
        mainPane.getChildren().add(button3);
        Exit_Button exit = new Exit_Button(this.mainStage);
        exit.setLayoutX(700);
        exit.setLayoutY(50);
        mainPane.getChildren().add(exit);
        button2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override

            public void handle(MouseEvent mouseEvent) {
                mainPane.getChildren().add(helmetChooseMenu);
            }
        });
        //Record Button
        RecordButton recordButton = new RecordButton(this.mainPane, this.mainStage);
        recordButton.setLayoutX(550);
        recordButton.setLayoutY(50);
        mainPane.getChildren().add(recordButton);
        recordButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override

            public void handle(MouseEvent mouseEvent) {
                start();
            }
        });
        //..................................
        Button Statsbutton = new Button();
        Statsbutton.setLayoutX(475);
        Statsbutton.setLayoutY(50);
        ImageView img;
        String imagePath = "src/main/resources/com/example/game/images/statbutton.png";
        img = new ImageView(new File(imagePath).toURI().toString());
        img.setFitHeight(50);
        img.setFitWidth(50);
        Statsbutton.setGraphic(img);
        String STYLE = "-fx-background-color:transparent; -fx-background-size: cover";
        Statsbutton.setStyle(STYLE);
        Statsbutton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Statsbutton.setEffect(new DropShadow());
            }
        });
        Statsbutton.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Statsbutton.setEffect(null);
            }
        });

        Statsbutton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String[] cmd = {"src\\main\\java\\com\\example\\game\\analyze.bat", ""};
                Process p = null;
                try {
                    p = Runtime.getRuntime().exec(cmd);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("analyze" + p);
                mainPane.getChildren().add(StatsButton());
            }
        });
        mainPane.getChildren().add(Statsbutton);
    }

    public void LoadGame() throws IOException {
        AnchorPane anchorPane = new AnchorPane();
        for (MediaPlayer mediaPlayer: players){
            mediaPlayer.setMute(true);
        }
        LoadSaveFile loadSaveFile = new LoadSaveFile();
        try {
            Path path = Paths.get("SaveFiles/save.ser");
            if (!Files.exists(path)) {
                throw new NoLoadFileFoundException();
            }
            SaveFileReturn saveFileReturn = loadSaveFile.loadGameState("SaveFiles/save.ser", anchorPane);
            LoadPage loadPage  = new LoadPage(this.getMainStage(), saveFileReturn, anchorPane);
            loadPage.start();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (NoLoadFileFoundException e){
            System.out.println(e.getMessage());
        }

    }


    public AnchorPane getMainPane() {
        return mainPane;
    }

    public Scene getMainScene() {
        return mainScene;
    }

    public Stage getMainStage() {
        Image icon = new Image(new File("src/main/resources/com/example/game/images/logo.png").toURI().toString());
        mainStage.getIcons().add(icon);
        mainStage.setTitle("Will Hero");
        mainStage.setResizable(false);
        return mainStage;
    }

    public void changeScene(Scene scene){
        for(MediaPlayer player : players ){
            mainPane.getChildren().remove(player);
            player.setMute(true);
        }

        mainStage.setScene(scene);
        mainStage.show();
    }

    public void removevideo() {
        for(MediaPlayer player : players ){
            mainPane.getChildren().remove(player);
            player.setMute(true);
        }
        mainPane.getChildren().remove(view);
        mainStage.show();
    }

    private AnchorPane StatsButton() {
        AnchorPane Statspane = new AnchorPane();
        Statspane.setPrefHeight(600);
        Statspane.setPrefWidth(800);
        Statspane.setLayoutX(0);
        Statspane.setLayoutY(0);
        String bg1 = "src/main/resources/com/example/game/images/cloud2.jpeg";
        Image bg = new Image(new File(bg1).toURI().toString(),800,600,false,true);
        BackgroundImage image = new BackgroundImage(bg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,null);
        Statspane.setBackground(new Background(image));
        //.................................................
        String graphpath = "src/main/java/com/example/game/point.png";
        ImageView statsimg = new ImageView(new File(graphpath).toURI().toString());
        statsimg.setFitWidth(450);
        statsimg.setFitHeight(450);
        statsimg.setX(150);
        statsimg.setY(100);
        Statspane.getChildren().add(statsimg);

        //.................................................
        CloseMenuButton closeMenuButton = new CloseMenuButton();
        closeMenuButton.setLayoutX(725);
        closeMenuButton.setLayoutY(10);
        closeMenuButton.setOnMouseClicked(mouseEvent -> {
            mainPane.getChildren().remove(Statspane);
        });
        Statspane.getChildren().add(closeMenuButton);

        return Statspane;
    }

    private AnchorPane HelmetChoices() {
        AnchorPane HelmetChoices = new AnchorPane();
        HelmetChoices.setPrefHeight(600);
        HelmetChoices.setPrefWidth(800);
        HelmetChoices.setLayoutX(0);
        HelmetChoices.setLayoutY(0);
        String bg1 = "src/main/resources/com/example/game/images/helmetwindow.png";
        Image bg = new Image(new File(bg1).toURI().toString(),800,600,false,true);
        BackgroundImage image = new BackgroundImage(bg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,null);
        HelmetChoices.setBackground(new Background(image));
        //.................................................
        Text sometext = new Text();
        sometext.setText("Choose Your Helmet");
        sometext.setFont(Font.font ("Verdana", 50));
        sometext.setFill(Color.WHITE);
        sometext.setX(160);
        sometext.setY(170);
        HelmetChoices.getChildren().add(sometext);
        //
        HelmetButton Panda = new HelmetButton("Panda", 40, 200);
        HelmetButton Angel = new HelmetButton("Angel", 230, 200);
        HelmetButton Loki = new HelmetButton("Loki", 420, 200);
        HelmetButton Jotun = new HelmetButton("Jotun", 610, 200);
        HelmetChoices.getChildren().add(Loki);
        HelmetChoices.getChildren().add(Angel);
        HelmetChoices.getChildren().add(Panda);
        HelmetChoices.getChildren().add(Jotun);
        this.helmetChosen = "Panda";
        Panda.setactive();
        Loki.setOnMouseClicked(mouseEvent -> {
            this.helmetChosen = "Loki";
            Loki.setactive();
            Angel.setinactive();
            Panda.setinactive();
            Jotun.setinactive();
        });
        Angel.setOnMouseClicked(mouseEvent -> {
            this.helmetChosen = "Angel";
            Angel.setactive();
            Loki.setinactive();
            Panda.setinactive();
            Jotun.setinactive();
        });
        Panda.setOnMouseClicked(mouseEvent -> {
            this.helmetChosen = "Panda";
            Panda.setactive();
            Loki.setinactive();
            Angel.setinactive();
            Jotun.setinactive();
        });
        Jotun.setOnMouseClicked(mouseEvent -> {
            this.helmetChosen = "Jotun";
            Jotun.setactive();
            Loki.setinactive();
            Panda.setinactive();
            Angel.setinactive();
        });
        CloseMenuButton closeMenuButton = new CloseMenuButton();
        closeMenuButton.setLayoutX(725);
        closeMenuButton.setLayoutY(10);
        closeMenuButton.setOnMouseClicked(mouseEvent -> {
            mainPane.getChildren().remove(helmetChooseMenu);
        });
        HelmetChoices.getChildren().add(closeMenuButton);
        StartButton button = new StartButton(this);
        button.setLayoutX(345);
        button.setLayoutY(435);
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    button.handler(helmetChosen);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        HelmetChoices.getChildren().add(button);
        return HelmetChoices;
    }

    public void start() {
        String path = "src/main/java/com/example/game/output.mp4";
        Media media = new Media(new File(path).toURI().toString());
        System.out.println(new File(path).toURI());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(1);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setVolume(0.3);
        MediaView mediaView = new MediaView(mediaPlayer);
        mainPane.getChildren().add(mediaView);
        mediaView.setFitWidth(800);
        mediaView.setFitHeight(600);
        mediaPlayer.play();
    }
}
