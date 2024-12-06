package org.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public void start(Stage stage) throws IOException {
        Scene loginScene = org.example.demo.controllers.SceneManager.getLoginScene();
        stage.setScene(loginScene);
        stage.setTitle("Uygulama");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}