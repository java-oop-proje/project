package org.example.demo.models;

public class UserDetails {
    private int detailId;
    private int userId;
    private String phoneNumber;
    private String streetAddress;
    private String city;
    private String state;
    private String zip;
    private String education;
    private String studyAbroad;
    private String highSchool;
    private String experience;
    private String leadershipActivities;
    private String skillsInterests;

    public UserDetails() {}

    public UserDetails(int detailId, int userId, String phoneNumber, String streetAddress, String city, String state, String zip, String education, String studyAbroad, String highSchool, String experience, String leadershipActivities, String skillsInterests) {
        this.detailId = detailId;
        this.userId = userId;
        this.phoneNumber = phoneNumber;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.education = education;
        this.studyAbroad = studyAbroad;
        this.highSchool = highSchool;
        this.experience = experience;
        this.leadershipActivities = leadershipActivities;
        this.skillsInterests = skillsInterests;
    }

    public int getDetailId() {
        return detailId;
    }

    public void setDetailId(int detailId) {
        this.detailId = detailId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getStudyAbroad() {
        return studyAbroad;
    }

    public void setStudyAbroad(String studyAbroad) {
        this.studyAbroad = studyAbroad;
    }

    public String getHighSchool() {
        return highSchool;
    }

    public void setHighSchool(String highSchool) {
        this.highSchool = highSchool;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getLeadershipActivities() {
        return leadershipActivities;
    }

    public void setLeadershipActivities(String leadershipActivities) {
        this.leadershipActivities = leadershipActivities;
    }

    public String getSkillsInterests() {
        return skillsInterests;
    }

    public void setSkillsInterests(String skillsInterests) {
        this.skillsInterests = skillsInterests;
    }
}