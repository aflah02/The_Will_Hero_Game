package com.example.game;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Transition;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.locks.LockSupport;

public class Coin_Chest extends Chest{
    private ArrayList<Coins> coins;
    String path1 = "src/main/resources/com/example/game/images/coinchest0.png";
    String path2 = "src/main/resources/com/example/game/images/coinchest1.png";
    String path3 = "src/main/resources/com/example/game/images/coinchest2.png";
    String path4 = "src/main/resources/com/example/game/images/coinchest3.png";
    String path5 = "src/main/resources/com/example/game/images/coinchest4.png";
    String path6 = "src/main/resources/com/example/game/images/coinchest5.png";
    String path7 = "src/main/resources/com/example/game/images/coinchest6.png";
    private final ImageView chest;
    private Position position;
    public String[] imagePaths;
    public ArrayList<Image> chestAnimations;
    Island islandOfResidence;
    private double speed;
    Coin_Chest(AnchorPane anchorPane, Position position, int width, int height, Island islandOfResidence){
        this.imagePaths = new String[]{path1, path2, path3, path4, path5, path6, path7};
        chestAnimations = new ArrayList<>();
        this.islandOfResidence = islandOfResidence;
        for (String path: imagePaths){
            chestAnimations.add(new Image(new File(path).toURI().toString()));
        }
        this.chest = new ImageView();
        Image img = new Image(new File(path1).toURI().toString());
        chest.setImage(img);
        chest.setX(position.getX());
        chest.setY(position.getY());
        chest.setFitWidth(width);
        chest.setFitHeight(height);
        chest.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                animateChest();
            }
        });
        anchorPane.getChildren().add(chest);
        this.position = position;
        this.speed = islandOfResidence.getSpeed();
    }

    public void animateChest() {
        Coin_Chest chest= this;
        Transition animation = new Transition() {
            {setCycleDuration(Duration.millis(500));}
            @Override
            protected void interpolate(double fraction) {
                int index = (int) (fraction*(chestAnimations.size()-1));
                chest.getImage().setImage(chestAnimations.get(index));
            }
        };
        animation.play();
        animation.setCycleCount(1);
    }
    @Override
    public void collide(Player player) {
    }
    @Override
    public ImageView getImage() {
        return this.chest;
    }

    @Override
    public Island getIslandofResidence() {
        return this.islandOfResidence;
    }

    @Override
    public void setSpeed(double v) {
        this.speed = v;
    }

    @Override
    public void setPositionY(double v) {
        this.position.setY(v);
        this.chest.setY(v);
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
        chest.setX(position.getX());
        chest.setY(position.getY());
    }
    @Override
    public Position getPosition() {
        return position;
    }

    public ArrayList<Coins> getCoins() {
        return coins;
    }
}
