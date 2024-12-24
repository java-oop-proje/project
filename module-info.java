module org.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires javafx.swing;
    requires org.apache.pdfbox;
    requires java.sql;
    requires dotenv.java;

    opens org.example.demo to javafx.fxml;
    opens org.example.demo.controllers to javafx.fxml;
    opens org.example.demo.models to javafx.base;
    opens org.example.demo.utils to javafx.fxml;
    
    exports org.example.demo;
    exports org.example.demo.controllers;
    exports org.example.demo.models;
    exports org.example.demo.utils;
    exports org.example.demo.database;
} 