package com.example.musicapplication.DataObject;

import com.example.musicapplication.Models.ResultNotFoundException;
import com.example.musicapplication.Models.Role;
import com.example.musicapplication.Models.User;

import java.util.ArrayList;
import java.util.List;

public class Database {
    private List<User> users = new ArrayList<>();
    public Database() {

        users.add(new User("Wim", "Wim!1111", Role.Sales));
        users.add(new User("Wim", "Wim@2222", Role.Manager));
    }

    public User LoginAuthorization(String userName, String password) throws ResultNotFoundException {
        for (User user: users)
        {
            if(user.getUserName().equals(userName)&&user.getPassword().equals(password)){
                return user;
            }
        }
        throw  new ResultNotFoundException("Invalid username or password combination.");
    }
}


