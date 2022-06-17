package com.example.mcs2.Model;

public class Users {

    Integer UserID;
    String email,username,password;

    public Users(Integer userID, String email, String username, String password) {
        UserID = userID;
        this.email = email;
        this.username = username;
        this.password = password;
    }


    public Integer getUserID() {
        return UserID;
    }

    public void setUserID(Integer userID) {
        UserID = userID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
