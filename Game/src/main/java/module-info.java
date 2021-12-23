module com.example.game {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.media;
    requires java.desktop;
    requires javafx.swing;


    opens com.example.game to javafx.fxml;
    exports com.example.game;
}