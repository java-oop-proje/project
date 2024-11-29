package org.example.demo;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.example.demo.database.Database;
import org.example.demo.models.Users;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        VBox loginRoot = new VBox(10);
        loginRoot.setPadding(new Insets(10));

        TextField email = new TextField();
        email.setPromptText("Email adresinizi giriniz");

        TextField password = new TextField();
        password.setPromptText("Şifrenizi giriniz");

        Button loginButton = new Button("Giriş Yap");
        loginButton.setOnAction(e -> {
            Database db = Database.getInstance();
            boolean result = db.Login(email.getText(), password.getText());
            if (result) {
                System.out.println("Giriş başarılı!");
                stage.setScene(createMainScene(stage)); // Ana sayfaya geçiş
            } else {
                System.out.println("Giriş başarısız!");
                Alert alert = new Alert(Alert.AlertType.ERROR, "Giriş başarısız! Bilgilerinizi kontrol ediniz.", ButtonType.OK);
                alert.showAndWait();
            }
        });

        Button registerButton = new Button("Kayıt ol");
        registerButton.setOnAction(e -> stage.setScene(createRegisterScene(stage))); // Kayıt sayfasına geçiş

        loginRoot.getChildren().addAll(email, password, loginButton, registerButton);

        Scene loginScene = new Scene(loginRoot, 300, 400);
        stage.setScene(loginScene);
        stage.setTitle("Giriş Ekranı");
        stage.show();
    }

    private Scene createMainScene(Stage stage) {
        VBox mainRoot = new VBox(10);
        mainRoot.setPadding(new Insets(10));

        Label welcomeLabel = new Label("Hoş geldiniz!");
        Button logoutButton = new Button("Çıkış Yap");
        logoutButton.setOnAction(e -> stage.setScene(createLoginScene(stage))); // Giriş ekranına geri dön

        mainRoot.getChildren().addAll(welcomeLabel, logoutButton);

        return new Scene(mainRoot, 300, 400);
    }

    private Scene createRegisterScene(Stage stage) {
        VBox registerRoot = new VBox(10);
        registerRoot.setPadding(new Insets(10));

        TextField firstName = new TextField();
        firstName.setPromptText("Yeni email adresinizi giriniz");

        TextField lastName = new TextField();
        lastName.setPromptText("Yeni email adresinizi giriniz");

        TextField newEmail = new TextField();
        newEmail.setPromptText("Yeni email adresinizi giriniz");

        TextField newPassword = new TextField();
        newPassword.setPromptText("Yeni şifrenizi giriniz");

        Button registerButton = new Button("Kayıt Ol");
        registerButton.setOnAction(e -> {
            Database db = Database.getInstance();
            Users user = new Users(firstName.getText(), lastName.getText(), newEmail.getText(), newPassword.getText());
            boolean result = db.CreateUser(user);
            if (!result) {
                System.out.println("Kayıt başarılı!");
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Kayıt başarılı! Giriş ekranına yönlendiriliyorsunuz.", ButtonType.OK);
                alert.showAndWait();
                stage.setScene(createLoginScene(stage)); // Giriş ekranına geri dön
            } else {
                System.out.println("Kayıt başarısız!");
                Alert alert = new Alert(Alert.AlertType.ERROR, "Kayıt başarısız! Bilgilerinizi kontrol ediniz.", ButtonType.OK);
                alert.showAndWait();
            }
        });

        Button backToLoginButton = new Button("Girişe Dön");
        backToLoginButton.setOnAction(e -> stage.setScene(createLoginScene(stage))); // Giriş ekranına geri dön

        registerRoot.getChildren().addAll(firstName,lastName,newEmail, newPassword, registerButton, backToLoginButton);

        return new Scene(registerRoot, 300, 400);
    }

    private Scene createLoginScene(Stage stage) {
        VBox loginRoot = new VBox(10);
        loginRoot.setPadding(new Insets(10));

        TextField email = new TextField();
        email.setPromptText("Email adresinizi giriniz");

        TextField password = new TextField();
        password.setPromptText("Şifrenizi giriniz");

        Button loginButton = new Button("Giriş Yap");
        loginButton.setOnAction(e -> {
            Database db = Database.getInstance();
            boolean result = db.Login(email.getText(), password.getText());
            if (result) {
                System.out.println("Giriş başarılı!");
                stage.setScene(createMainScene(stage)); // Ana sayfaya geçiş
            } else {
                System.out.println("Giriş başarısız!");
                Alert alert = new Alert(Alert.AlertType.ERROR, "Giriş başarısız! Bilgilerinizi kontrol ediniz.", ButtonType.OK);
                alert.showAndWait();
            }
        });

        Button registerButton = new Button("Kayıt ol");
        registerButton.setOnAction(e -> stage.setScene(createRegisterScene(stage))); // Kayıt sayfasına geçiş

        loginRoot.getChildren().addAll(email, password, loginButton, registerButton);

        return new Scene(loginRoot, 300, 400);
    }

    public static void main(String[] args) {
        launch();
    }
}