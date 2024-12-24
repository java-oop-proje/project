package org.example.demo.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import java.io.IOException;

import org.example.demo.UserSession;
import org.example.demo.database.Database;
import org.example.demo.models.UserDetails;
import org.example.demo.models.Users;
import org.example.demo.utils.Pdf;

import java.util.List;

public class FarukDosyaController {
    @FXML
    private ListView<String> listView;
    @FXML
    private Button cvOlusturButton;
    @FXML
    private Button cvGorButton;

    @FXML
    public void initialize() {
        try {
            Database db = new Database();
            List<UserDetails> userDetails = db.GetUserDetails(UserSession.getInstance().getUser().getId());
            for (UserDetails detail : userDetails) {
                String userInfo = String.format(
                    "Telefon: %s\nAdres: %s\nŞehir: %s\nEyalet: %s\nPosta Kodu: %s\n" +
                    "Eğitim: %s\nYurtdışı Eğitim: %s\nLise: %s\n" + 
                    "Deneyim: %s\nLiderlik Aktiviteleri: %s\nYetenekler ve İlgi Alanları: %s",
                    detail.getPhoneNumber(),
                    detail.getStreetAddress(), 
                    detail.getCity(),
                    detail.getState(),
                    detail.getZip(),
                    detail.getEducation(),
                    detail.getStudyAbroad(),
                    detail.getHighSchool(),
                    detail.getExperience(), 
                    detail.getLeadershipActivities(),
                    detail.getSkillsInterests()
                );
                listView.getItems().add(userInfo);
            }
            
            // ListView seçim değişikliğini dinle
            listView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
                cvGorButton.setDisable(newVal == null);
            });
            
            // Başlangıçta butonu devre dışı bırak
            cvGorButton.setDisable(true);
        } catch (Exception e) {
            System.err.println("Initialize hatası: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void cvOlustur() throws IOException {
        try {
            if (cvOlusturButton != null) {
                java.nio.file.Path pdfPath = java.nio.file.Paths.get("src/main/resources/pdf/cv.pdf");
                java.nio.file.Files.deleteIfExists(pdfPath);
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
                String selectedUserInfo = listView.getSelectionModel().getSelectedItem();
                if (selectedUserInfo != null) {
                    Database db = Database.getInstance();
                    int userId = UserSession.getInstance().getUser().getId();
                    Users user = UserSession.getInstance().getUser();
                    
                    // ListView'den seçilen öğeyi kullanarak UserDetails'i bul
                    List<UserDetails> allDetails = db.GetUserDetails(userId);
                    UserDetails selectedDetails = null;
                    
                    for (UserDetails detail : allDetails) {
                        String formattedDetail = String.format(
                            "Telefon: %s\nAdres: %s\nŞehir: %s\nEyalet: %s\nPosta Kodu: %s\n" +
                            "Eğitim: %s\nYurtdışı Eğitim: %s\nLise: %s\n" + 
                            "Deneyim: %s\nLiderlik Aktiviteleri: %s\nYetenekler ve İlgi Alanları: %s",
                            detail.getPhoneNumber(),
                            detail.getStreetAddress(), 
                            detail.getCity(),
                            detail.getState(),
                            detail.getZip(),
                            detail.getEducation(),
                            detail.getStudyAbroad(),
                            detail.getHighSchool(),
                            detail.getExperience(), 
                            detail.getLeadershipActivities(),
                            detail.getSkillsInterests()
                        );
                        
                        if (formattedDetail.equals(selectedUserInfo)) {
                            selectedDetails = detail;
                            break;
                        }
                    }
                    
                    if (user != null && selectedDetails != null) {
                        Pdf pdf = new Pdf();
                        try {
                            pdf.generatePDF(selectedDetails, user);
                            Stage stage = (Stage) cvGorButton.getScene().getWindow();
                            stage.setScene(SceneManager.getViewPdfScene());
                        } catch (IOException e) {
                            System.err.println("PDF oluşturma hatası: " + e.getMessage());
                            e.printStackTrace();
                        }
                    }
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
