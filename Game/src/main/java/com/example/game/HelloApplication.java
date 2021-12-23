package com.example.game;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        try{
            View_Manager manager = new View_Manager(null);
            stage = manager.getMainStage();
            stage.show();
            try {
                FileWriter myWriter = new FileWriter("filename.txt");
                myWriter.write("Files in Java might be tricky, but it is fun enough!");
                myWriter.close();
                System.out.println("Successfully wrote to the file.");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}