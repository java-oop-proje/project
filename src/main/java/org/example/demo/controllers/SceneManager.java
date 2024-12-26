package org.example.demo.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class SceneManager {

    private static Scene loginScene;
    private static Scene registerScene;
    private static Scene mainScene;
    private static Scene farukdosyaScene;
    private Scene viewPdfScene;

    public static Scene getLoginScene() throws IOException {
        if (loginScene == null) {
            loginScene = loadScene("/fxml/login.fxml");
        }
        return loginScene;
    }

    public static Scene getRegisterScene() throws IOException {
        if (registerScene == null) {
            registerScene = loadScene("/fxml/register.fxml");
        }
        return registerScene;
    }

    public static Scene getMainScene() throws IOException {
        if (mainScene == null) {
            mainScene = loadScene("/fxml/main.fxml");
        }
        return mainScene;
    }

    public static Scene getfarukdosyaScene() throws IOException {
        if (farukdosyaScene == null) {
            farukdosyaScene = loadScene("/fxml/farukdosya.fxml");
        }
        return farukdosyaScene;
    }

    public Scene getViewPdfScene() throws IOException {

        viewPdfScene = loadScene("/fxml/viewPdf.fxml");

        return viewPdfScene;
    }
    public static Scene loadScene(String fxmlPath) throws IOException {
        FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource(fxmlPath));
        Parent root = loader.load();
        return new Scene(root);
    }
    
}