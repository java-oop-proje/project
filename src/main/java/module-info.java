module org.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires io.github.cdimascio.dotenv.java;
    requires java.sql;
    requires itextpdf;

    opens org.example.demo.controllers to javafx.fxml;
    exports org.example.demo;
}