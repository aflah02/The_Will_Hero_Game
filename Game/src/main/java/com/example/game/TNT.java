package com.example.game;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
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

    @Override
    public Position getPosition() {
        return position;
    }

    private int timeToBurst;
    int flag , flag1;
    private Island islandofResidence;
    private Boolean isBurst;
    private int Radius;
    private double initpos;
    private double speed;
    String pathgif = "src/main/resources/com/example/game/images/tnt1.png";
    String path1 = "src/main/resources/com/example/game/images/tnt1.png";
    String path2 = "src/main/resources/com/example/game/images/tnt2.png";
    String path3 = "src/main/resources/com/example/game/images/tnt3.png";
    String path4 = "src/main/resources/com/example/game/images/tnt4.png";
    String path5 = "src/main/resources/com/example/game/images/tnt5.png";
    String path6 = "src/main/resources/com/example/game/images/tnt6.png";
    String path7 = "src/main/resources/com/example/game/images/tnt7.png";
    String path8 = "src/main/resources/com/example/game/images/tnt8.png";
    String path9 = "src/main/resources/com/example/game/images/tnt9.png";
    private ArrayList<ImageView> images;

    private ImageView tnt;
    TNT(AnchorPane anchorPane, Position position, int width, int height, double speed, Island islandofResidence){
        images = new ArrayList<>();
        this.tnt = new ImageView();
        this.speed = speed;
        this.position = position;
        this.initpos = position.getY();
        this.islandofResidence = islandofResidence;
        Image img = new Image(new File(pathgif).toURI().toString());
        tnt.setImage(img);
        tnt.setX(position.getX());
        tnt.setY(position.getY());
        tnt.setFitWidth(width);
        tnt.setFitHeight(height);
        tnt.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                transition(anchorPane);
            }
        });
        addimages();
        this.flag =0;
        anchorPane.getChildren().add(tnt);
    }

    private void addimages() {
        ImageView original = this.tnt;
        double x = tnt.getX();
        double y = tnt.getY();
        double h = tnt.getFitHeight();
        double w = tnt.getFitWidth();
        this.tnt = new ImageView();
        Image img = new Image(new File(path2).toURI().toString());
        tnt.setImage(img);
        tnt.setX(x);
        tnt.setY(y);
        tnt.setFitWidth(w);
        tnt.setFitHeight(h);
        images.add(this.tnt);
        this.tnt = new ImageView();
        img = new Image(new File(path3).toURI().toString());
        tnt.setImage(img);
        tnt.setX(x);
        tnt.setY(y);
        tnt.setFitWidth(w);
        tnt.setFitHeight(h);
        images.add(this.tnt);
        this.tnt = new ImageView();
        img = new Image(new File(path4).toURI().toString());
        tnt.setImage(img);
        tnt.setX(x);
        tnt.setY(y);
        tnt.setFitWidth(w);
        tnt.setFitHeight(h);
        images.add(this.tnt);
        this.tnt = new ImageView();
        img = new Image(new File(path5).toURI().toString());
        tnt.setImage(img);
        tnt.setX(x);
        tnt.setY(y);
        tnt.setFitWidth(w);
        tnt.setFitHeight(h);
        images.add(this.tnt);
        this.tnt = new ImageView();
        img = new Image(new File(path6).toURI().toString());
        tnt.setImage(img);
        tnt.setX(x);
        tnt.setY(y);
        tnt.setFitWidth(w);
        tnt.setFitHeight(h);
        images.add(this.tnt);
        this.tnt = new ImageView();
        img = new Image(new File(path7).toURI().toString());
        tnt.setImage(img);
        tnt.setX(x);
        tnt.setY(y);
        tnt.setFitWidth(w);
        tnt.setFitHeight(h);
        images.add(this.tnt);
        this.tnt = new ImageView();
        img = new Image(new File(path8).toURI().toString());
        tnt.setImage(img);
        tnt.setX(x);
        tnt.setY(y);
        tnt.setFitWidth(w);
        tnt.setFitHeight(h);
        images.add(this.tnt);
        this.tnt = new ImageView();
        img = new Image(new File(path9).toURI().toString());
        tnt.setImage(img);
        tnt.setX(x);
        tnt.setY(y);
        tnt.setFitWidth(w);
        tnt.setFitHeight(h);
        images.add(this.tnt);
        this.tnt = original;
    }

    public void setFlag(int flag){
        if(flag==1){
            this.flag1 = this.flag;
            this.flag =flag;
        }
        else{
            this.flag = this.flag1;
        }
    }

    private void transition(AnchorPane anchorPane) {
        if(flag==0){
            System.out.println("Called Transition");
            anchorPane.getChildren().remove(this.tnt);
            SequentialTransition show = new SequentialTransition();
            for(int i=0;i<images.size();i++){
                ImageView image = images.get(i);
                SequentialTransition transitioni = new SequentialTransition();
                FadeTransition fadeIn = getFadeTransition(image, 0.0, 1.0, 1);
                PauseTransition stayOn = new PauseTransition(Duration.millis(150));
                FadeTransition fadeOut = getFadeTransition(image, 1.0, 0.0, 1);
                transitioni.getChildren().addAll(fadeIn ,stayOn ,fadeOut);
                image.setOpacity(0);
                anchorPane.getChildren().add(image);
                show.getChildren().add(transitioni);
            }
            show.play();
            flag = 1;
        }
    }

    private FadeTransition getFadeTransition(ImageView imageView, double fromValue, double toValue, int durationInMilliseconds) {

        FadeTransition ft = new FadeTransition(Duration.millis(durationInMilliseconds), imageView);
        ft.setFromValue(fromValue);
        ft.setToValue(toValue);

        return ft;
    }

    public void setPositionY(double cord){
        tnt.setY(cord);
        position.setY(cord);
        for(ImageView image : images){
            image.setY(cord);
        }
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



    public ImageView getTnt() {
        return tnt;
    }

    public void Burst() {
        isBurst = true;
    }

    public void collide(Player player) {
    }

    public double getinitpos() {
        return initpos;
    }
}
