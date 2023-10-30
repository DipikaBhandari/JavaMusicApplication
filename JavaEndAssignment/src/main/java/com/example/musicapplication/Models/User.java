package com.example.musicapplication.Models;

import java.io.Serializable;

public class User implements Serializable {
    private String userName;
    private String password;


    private String customerFirstName;

    public String getCustomerLastName() {
        return customerLastName;
    }

    private String customerLastName;
    private Role role;

    public String getEmailAddress() {
        return emailAddress;
    }

    private String emailAddress;

    public int getPhoneNumber() {
        return phoneNumber;
    }

    private int phoneNumber;

    public User(String userName, String password, Role role){
        this.userName=userName;
        this.password = password;
        this.role = role;
    }
    public User(String customerFirstName,String customerLastName,String emailAddress, int phoneNumber)
    {
        this.customerFirstName=customerFirstName;
        this.customerLastName=customerLastName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }





    public String getUserName() {return userName;}


    public String getPassword() {
        return password;
    }



    public Role getRole() {
        return role;
    }


    public String getCustomerFirstName() {
        return customerFirstName;
    }
}
