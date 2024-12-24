package org.example.demo.utils;

import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.example.demo.models.UserDetails;
import org.example.demo.models.Users;

import java.io.IOException;

public class Pdf {
    public void generatePDF(UserDetails userDetails, Users user) throws IOException {
        java.nio.file.Path pdfPath = java.nio.file.Paths.get("src/main/resources/pdf/cv.pdf");
        java.nio.file.Files.deleteIfExists(pdfPath);
        
        PDDocument document = new PDDocument();
        PDPage page = new PDPage(PDRectangle.A4);
        document.addPage(page);

        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 20);
        contentStream.beginText();
        contentStream.setLeading(22.5f);
        contentStream.newLineAtOffset(50, 750);

        // Name (Assuming UserDetails contains a name field)
        contentStream.showText(user.getFirstName()+ " " + user.getLastName());
        contentStream.newLine();

        contentStream.setFont(PDType1Font.HELVETICA, 12);
        contentStream.showText(userDetails.getStreetAddress() + ", " + userDetails.getCity() + ", " + userDetails.getState() + " " + userDetails.getZip());
        contentStream.newLine();
        contentStream.showText("Phone: " + userDetails.getPhoneNumber());
        contentStream.newLine();
        contentStream.showText("Email: " + user.getEmail()); // Assuming UserDetails contains an email field
        contentStream.newLine();
        contentStream.newLine();

        // Education Section
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 14);
        contentStream.showText("Education");
        contentStream.newLine();
        contentStream.setFont(PDType1Font.HELVETICA, 12);
        contentStream.showText(userDetails.getEducation());
        contentStream.newLine();
        contentStream.newLine();

        // Study Abroad Section
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 14);
        contentStream.showText("Study Abroad");
        contentStream.newLine();
        contentStream.setFont(PDType1Font.HELVETICA, 12);
        contentStream.showText(userDetails.getStudyAbroad());
        contentStream.newLine();
        contentStream.newLine();

        // High School Section
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 14);
        contentStream.showText("High School");
        contentStream.newLine();
        contentStream.setFont(PDType1Font.HELVETICA, 12);
        contentStream.showText(userDetails.getHighSchool());
        contentStream.newLine();
        contentStream.newLine();

        // Experience Section
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 14);
        contentStream.showText("Experience");
        contentStream.newLine();
        contentStream.setFont(PDType1Font.HELVETICA, 12);
        contentStream.showText(userDetails.getExperience());
        contentStream.newLine();
        contentStream.newLine();

        // Leadership & Activities Section
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 14);
        contentStream.showText("Leadership & Activities");
        contentStream.newLine();
        contentStream.setFont(PDType1Font.HELVETICA, 12);
        contentStream.showText(userDetails.getLeadershipActivities());
        contentStream.newLine();
        contentStream.newLine();

        // Skills & Interests Section
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 14);
        contentStream.showText("Skills & Interests");
        contentStream.newLine();
        contentStream.setFont(PDType1Font.HELVETICA, 12);
        contentStream.showText(userDetails.getSkillsInterests());
        contentStream.newLine();

        contentStream.endText();
        contentStream.close();

        document.save("src/main/resources/pdf/web/cv.pdf");
        document.close();
    }
}
