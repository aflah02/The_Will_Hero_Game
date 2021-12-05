package com.example.game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.File;

public class Chest_Coin implements Coins{
    int flag;
    String path1 = "src/main/resources/com/example/game/images/chest1.png";
    String path2 = "src/main/resources/com/example/game/images/chest2.png";
    String path3 = "src/main/resources/com/example/game/images/chest3.png";
    String path4 = "src/main/resources/com/example/game/images/chest4.png";
    String path5 = "src/main/resources/com/example/game/images/chest5.png";
    String path6 = "src/main/resources/com/example/game/images/chest6.png";
    String path7 = "src/main/resources/com/example/game/images/chest7.png";
    String path8 = "src/main/resources/com/example/game/images/chest8.png";
    String path9 = "src/main/resources/com/example/game/images/chest9.png";
    private ImageView chest;

    Chest_Coin(AnchorPane anchorPane, Position position, int width, int height){
        this.chest = new ImageView();
        Image img = new Image(new File(path1).toURI().toString());
        chest.setImage(img);
        chest.setX(position.getX());
        chest.setY(position.getY());
        chest.setFitWidth(width);
        chest.setFitHeight(height);
        anchorPane.getChildren().add(chest);
        flag=0;
    }
    private Boolean isCollected;
    Chest_Coin(){

    }
    public Boolean getIsCollected() {
        return isCollected;
    }
    public void collide(Player player){
    }

    public void transition(AnchorPane anchorPane){
        double x = chest.getX();
        double y = chest.getY();
        double h = chest.getFitHeight();
        double w = chest.getFitWidth();
        if(flag==20){
            anchorPane.getChildren().remove(chest);
            this.chest = new ImageView();
            Image img = new Image(new File(path2).toURI().toString());
            chest.setImage(img);
            chest.setX(x);
            chest.setY(y);
            chest.setFitWidth(w);
            chest.setFitHeight(h);
            anchorPane.getChildren().add(chest);
        }
        else if(flag==40){
            anchorPane.getChildren().remove(chest);
            this.chest = new ImageView();
            Image img = new Image(new File(path3).toURI().toString());
            chest.setImage(img);
            chest.setX(x);
            chest.setY(y);
            chest.setFitWidth(w);
            chest.setFitHeight(h);
            anchorPane.getChildren().add(chest);
        }
        else if(flag==60){
            anchorPane.getChildren().remove(chest);
            this.chest = new ImageView();
            Image img = new Image(new File(path4).toURI().toString());
            chest.setImage(img);
            chest.setX(x);
            chest.setY(y);
            chest.setFitWidth(w);
            chest.setFitHeight(h);
            anchorPane.getChildren().add(chest);
        }
        else if(flag==80){
            anchorPane.getChildren().remove(chest);
            this.chest = new ImageView();
            Image img = new Image(new File(path5).toURI().toString());
            chest.setImage(img);
            chest.setX(x);
            chest.setY(y);
            chest.setFitWidth(w);
            chest.setFitHeight(h);
            anchorPane.getChildren().add(chest);
        }
        else if(flag==100){
            anchorPane.getChildren().remove(chest);
            this.chest = new ImageView();
            Image img = new Image(new File(path6).toURI().toString());
            chest.setImage(img);
            chest.setX(x);
            chest.setY(y);
            chest.setFitWidth(w);
            chest.setFitHeight(h);
            anchorPane.getChildren().add(chest);
        }
        else if(flag==120){
            anchorPane.getChildren().remove(chest);
            this.chest = new ImageView();
            Image img = new Image(new File(path7).toURI().toString());
            chest.setImage(img);
            chest.setX(x);
            chest.setY(y);
            chest.setFitWidth(w);
            chest.setFitHeight(h);
            anchorPane.getChildren().add(chest);
        }
        else if(flag==140){
            anchorPane.getChildren().remove(chest);
            this.chest = new ImageView();
            Image img = new Image(new File(path8).toURI().toString());
            chest.setImage(img);
            chest.setX(x);
            chest.setY(y);
            chest.setFitWidth(w);
            chest.setFitHeight(h);
            anchorPane.getChildren().add(chest);
        }
        else if(flag==160){
            anchorPane.getChildren().remove(chest);
            this.chest = new ImageView();
            Image img = new Image(new File(path9).toURI().toString());
            chest.setImage(img);
            chest.setX(x);
            chest.setY(y);
            chest.setFitWidth(w);
            chest.setFitHeight(h);
            anchorPane.getChildren().add(chest);
        }
        flag++;
    }
}
