package com.example.game;

import javafx.animation.AnimationTimer;
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
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class moveScreenButton extends Button {
    private final String STYLE = "-fx-background-color:transparent; -fx-background-size: cover";
    ArrayList<Island> listOfIslands;
    ArrayList<Game_Objects> listOfGameObjects;
    Hero hero;
    int score;
    long startTime;
    private int counter;
    private boolean flag;
    moveScreenButton(double x , double y, ArrayList<Island> listOfIslands, ArrayList<Game_Objects> listOfGameObjects, Hero player, long startTime){
        this.startTime = startTime;
        this.listOfIslands = listOfIslands;
        this.listOfGameObjects = listOfGameObjects;
        this.setLayoutX(x);
        this.setLayoutY(y);
        setText("");
        setPrefHeight(600);
        setPrefWidth(800);
        initialisebutton();
        this.hero = player;
        this.score = 0;
        this.setStyle(STYLE);
        this.counter = 0;
        this.flag=false;
    }

    private void initialisebutton(){
        setOnMouseClicked(mouseEvent ->
                        handler()
        );
    }

    private void handler() {
//        System.out.println("Score " + score + " PositionX " + hero.getPosition().getX()*score*100 +
//                " PositionY " + hero.getPosition().getY());

        score = score + 1;
        hero.setScore(score);
        hero.animate();
        this.counter = 0;
        this.flag=false;
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                counter = counter + 10;
                for (Island island : listOfIslands) {
                    island.setPosition(new Position(island.getPosition().getX() - 10, island.getPosition().getY()));
                }
                for (Game_Objects game_objects : listOfGameObjects) {
                    game_objects.setPosition(new Position(game_objects.getPosition().getX() - 10, game_objects.getPosition().getY()));
                    if(check_collision(hero,game_objects)){
                        flag=true;
                    }
                }
                if (counter >= 100 || flag) {
                    stop();
                }
            }
        };
        timer.start();
            /*if(check_collision(hero,game_objects)){
                try{
                    //game_objects.collide(hero);
                }
                catch(Exception e){
                    System.out.println(game_objects.getName());
                }
            }*/
    }

    private boolean check_collision(Hero hero, Game_Objects game_objects) {
        double hero_height = hero.getHero().getFitHeight();
        double hero_width = hero.getHero().getFitWidth();
        double obj_height = game_objects.getImage().getFitHeight();
        double obj_width = game_objects.getImage().getFitWidth();
        double hero_start_X , hero_end_X , hero_start_Y, hero_end_Y;
        double obj_start_X , obj_end_X , obj_start_Y, obj_end_Y;
        hero_start_X = hero.getHero().getX();
        hero_end_X = hero_start_X + hero_width;
        hero_start_Y = hero.getHero().getY();
        hero_end_Y = hero_start_Y + hero_height;
        obj_start_X = game_objects.getImage().getX();
        obj_end_X = obj_start_X + obj_width;
        obj_start_Y = game_objects.getImage().getY();
        obj_end_Y = obj_start_Y + obj_height;
        if((hero_start_Y <= obj_end_Y) && (hero_end_Y <= obj_end_Y && hero_end_Y >=obj_start_Y)){
            if((hero_start_X <= obj_end_X && hero_start_X >=obj_start_X) || (hero_end_X <= obj_end_X && hero_end_X >=obj_start_X)){
                if(game_objects instanceof Chest){
                    if(!((Chest) game_objects).getOpen()){
                        return true;
                    }
                }
                else{
                    return true;
                }
            }
        }
        return false;
    }

}
