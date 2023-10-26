package com.example.musicapplication.Models;

public class User {
    private String userName;
    private String password;
    private Role role;
    private String emailAddress;
    private int phoneNumber;
    public User(String userName, String password, Role role, String emailAddress, int phoneNumber)
    {
        this.userName=userName;
        this.password = password;
        this.role = role;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    public User(String userName, String password, Role role){
        this.userName=userName;
        this.password = password;
        this.role = role;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }
    public String getEmailAddress() {
        return emailAddress;
    }
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    public int getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
