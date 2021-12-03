package com.example.game;


import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController{
    @FXML
    private Label welcomeText;

    @FXML
    protected void Load_states(){welcomeText.setText("Load");};
    @FXML
    protected void start_game(){welcomeText.setText("Start Game");}
    @FXML
    protected void Exit(){welcomeText.setText("Exit");}
    @FXML
    protected void Screen_Click(){
        /*
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Group root = new Group();
        Scene theScene = new Scene( root );
        stage.setScene( theScene );
        root.getChildren().add(fxmlLoader.load());
        stage.show();
        */
        welcomeText.setText("Screen Clicked");
    }
}