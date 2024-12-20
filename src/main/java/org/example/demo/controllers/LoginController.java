package org.example.demo.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.demo.UserSession;
import org.example.demo.database.Database;
import org.example.demo.models.Users;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField emailField;

    @FXML
    private TextField passwordField;

    public void initialize() {
        if (UserSession.getInstance().getUser() != null) {
            try {
                Stage stage = (Stage) emailField.getScene().getWindow();
                stage.setScene(SceneManager.getMainScene());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void handleLogin() throws IOException {
        Database db = Database.getInstance();
        Users result = db.Login(emailField.getText(), passwordField.getText());
        if (result != null) {
            UserSession.getInstance().setUser(result);
            System.out.println("Giriş başarılı!");
            Stage stage = (Stage) emailField.getScene().getWindow();
            stage.setScene(SceneManager.getfarukdosyaScene());
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