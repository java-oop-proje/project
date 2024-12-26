package org.example.demo.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.example.demo.UserSession;
import org.example.demo.database.Database;
import org.example.demo.models.UserDetails;
import org.example.demo.utils.Pdf;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class MainController {

    @FXML
    private TextField streetAddressField;
    @FXML
    private TextField cityField;
    @FXML
    private TextField stateField;
    @FXML
    private TextField zipField;
    @FXML
    private TextField phoneNumberField;
    @FXML
    private TextArea educationArea;
    @FXML
    private TextArea studyAbroadArea;
    @FXML
    private TextArea highSchoolArea;
    @FXML
    private TextArea experienceArea;
    @FXML
    private TextArea leadershipActivitiesArea;
    @FXML
    private TextArea skillsInterestsArea;
    @FXML
    private ImageView imageView;
    @FXML
    private Button backButton;
    @FXML
    private Button downloadButton;
    private boolean isPdfGenerated = false;

    public void initialize() {
        if (UserSession.getInstance().getUser().getId() == -1) {
            try {
                logout();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (!isPdfGenerated){
            backButton.setDisable(true);
        }
    }
    @FXML
    private Button logoutButton;
    public void logout() throws IOException {
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        stage.setScene(SceneManager.getLoginScene());
    }

    @FXML
    private void handleSubmitButtonAction() throws IOException {
        isPdfGenerated=false;
        backButton.setDisable(true);
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

        if (UserSession.getInstance().getUser().getId() == -1) {
            logout();
            return;
        }

        if (streetAddress.isEmpty() || city.isEmpty() || state.isEmpty() || zip.isEmpty() || phoneNumber.isEmpty() || education.isEmpty() || studyAbroad.isEmpty() || highSchool.isEmpty() || experience.isEmpty() || leadershipActivities.isEmpty() || skillsInterests.isEmpty()) {
            return;
        }

        UserDetails userDetails = new UserDetails(0, UserSession.getInstance().getUser().getId(), phoneNumber, streetAddress, city, state, zip, education, studyAbroad, highSchool, experience, leadershipActivities, skillsInterests);
        Database db = Database.getInstance();
        db.InsertUserDetails(userDetails);

        Pdf pdf = new Pdf();

        try {
            pdf.generatePDF(userDetails, UserSession.getInstance().getUser());
        } catch (IOException e) {
            e.printStackTrace();
        }

        loadPdfToImageView("./cv.pdf");
    }

    @FXML
    public void backButtonHandler() {
        try {
            Stage stage = (Stage) backButton.getScene().getWindow();
            SceneManager sm = new SceneManager();
            stage.setScene(sm.getfarukdosyaScene());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void downloadHandler() {
        if (!isPdfGenerated) {
            return;
        }
        Pdf pdf = new Pdf();
        pdf.downloadPDF();
    }


    private void loadPdfToImageView(String pdfPath) {
        try {
            PDDocument document = PDDocument.load(new File(pdfPath));
            PDFRenderer pdfRenderer = new PDFRenderer(document);

            BufferedImage bufferedImage = pdfRenderer.renderImageWithDPI(0, 300);

            Image image = bufferedImageToImage(bufferedImage);

            imageView.setImage(image);

            document.close();
            isPdfGenerated = true;
            backButton.setDisable(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Image bufferedImageToImage(BufferedImage bufferedImage) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", byteArrayOutputStream);
            byte[] imageData = byteArrayOutputStream.toByteArray();

            return new Image(new java.io.ByteArrayInputStream(imageData));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}