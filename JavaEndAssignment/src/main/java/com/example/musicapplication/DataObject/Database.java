package com.example.musicapplication.DataObject;

import com.example.musicapplication.Models.Role;
import com.example.musicapplication.Models.User;

import java.util.ArrayList;
import java.util.List;

public class Database {

    private List<User> user = new ArrayList<>();
    public Database(){

        user.add(new User("Wim", "Wim!111", Role.Sales, "wim111@gmail.com", 06267));
    }

    public User
}
