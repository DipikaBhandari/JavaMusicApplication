package com.example.musicapplication.Controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
    public PasswordField Passwordtxt;
    public TextField Usernametxt;
    public Label lblErrorMessage;
    public Button btnLogin;

    public void onLoginButtonClick(ActionEvent actionEvent) {
        String username = Usernametxt.getText();
        String password = Passwordtxt.getText();


    }
}
