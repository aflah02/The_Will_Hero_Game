package com.example.game;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.File;
import java.util.ArrayList;

public class moveScreenButton extends Button {
    ArrayList<Island> listOfIslands;
    ArrayList<Game_Objects> listOfGameObjects;

    moveScreenButton(double x , double y, ArrayList<Island> listOfIslands, ArrayList<Game_Objects> listOfGameObjects){
        this.listOfIslands = listOfIslands;
        this.listOfGameObjects = listOfGameObjects;
        this.setLayoutX(x);
        this.setLayoutY(y);
        setText("");
        setPrefHeight(50);
        setPrefWidth(50);
        initialisebutton();
    }

    private void initialisebutton(){
        setOnMouseClicked(mouseEvent ->
                handler()
        );
    }

    private void handler() {
        System.out.println("Move Button Clicked");
        for(Island island: listOfIslands){
            island.setPosition(new Position(island.getPosition().getX()-100, island.getPosition().getY()));
        }
        for (Game_Objects game_objects: listOfGameObjects){
            game_objects.setPosition(new Position(game_objects.getPosition().getX()-100, game_objects.getPosition().getY()));
        }
    }

}
