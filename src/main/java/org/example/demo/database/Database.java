package org.example.demo.database;


import io.github.cdimascio.dotenv.Dotenv;
import org.example.demo.models.UserDetails;
import org.example.demo.models.Users;
import org.example.demo.utils.PasswordHasher;

import java.sql.*;

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

    public Users CreateUser(Users user) {
        PasswordHasher hasher = new PasswordHasher();
        user.setPassword(hasher.hashPassword(user.getPassword()));
        String sql = "INSERT INTO users (firstname, lastname, email, password) VALUES ( ?, ?, ?, ?) RETURNING id,firstname,lastname,email";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, user.getFirstName());
            pstmt.setString(2, user.getLastName());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getPassword());

            ResultSet rs = pstmt.executeQuery();
            rs.next();

            Users newUser = new Users(rs.getInt("user_id"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("email"), null);

            return newUser ;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Users Login(String email, String password) {
        PasswordHasher hasher = new PasswordHasher();
        try {
            ResultSet result = connection.createStatement().executeQuery("SELECT * FROM users WHERE email = '" + email + "'");
            while (result.next()) {
                String dbPassword = result.getString("password");
                if (hasher.verifyPassword(password, dbPassword)) {
                    return new Users(result.getInt("user_id"), result.getString("firstname"), result.getString("lastname"), result.getString("email"), null);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean InsertUserDetails(UserDetails userDetails) {
        try {
            String sql = "INSERT INTO user_details (user_id, phone_number, street_address, city, state, zip, education, study_abroad, high_school, experience, leadership_activities, skills_interests) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userDetails.getUserId());
            statement.setString(2, userDetails.getPhoneNumber());
            statement.setString(3, userDetails.getStreetAddress());
            statement.setString(4, userDetails.getCity());
            statement.setString(5, userDetails.getState());
            statement.setString(6, userDetails.getZip());
            statement.setString(7, userDetails.getEducation());
            statement.setString(8, userDetails.getStudyAbroad());
            statement.setString(9, userDetails.getHighSchool());
            statement.setString(10, userDetails.getExperience());
            statement.setString(11, userDetails.getLeadershipActivities());
            statement.setString(12, userDetails.getSkillsInterests());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}