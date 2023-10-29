package com.example.musicapplication.Controllers;

import com.example.musicapplication.DataObject.Database;
import com.example.musicapplication.Models.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DashboardController {
    @FXML
    private Label welcome;
    @FXML
    private Label userRole;
    @FXML
    private Label datetime;

    private Database database;
    private User user;

    public void userInstance(User user){
        this.user=user;
    }

    public void useDatabase(Database database){
        this.database=database;
    }

    public void start() {
        Welcome();
    }
    public void Welcome(){
        welcome.setText("Welcome " + user.getUserName()+ "!");;
        userRole.setText("Your role is "+ user.getRole()+ ".");
        DateTime();
    }

    public void DateTime(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);
        datetime.setText("It is now: " + formattedDateTime);
    }
}
