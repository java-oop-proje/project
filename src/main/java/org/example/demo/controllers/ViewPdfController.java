package org.example.demo.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.example.demo.utils.Pdf;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.ByteArrayOutputStream;

public class ViewPdfController {

    @FXML
    private ImageView imageView;
    @FXML
    private Button logoutButton;

    public void initialize() {
        loadPdfToImageView("./cv.pdf");
    }

    private void loadPdfToImageView(String pdfPath) {
        try {
            PDDocument document = PDDocument.load(new File(pdfPath));
            PDFRenderer pdfRenderer = new PDFRenderer(document);

            BufferedImage bufferedImage = pdfRenderer.renderImageWithDPI(0, 300);

            Image image = bufferedImageToImage(bufferedImage);

            imageView.setImage(image);

            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void downloadHandler() {
        Pdf pdf = new Pdf();
        pdf.downloadPDF();


        Alert alert = new Alert(Alert.AlertType.ERROR, "Pdf indirildi", ButtonType.OK);
        alert.showAndWait();
    }

    public void logout() throws IOException {
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        stage.setScene(SceneManager.getLoginScene());
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

    @FXML
    private void handleBackButton() {
        try {
            SceneManager sm = new SceneManager();
            Scene scene = sm.getfarukdosyaScene();
            Stage stage = (Stage) imageView.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}