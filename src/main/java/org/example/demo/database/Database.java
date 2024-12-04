package org.example.demo.database;


import io.github.cdimascio.dotenv.Dotenv;
import org.example.demo.models.Users;
import org.example.demo.utils.PasswordHasher;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {
    private static Database instance;
    private Connection connection;
    private String dbname;
    private String dbusername;
    private String dbpassword;
    private String dbhost;
    private String dbport;

    // Private constructor to prevent instantiation
    private Database() {
        try {
            Dotenv dotenv = Dotenv.load();
            dbname = dotenv.get("DB_NAME");
            dbusername = dotenv.get("DB_USERNAME");
            dbpassword = dotenv.get("DB_PASSWORD");
            dbhost = dotenv.get("DB_HOST");
            dbport = dotenv.get("DB_PORT");

            connection = DriverManager.getConnection("jdbc:postgresql://" + dbhost + ":" + dbport + "/" + dbname, dbusername, dbpassword);
            System.out.println("Database connection successful!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public boolean CreateUser(Users user) {
        PasswordHasher hasher = new PasswordHasher();
        user.setPassword(hasher.hashPassword(user.getPassword()));
        try {
            boolean result = connection.createStatement().execute("INSERT INTO users (first_name, last_name, email, password) VALUES ('" + user.getFirstName() + "', '" + user.getLastName() + "', '" + user.getEmail() + "', '" + user.getPassword() + "')");
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean Login(String email, String password) {
        PasswordHasher hasher = new PasswordHasher();
        try {
            ResultSet result = connection.createStatement().executeQuery("SELECT 1 FROM users WHERE email = '" + email + "' AND password = '" + hasher.hashPassword(password) + "'");
            return result.first();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}