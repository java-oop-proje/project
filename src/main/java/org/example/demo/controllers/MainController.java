package org.example.demo.controllers;

import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    @FXML
    private javafx.scene.control.Button logoutButton;

    public void logout() throws IOException {
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        stage.setScene(SceneManager.getLoginScene());
    }
}