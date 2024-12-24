package org.example.demo.controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class ViewPdfController {
    @FXML
    private WebView webView;

    @FXML
    public void initialize() {
        String webPagePath = getClass().getResource("/pdf/web/viewer.html").toExternalForm();
        webView.getEngine().load(webPagePath);
    }

    @FXML
    private void handleBackButton() {
        try {
            Scene scene = SceneManager.getfarukdosyaScene();
            Stage stage = (Stage) webView.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
