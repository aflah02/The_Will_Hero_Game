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
import java.util.concurrent.locks.LockSupport;

public class Coin_Chest extends Chest{
    private int flag,flag1;
    private ArrayList<Coins> coins;
    private ArrayList<ImageView> images;
    String path1 = "src/main/resources/com/example/game/images/coinchest0.png";
    String path2 = "src/main/resources/com/example/game/images/coinchest1.png";
    String path3 = "src/main/resources/com/example/game/images/coinchest2.png";
    String path4 = "src/main/resources/com/example/game/images/coinchest3.png";
    String path5 = "src/main/resources/com/example/game/images/coinchest4.png";
    String path6 = "src/main/resources/com/example/game/images/coinchest5.png";
    String path7 = "src/main/resources/com/example/game/images/coinchest6.png";
    String path8 = "src/main/resources/com/example/game/images/coinchest6.png";
    private ImageView chest;
    public ArrayList<Coins> getCoins() {
        return coins;
    }
    private Position position;
    public String[] imagePaths;

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

    Coin_Chest(AnchorPane anchorPane, Position position, int width, int height){
        this.imagePaths = new String[]{path1, path2, path3, path4, path5, path6, path7};
        images = new ArrayList<>();
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
                transition(anchorPane);
            }
        });
        anchorPane.getChildren().add(chest);
//        addimages();
        animateChest();
        this.flag =0;
        this.position = position;
    }

    private void addimages() {
        ImageView original = this.chest;
        double x = chest.getX();
        double y = chest.getY();
        double h = chest.getFitHeight();
        double w = chest.getFitWidth();
        this.chest = new ImageView();
        for (int i = 0; i < 8; i++){

        }
        Image img = new Image(new File(path2).toURI().toString());
        chest.setImage(img);
        chest.setX(x);
        chest.setY(y);
        chest.setFitWidth(w);
        chest.setFitHeight(h);
        images.add(this.chest);
        this.chest = new ImageView();
        img = new Image(new File(path3).toURI().toString());
        chest.setImage(img);
        chest.setX(x);
        chest.setY(y);
        chest.setFitWidth(w);
        chest.setFitHeight(h);
        images.add(this.chest);
        this.chest = new ImageView();
        img = new Image(new File(path4).toURI().toString());
        chest.setImage(img);
        chest.setX(x);
        chest.setY(y);
        chest.setFitWidth(w);
        chest.setFitHeight(h);
        images.add(this.chest);
        this.chest = new ImageView();
        img = new Image(new File(path5).toURI().toString());
        chest.setImage(img);
        chest.setX(x);
        chest.setY(y);
        chest.setFitWidth(w);
        chest.setFitHeight(h);
        images.add(this.chest);
        this.chest = new ImageView();
        img = new Image(new File(path6).toURI().toString());
        chest.setImage(img);
        chest.setX(x);
        chest.setY(y);
        chest.setFitWidth(w);
        chest.setFitHeight(h);
        images.add(this.chest);
        this.chest = new ImageView();
        img = new Image(new File(path7).toURI().toString());
        chest.setImage(img);
        chest.setX(x);
        chest.setY(y);
        chest.setFitWidth(w);
        chest.setFitHeight(h);
        images.add(this.chest);
        this.chest = new ImageView();
        img = new Image(new File(path8).toURI().toString());
        chest.setImage(img);
        chest.setX(x);
        chest.setY(y);
        chest.setFitWidth(w);
        chest.setFitHeight(h);
        images.add(this.chest);
        this.chest = original;
    }
    public void animateChest() {
        SequentialTransition show = new SequentialTransition();
        for (String path: imagePaths){
            System.out.println("Transitioning Chest");
            chest.setImage(new Image(new File(path).toURI().toString()));
            SequentialTransition transitioni = new SequentialTransition();
            FadeTransition fadeIn = getFadeTransition(chest, 0.0, 1.0, 10000);
            PauseTransition stayOn = new PauseTransition(Duration.millis(1500));
            FadeTransition fadeOut = getFadeTransition(chest, 1.0, 0.0, 10000);
            transitioni.getChildren().addAll(fadeIn ,stayOn ,fadeOut);
            show.getChildren().add(transitioni);
        }
        show.play();
        chest.setImage(new Image(new File(path1).toURI().toString()));
    }
    @Override
    public void collide(Player player) {

    }
    public void transition(AnchorPane anchorPane){
        if(flag==0){
            System.out.println("Called Transition");
            anchorPane.getChildren().remove(this.chest);
            SequentialTransition show = new SequentialTransition();
            for(int i=0;i<images.size();i++){
                ImageView image = images.get(i);
                SequentialTransition transitioni = new SequentialTransition();
                FadeTransition fadeIn = getFadeTransition(image, 0.0, 1.0, 1);
                PauseTransition stayOn = new PauseTransition(Duration.millis(150));
                if(i!=images.size()-1){
                    FadeTransition fadeOut = getFadeTransition(image, 1.0, 0.0, 1);
                    transitioni.getChildren().addAll(fadeIn ,stayOn ,fadeOut);
                }
                else{
                    transitioni.getChildren().addAll(fadeIn ,stayOn);
                }
                image.setOpacity(0);
                anchorPane.getChildren().add(image);
                show.getChildren().add(transitioni);
            }
            show.play();
            flag = 1;
        }
    }

    public void clearmethod(AnchorPane anchorPane) {
        for(ImageView image: images){
            anchorPane.getChildren().remove(image);
        }
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

    private FadeTransition getFadeTransition(ImageView imageView, double fromValue, double toValue, int durationInMilliseconds) {

        FadeTransition ft = new FadeTransition(Duration.millis(durationInMilliseconds), imageView);
        ft.setFromValue(fromValue);
        ft.setToValue(toValue);

        return ft;
    }

    @Override
    public ImageView getImage() {
        return this.chest;
    }
}
