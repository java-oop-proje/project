package org.example.demo.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import java.io.IOException;

import org.example.demo.UserSession;
import org.example.demo.database.Database;
import org.example.demo.models.UserDetails;
import org.example.demo.models.Users;
import org.example.demo.utils.Pdf;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class PdfListController {

    @FXML
    private Button cvOlusturButton;
    @FXML
    private Button cvGorButton;

    @FXML
    private TableView<UserDetails> userTableView;
    @FXML
    private TableColumn<UserDetails, String> phoneColumn;
    @FXML
    private TableColumn<UserDetails, String> addressColumn;
    @FXML
    private TableColumn<UserDetails, String> cityColumn;
    @FXML
    private TableColumn<UserDetails, String> stateColumn;
    @FXML
    private TableColumn<UserDetails, String> zipColumn;
    @FXML
    private TableColumn<UserDetails, String> educationColumn;
    @FXML
    private TableColumn<UserDetails, String> studyAbroadColumn;
    @FXML
    private TableColumn<UserDetails, String> highSchoolColumn;
    @FXML
    private TableColumn<UserDetails, String> experienceColumn;
    @FXML
    private TableColumn<UserDetails, String> leadershipActivitiesColumn;
    @FXML
    private TableColumn<UserDetails, String> skillsInterestsColumn;

    @FXML
    private Button logoutButton;
    public void logout() throws IOException {
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        stage.setScene(SceneManager.getLoginScene());
    }

    @FXML
    public void initialize() {
        System.out.println("PdfListController initialize");
        try {
            Database db = new Database();
            List<UserDetails> userDetails = db.GetUserDetails(UserSession.getInstance().getUser().getId());

            userTableView.getItems().addAll(userDetails);

            phoneColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhoneNumber()));
            addressColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStreetAddress()));
            cityColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCity()));
            stateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getState()));
            zipColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getZip()));
            educationColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEducation()));
            studyAbroadColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStudyAbroad()));
            highSchoolColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getHighSchool()));
            experienceColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getExperience()));
            leadershipActivitiesColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLeadershipActivities()));
            skillsInterestsColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSkillsInterests()));

            cvGorButton.setDisable(true);

            userTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                cvGorButton.setDisable(newValue == null);
            });

        } catch (Exception e) {
            System.err.println("Initialize hatası: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void cvOlustur() throws IOException {
        try {
            if (cvOlusturButton != null) {
                Path pdfPath = Paths.get("src/main/resources/pdf/web/cv.pdf");
                Files.deleteIfExists(pdfPath);
                Stage stage = (Stage) cvOlusturButton.getScene().getWindow();
                stage.setScene(SceneManager.getMainScene());
            } else {
                System.err.println("cvOlusturButton FXML'de bulunamadı!");
            }
        } catch (Exception e) {
            System.err.println("CV Oluştur hatası: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void cvGor() throws IOException {
        try {
            if (cvGorButton != null) {
                UserDetails selectedDetails = userTableView.getSelectionModel().getSelectedItem();

                if (selectedDetails != null) {
                    Users user = UserSession.getInstance().getUser();

                    if (user != null) {
                        Pdf pdf = new Pdf();
                        try {
                            pdf.generatePDF(selectedDetails, user);
                            Stage stage = (Stage) cvGorButton.getScene().getWindow();
                            SceneManager sceneManager = new SceneManager();
                            Scene scene = sceneManager.loadScene("/fxml/viewpdf.fxml");
                            stage.setScene(scene);
                        } catch (IOException e) {
                            System.err.println("PDF oluşturma hatası: " + e.getMessage());
                            e.printStackTrace();
                        }
                    } else {
                        System.err.println("Kullanıcı oturumu mevcut değil!");
                    }
                } else {
                    System.err.println("Seçilen detay bulunamadı!");
                }
            } else {
                System.err.println("cvGorButton FXML'de bulunamadı!");
            }
        } catch (Exception e) {
            System.err.println("CV Gör hatası: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
