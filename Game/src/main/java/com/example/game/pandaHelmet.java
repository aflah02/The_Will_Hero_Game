package com.example.game;

import javafx.animation.SequentialTransition;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.File;

public class pandaHelmet {
    private ImageView pandaHelmet;
    private String helmetImage = "src/main/resources/com/example/game/images/panda.png";
    private String streakpath = "src/main/resources/com/example/game/images/pandastreak.png";
    private int height,width;
    private int flag =0;
    public pandaHelmet(AnchorPane anchorPane, Position position, int width, int height){
        createImage(helmetImage);
        pandaHelmet.setX(position.getX());
        pandaHelmet.setY(position.getY());
        pandaHelmet.setFitWidth(width);
        pandaHelmet.setFitHeight(height);
        this.height = height;
        this.width = width;
    }

    public ImageView getPandaHelmet() {
        return pandaHelmet;
    }

    public void createImage(String path){
        this.pandaHelmet = new ImageView(new File(path).toURI().toString());
    }

    public void animate() {
        Transition animation = new Transition() {
            {setCycleDuration(Duration.millis(100));}
            @Override
            protected void interpolate(double fraction) {
                    Image streakimage = new Image(new File(streakpath).toURI().toString());
                    pandaHelmet.setImage(streakimage);
                    pandaHelmet.setFitWidth(width*2.7);
            }
        };
        Transition animation2 = new Transition() {
            {setCycleDuration(Duration.millis(200));}
            @Override
            protected void interpolate(double fraction) {
                Image streakimage = new Image(new File(helmetImage).toURI().toString());
                pandaHelmet.setImage(streakimage);
                pandaHelmet.setFitWidth(width);
            }
        };
        animation.setCycleCount(1);
        animation.setCycleCount(1);
        SequentialTransition trans = new SequentialTransition();
        trans.getChildren().addAll(animation,animation2);
        trans.setCycleCount(1);
        trans.play();
    }
}
