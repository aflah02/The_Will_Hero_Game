package com.example.game;

import javafx.animation.SequentialTransition;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.File;
import java.io.Serial;
import java.io.Serializable;

public class Helmet implements Serializable {
    private transient ImageView Helmet;
    private final String pandaHelmetImage = "src/main/resources/com/example/game/images/panda.png";
    private final String pandaStreakPath = "src/main/resources/com/example/game/images/panda_streak.png";
    private final String angelHelmetImage = "src/main/resources/com/example/game/images/angel.png";
    private final String angelStreakPath = "src/main/resources/com/example/game/images/angelstreak.png";
    private final String jotunHelmetImage = "src/main/resources/com/example/game/images/jotun.png";
    private final String jotunStreakPath = "src/main/resources/com/example/game/images/jotunstreak.png";
    private final String lokiHelmetImage = "src/main/resources/com/example/game/images/loki.png";
    private final String lokiStreakPath = "src/main/resources/com/example/game/images/lokistreak.png";
    private final int height;
    private final int width;
    private final int flag =0;
    private final String helmetName;
    public Helmet(AnchorPane anchorPane, Position position, int width, int height, String character){
        //this.helmetName = character;
        if(character==null){
            this.helmetName = "Panda";
        }
        else{
            this.helmetName = character;
        }
        if(helmetName.equals("Loki")){
            createImage(lokiHelmetImage, character);
        }
        else if(helmetName.equals("Jotun")){
            createImage(jotunHelmetImage, character);
        }
        else if(helmetName.equals("Panda")){
            createImage(pandaHelmetImage, character);
        }
        else{
            createImage(angelHelmetImage, character);
        }
        Helmet.setX(position.getX());
        Helmet.setY(position.getY());
        Helmet.setFitWidth(width);
        Helmet.setFitHeight(height);
        this.height = height;
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public ImageView getHelmet() {
        return Helmet;
    }

    public void createImage(String path, String Character){
        this.Helmet = new ImageView(new File(path).toURI().toString());
    }
    
    public void animate(Weapon weapon) {
        Transition animation = new Transition() {
            {setCycleDuration(Duration.millis(300));}
            @Override
            protected void interpolate(double fraction) {
                Image streakimage;
                if(helmetName.equals("Loki")){
                    streakimage = new Image(new File(lokiStreakPath).toURI().toString());
                }
                else if(helmetName.equals("Jotun")){
                    streakimage = new Image(new File(jotunStreakPath).toURI().toString());
                }
                else if(helmetName.equals("Panda")){
                    streakimage = new Image(new File(pandaStreakPath).toURI().toString());
                }
                else{
                    streakimage = new Image(new File(angelStreakPath).toURI().toString());
                }

                Helmet.setImage(streakimage);
                    Helmet.setFitWidth(width);
                    Helmet.setFitWidth(width*2);
                    Helmet.setX(75-width);
                    if (weapon != null){
                        if (weapon.getName().equals("Lance")){
                            weapon.getimage().setX(weapon.getimage().getX()+60);
                            weapon.getimage().setFitWidth(100);
                            weapon.getimage().setFitHeight(60);
                        }
                        else{
                            weapon.getimage().setX(weapon.getimage().getX()+80);
                            weapon.getimage().setRotate(180);
                        }
                    }
            }
        };
        Transition animation2 = new Transition() {
            {setCycleDuration(Duration.millis(300));}
            @Override
            protected void interpolate(double fraction) {
                Image streakimage;

                if(helmetName.equals("Loki")){
                    streakimage = new Image(new File(lokiHelmetImage).toURI().toString());
                }
                else if(helmetName.equals("Jotun")){
                    streakimage = new Image(new File(jotunHelmetImage).toURI().toString());
                }
                else if(helmetName.equals("Panda")){
                    streakimage = new Image(new File(pandaHelmetImage).toURI().toString());
                }
                else{
                    streakimage = new Image(new File(angelHelmetImage).toURI().toString());
                }
                Helmet.setImage(streakimage);
                Helmet.setFitWidth(height);
                Helmet.setFitWidth(width);
                Helmet.setX(75);
                if (weapon != null){
                    if (weapon.getName().equals("Lance")){
                        weapon.getimage().setX(75);
                        weapon.getimage().setFitWidth(80);
                        weapon.getimage().setFitHeight(40);
                    }
                    else{
                        weapon.getimage().setX(75-40);
                        weapon.getimage().setRotate(0);
                    }
                }
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
