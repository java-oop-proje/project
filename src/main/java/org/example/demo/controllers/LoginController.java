package org.example.demo.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.demo.database.Database;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField emailField;

    @FXML
    private TextField passwordField;

    public void handleLogin() throws IOException {
        Database db = Database.getInstance();
        boolean result = db.Login(emailField.getText(), passwordField.getText());
        if (result) {
            System.out.println("Giriş başarılı!");
            Stage stage = (Stage) emailField.getScene().getWindow();
            stage.setScene(SceneManager.getMainScene());
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Giriş başarısız! Bilgilerinizi kontrol ediniz.", ButtonType.OK);
            alert.showAndWait();
        }
    }

    public void goToRegister() throws IOException {
        Stage stage = (Stage) emailField.getScene().getWindow();
        stage.setScene(SceneManager.getRegisterScene());
    }
}