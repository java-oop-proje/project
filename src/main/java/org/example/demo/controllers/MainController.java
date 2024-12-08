package org.example.demo.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.demo.UserSession;
import org.example.demo.database.Database;
import org.example.demo.models.UserDetails;

import java.io.IOException;

public class MainController {
    @FXML private javafx.scene.control.Button logoutButton;
    @FXML private TextField streetAddressField;
    @FXML private TextField cityField;
    @FXML private TextField stateField;
    @FXML private TextField zipField;
    @FXML private TextField phoneNumberField;
    @FXML private TextArea educationArea;
    @FXML private TextArea studyAbroadArea;
    @FXML private TextArea highSchoolArea;
    @FXML private TextArea experienceArea;
    @FXML private TextArea leadershipActivitiesArea;
    @FXML private TextArea skillsInterestsArea;

    public void logout() throws IOException {
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        stage.setScene(SceneManager.getLoginScene());
    }

    @FXML
    private void handleSubmitButtonAction() throws IOException {
        String streetAddress = streetAddressField.getText();
        String city = cityField.getText();
        String state = stateField.getText();
        String zip = zipField.getText();
        String phoneNumber = phoneNumberField.getText();
        String education = educationArea.getText();
        String studyAbroad = studyAbroadArea.getText();
        String highSchool = highSchoolArea.getText();
        String experience = experienceArea.getText();
        String leadershipActivities = leadershipActivitiesArea.getText();
        String skillsInterests = skillsInterestsArea.getText();

        if (UserSession.getInstance().getUserId() == -1) {
            logout();
            return;
        }

        if (streetAddress.isEmpty() || city.isEmpty() || state.isEmpty() || zip.isEmpty() || phoneNumber.isEmpty() || education.isEmpty() || studyAbroad.isEmpty() || highSchool.isEmpty() || experience.isEmpty() || leadershipActivities.isEmpty() || skillsInterests.isEmpty()) {
            return;
        }

        UserDetails userDetails = new UserDetails(0, UserSession.getInstance().getUserId(), phoneNumber, streetAddress, city, state, zip, education, studyAbroad, highSchool, experience, leadershipActivities, skillsInterests);
        Database db = Database.getInstance();
        db.InsertUserDetails(userDetails);
    }
}