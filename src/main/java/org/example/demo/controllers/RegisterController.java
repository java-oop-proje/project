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

public class RegisterController {

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField passwordField;

    public void handleRegister() throws IOException {
        Database db = Database.getInstance();
        Users user = new Users(firstNameField.getText(), lastNameField.getText(), emailField.getText(), passwordField.getText());
        int result = db.CreateUser(user);
        if (result != -1) {
            UserSession.getInstance().setUserId(result);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Kayıt başarılı! Giriş ekranına yönlendiriliyorsunuz.", ButtonType.OK);
            alert.showAndWait();
            Stage stage = (Stage) emailField.getScene().getWindow();
            stage.setScene(SceneManager.getLoginScene());
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Kayıt başarısız! Bilgilerinizi kontrol ediniz.", ButtonType.OK);
            alert.showAndWait();
        }
    }

    public void goToLogin() throws IOException {
        Stage stage = (Stage) emailField.getScene().getWindow();
        stage.setScene(SceneManager.getLoginScene());
    }
}