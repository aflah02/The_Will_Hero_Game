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
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class moveScreenButton extends Button {
    ArrayList<Island> listOfIslands;
    ArrayList<Game_Objects> listOfGameObjects;
    Hero hero;
    int score;
    long startTime;
    moveScreenButton(double x , double y, ArrayList<Island> listOfIslands, ArrayList<Game_Objects> listOfGameObjects, Hero player, long startTime){
        this.startTime = startTime;
        this.listOfIslands = listOfIslands;
        this.listOfGameObjects = listOfGameObjects;
        this.setLayoutX(x);
        this.setLayoutY(y);
        setText("");
        setPrefHeight(50);
        setPrefWidth(50);
        initialisebutton();
        this.hero = player;
        this.score = 0;
    }

    private void initialisebutton(){
        setOnMouseClicked(mouseEvent ->
                        handler()
        );
    }
    void appendToFile(String filePath, String content) {
        try (FileWriter fw = new FileWriter(filePath, true)) {
            fw.write(content + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Error in Logging Player High Score");
        }
    }
    private void handler() {
//        System.out.println("Score " + score + " PositionX " + hero.getPosition().getX()*score*100 +
//                " PositionY " + hero.getPosition().getY());
        appendToFile("heroLocations.txt", "Time " + (java.time.Instant.now().getEpochSecond()-this.startTime) + " Score " + score + " PositionX " + (int)(hero.getPosition().getX()+score*100) +
                " PositionY " + (int)hero.getPosition().getY());
        score = score + 1;
        hero.setScore(score);
        hero.animate();
        for(Island island: listOfIslands){
            island.setPosition(new Position(island.getPosition().getX()-100, island.getPosition().getY()));
        }
        for (Game_Objects game_objects: listOfGameObjects){
            game_objects.setPosition(new Position(game_objects.getPosition().getX() - 100,game_objects.getPosition().getY()));
            /*if(check_collision(hero,game_objects)){
                try{
                    //game_objects.collide(hero);
                }
                catch(Exception e){
                    System.out.println(game_objects.getName());
                }
            }*/
        }

    }

    /*
    private boolean check_collision(Hero hero, Game_Objects game_objects) {
        double hero_height = hero.getHero().getFitHeight();
        double hero_width = hero.getHero().getFitWidth();
        double obj_height = game_objects.getImage().getFitHeight();
        double obj_width = game_objects.getImage().getFitWidth();
        if(hero.getHero().getY() + hero_height/2 <= game_objects.getImage().getY()+obj_height/2 && hero.getHero().getY() + hero_height/2 >= game_objects.getImage().getY() - obj_height/2){
            if(hero.getHero().getX()+hero_width/2 >= game_objects.getImage().getX()-obj_width/2 && hero.getHero().getX()+hero_width/2<=game_objects.getImage().getX()+obj_width/2) {
                return true;
            }
            else if(hero.getHero().getX()-hero_width/2 >= game_objects.getImage().getX()-obj_width/2 && hero.getHero().getX()-hero_width/2<=game_objects.getImage().getX()+obj_width/2) {
                return true;
            }
        }
        else if(hero.getHero().getY() - hero_height/2 <= game_objects.getImage().getY()+obj_height/2 && hero.getHero().getY() - hero_height/2 >= game_objects.getImage().getY() - obj_height/2 ){
            if(hero.getHero().getX()+hero_width/2 >= game_objects.getImage().getX()-obj_width/2 && hero.getHero().getX()+hero_width/2<=game_objects.getImage().getX()+obj_width/2) {
                return true;
            }
            else if(hero.getHero().getX()-hero_width/2 >= game_objects.getImage().getX()-obj_width/2 && hero.getHero().getX()-hero_width/2<=game_objects.getImage().getX()+obj_width/2) {
                return true;
            }
        }
        return false;
    }
    */

}
