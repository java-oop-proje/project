package org.example.demo;

public class UserSession {
    private static UserSession instance;
    private Integer userId;

    private UserSession() {}

    public static UserSession getInstance() {
        if(instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }
}