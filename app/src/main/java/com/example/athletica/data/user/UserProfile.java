package com.example.athletica.data.user;

import java.util.ArrayList;

public class UserProfile {

    private String id;
    private String name;
    private String gender;
    private String birthdate;
    private String description;
    private ArrayList<String> follows;
    private ArrayList<String> sportPreferences;

    public UserProfile() {

    }

    public UserProfile(String name, String gender, String description, String birthdate, ArrayList<String> sportPreferences, ArrayList<String> follows) {
        this.name = name;
        this.gender = gender;
        this.description = description;
        this.birthdate = birthdate;
        this.sportPreferences = sportPreferences;
        this.follows = follows;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<String> getSportPreferences() {
        return sportPreferences;
    }

    public ArrayList<String> getFollows() {
        return follows;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setSportPreferences(ArrayList<String> sportPreferences) {
        this.sportPreferences = sportPreferences;
    }

    public void setFollows(ArrayList<String> follows) {
        this.follows = follows;
    }
}