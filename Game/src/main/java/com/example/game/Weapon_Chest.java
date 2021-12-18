package com.example.game;

import javafx.animation.Transition;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;

public class Weapon_Chest extends Chest{
    Weapon weapon;
    String path1 = "src/main/resources/com/example/game/images/chest1.png";
    String path2 = "src/main/resources/com/example/game/images/chest2.png";
    String path3 = "src/main/resources/com/example/game/images/chest3.png";
    String path4 = "src/main/resources/com/example/game/images/chest4.png";
    String path5 = "src/main/resources/com/example/game/images/chest5.png";
    String path6 = "src/main/resources/com/example/game/images/chest6.png";
    String path7 = "src/main/resources/com/example/game/images/chest7.png";
    String path8 = "src/main/resources/com/example/game/images/chest8.png";
    private final ImageView chest;
    private Position position;
    public String[] imagePaths;
    public ArrayList<Image> chestAnimations;

    Weapon_Chest(AnchorPane anchorPane, Position position, int width, int height, String WeaponName){
        this.imagePaths = new String[]{path1, path2, path3, path4, path5, path6, path7, path8};
        chestAnimations = new ArrayList<>();
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
        if (WeaponName == "Sword"){
            this.weapon = new Sword();
        }
        else{
            this.weapon = new Lance();
        }
    }

    public void animateChest() {
        Weapon_Chest chest = this;
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
    public void setPosition(Position position) {
        this.position = position;
        chest.setX(position.getX());
        chest.setY(position.getY());
    }
    @Override
    public Position getPosition() {
        return position;
    }
}
