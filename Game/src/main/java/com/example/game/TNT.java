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

public class TNT extends gameObstacles{
    private Position position;
    private int timeToBurst;
    private final Island islandofResidence;
    private Boolean isBurst;
    private int Radius;
    private double initpos;
    private double speed;
    String path1 = "src/main/resources/com/example/game/images/tnt1.png";
    String path2 = "src/main/resources/com/example/game/images/tnt2.png";
    String path3 = "src/main/resources/com/example/game/images/tnt3.png";
    String path4 = "src/main/resources/com/example/game/images/tnt4.png";
    String path5 = "src/main/resources/com/example/game/images/tnt5.png";
    String path6 = "src/main/resources/com/example/game/images/tnt6.png";
    String path7 = "src/main/resources/com/example/game/images/tnt7.png";
    String path8 = "src/main/resources/com/example/game/images/tnt8.png";
    String path9 = "src/main/resources/com/example/game/images/tnt9.png";
    public String[] imagePaths;
    public ArrayList<Image> tntAnimations;
    private final ImageView TNTImageView;

    TNT(AnchorPane anchorPane, Position position, int width, int height, double speed, Island islandofResidence){
        this.imagePaths = new String[]{path1, path2, path3, path4, path5, path6, path7, path8, path9};
        tntAnimations = new ArrayList<>();
        for (String path: imagePaths){
            tntAnimations.add(new Image(new File(path).toURI().toString()));
        }
        this.TNTImageView = new ImageView();
        Image img = new Image(new File(path1).toURI().toString());
        TNTImageView.setImage(img);
        TNTImageView.setX(position.getX());
        TNTImageView.setY(position.getY());
        TNTImageView.setFitWidth(width);
        TNTImageView.setFitHeight(height);
        TNTImageView.setOnMouseClicked(mouseEvent -> animateTNT());
        anchorPane.getChildren().add(TNTImageView);
        this.position = position;
        this.islandofResidence = islandofResidence;
    }

    public void animateTNT() {
        TNT tnt = this;
        Transition animation = new Transition() {
            {setCycleDuration(Duration.millis(500));}
            @Override
            protected void interpolate(double fraction) {
                int index = (int) (fraction*(tntAnimations.size()-1));
                tnt.getTNTImageView().setImage(tntAnimations.get(index));
            }
        };
        animation.play();
        animation.setCycleCount(1);
    }

    @Override
    public double getSpeed() {
        return speed;
    }

    public int getTimeToBurst() {
        return timeToBurst;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public Island getIslandofResidence() {
        return islandofResidence;
    }

    public void setinitpos(double pos) {
        this.initpos = initpos;
    }

    public ImageView getTNTImageView() {
        return TNTImageView;
    }

    public void Burst() {
        isBurst = true;
    }

    public void collide(Player player) {
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
        TNTImageView.setX(position.getX());
        TNTImageView.setY(position.getY());
    }

    @Override
    public Position getPosition() {
        return position;
    }

    public double getinitpos() {
        return initpos;
    }

    @Override
    public void setPositionY(double v) {
        this.position.setY(v);
        this.TNTImageView.setY(v);
    }
}
