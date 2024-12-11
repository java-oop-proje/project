package org.example.demo;

import org.example.demo.models.Users;
import org.example.demo.models.Session;

public class UserSession implements Session {
    private static UserSession instance;
    private Users user;

    private UserSession() {}

    public static UserSession getInstance() {
        if(instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Users getUser() {
        return user;
    }
}
