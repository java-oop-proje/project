module org.example.demo {
    requires javafx.fxml;
    requires io.github.cdimascio.dotenv.java;
    requires java.sql;
    requires org.apache.pdfbox;
    requires javafx.web;
    requires javafx.graphics;

    opens org.example.demo.controllers to javafx.fxml;
    exports org.example.demo;
}