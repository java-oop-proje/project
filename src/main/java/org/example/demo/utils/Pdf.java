package org.example.demo.utils;

import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.example.demo.models.UserDetails;
import org.example.demo.models.Users;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Pdf {
    private static final String PDF_PATH = "./cv.pdf";
    private static final String FONT_PATH = "/fonts/arial.ttf";

    public void generatePDF(UserDetails userDetails, Users user) throws IOException {
        Path pdfPath = Paths.get(PDF_PATH);
        Files.deleteIfExists(pdfPath);

        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            PDType0Font font = PDType0Font.load(document, getClass().getResourceAsStream(FONT_PATH));

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.setFont(font, 20);
                contentStream.beginText();
                contentStream.setLeading(22.5f);
                contentStream.newLineAtOffset(50, 750);

                contentStream.showText(user.getFirstName() + " " + user.getLastName());
                contentStream.newLine();

                contentStream.setFont(font, 12);
                contentStream.showText(userDetails.getStreetAddress() + ", " +
                        userDetails.getCity() + ", " +
                        userDetails.getState() + " " +
                        userDetails.getZip());
                contentStream.newLine();
                contentStream.showText("Phone: " + userDetails.getPhoneNumber());
                contentStream.newLine();
                contentStream.showText("Email: " + user.getEmail());
                contentStream.newLine();
                contentStream.newLine();

                contentStream.setFont(font, 14);
                contentStream.showText("Education");
                contentStream.newLine();
                contentStream.setFont(font, 12);
                contentStream.showText(userDetails.getEducation());
                contentStream.newLine();
                contentStream.newLine();

                contentStream.setFont(font, 14);
                contentStream.showText("Study Abroad");
                contentStream.newLine();
                contentStream.setFont(font, 12);
                contentStream.showText(userDetails.getStudyAbroad());
                contentStream.newLine();
                contentStream.newLine();

                contentStream.setFont(font, 14);
                contentStream.showText("High School");
                contentStream.newLine();
                contentStream.setFont(font, 12);
                contentStream.showText(userDetails.getHighSchool());
                contentStream.newLine();
                contentStream.newLine();

                contentStream.setFont(font, 14);
                contentStream.showText("Experience");
                contentStream.newLine();
                contentStream.setFont(font, 12);
                contentStream.showText(userDetails.getExperience());
                contentStream.newLine();
                contentStream.newLine();

                contentStream.setFont(font, 14);
                contentStream.showText("Leadership & Activities");
                contentStream.newLine();
                contentStream.setFont(font, 12);
                contentStream.showText(userDetails.getLeadershipActivities());
                contentStream.newLine();
                contentStream.newLine();

                contentStream.setFont(font, 14);
                contentStream.showText("Skills & Interests");
                contentStream.newLine();
                contentStream.setFont(font, 12);
                contentStream.showText(userDetails.getSkillsInterests());
                contentStream.newLine();

                contentStream.endText();
            }

            document.save(PDF_PATH);
        }
    }
}