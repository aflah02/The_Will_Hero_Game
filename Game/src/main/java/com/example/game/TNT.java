package com.example.game;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

public class TNT extends gameObstacles implements Serializable {
    private Position position;
    private int timeToBurst;
    private Island islandofResidence;
    private Boolean isBurst;
    private int Radius;
    private double initpos;
    private double speed;
    private String path1 = "src/main/resources/com/example/game/images/tnt1.png";
    private String path2 = "src/main/resources/com/example/game/images/tnt2.png";
    private String path3 = "src/main/resources/com/example/game/images/tnt3.png";
    private String path4 = "src/main/resources/com/example/game/images/tnt4.png";
    private String path5 = "src/main/resources/com/example/game/images/tnt5.png";
    private String path6 = "src/main/resources/com/example/game/images/tnt6.png";
    private String path7 = "src/main/resources/com/example/game/images/tnt7.png";
    private String path8 = "src/main/resources/com/example/game/images/tnt8.png";
    private String path9 = "src/main/resources/com/example/game/images/tnt9.png";
    private String path10 = "src/main/resources/com/example/game/images/tntOver.png";
    public String[] imagePaths;
    public transient ArrayList<Image> tntAnimations;
    private final transient ImageView TNTImageView;
    private transient MediaPlayer burstsound;

    TNT(AnchorPane anchorPane, Position position, int width, int height, double speed, Island islandofResidence,MediaPlayer burstsound){
        this.burstsound = burstsound;
        this.isBurst = false;
        this.width = width;
        this.height = height;
        this.imagePaths = new String[]{path2, path3, path4, path5, path6, path7, path8, path9, path10};
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
        TNTImageView.setOnMouseClicked(mouseEvent -> animateTNT(0));
        anchorPane.getChildren().add(TNTImageView);
        this.position = position;
        this.islandofResidence = islandofResidence;
    }

    @Override
    public String getName(){
        return "TNT";
    }

    @Override
    public double getImageViewWidth() {
        return TNTImageView.getFitWidth();
    }

    @Override
    public double getImageViewHeight() {
        return TNTImageView.getFitHeight();
    }

    public void animateTNT(double a) {
        if(!isBurst){
            TNT tnt = this;
            Transition animation = new Transition() {
                {setCycleDuration(Duration.millis(500));}
                @Override
                protected void interpolate(double fraction) {
                    int index = (int) (fraction*(tntAnimations.size()-1));
                    tnt.getTNTImageView().setImage(tntAnimations.get(index));
                    double X = tnt.getTNTImageView().getX();
                    tnt.getTNTImageView().setX(X + a/9);
                }
            };
            animation.setOnFinished(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    Burst();
                }
            });
            burstsound.play();
            burstsound.seek(Duration.ZERO);
            animation.setCycleCount(1);
            animation.play();
        }
    }

    @Override
    public ImageView getImage(){
        return this.TNTImageView;
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
        if(!isBurst){
            System.out.println("burst");
            isBurst = true;
        }

    }

    public void setIslandofResidence(Island island) {
        this.islandofResidence = island;
    }

    @Override
    public void collide(Hero hero) {
        if(!isBurst){
            if(hero.getHero().getY() + hero.getHero().getFitHeight()*3/4 <= this.getTNTImageView().getY()){
                animateTNT(0);
            }
            else{
                animateTNT(0);
            }
        }
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

    public Boolean getBurst() {
        return isBurst;
    }
}
