package com.example.musicapplication.Controllers;

import com.example.musicapplication.DataObject.Database;
import com.example.musicapplication.MusicApplication;
import com.example.musicapplication.Models.ResultNotFoundException;
import com.example.musicapplication.Models.Role;
import com.example.musicapplication.Models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField usernametxt;
    @FXML
    private Label lblErrorMessage;
    @FXML
    private Button btnLogin;
    @FXML
    private PasswordField passwordtxt;
    private Database database;
    private Role role;
    private User user;

    public void start(Database database){
        this.database = database;
        btnLogin.setDisable(true);
        passwordtxt.textProperty().addListener((observable, oldValue, newValue) -> {
            boolean isValidPassword = PasswordValidity(newValue);
            btnLogin.setDisable(!isValidPassword);
        });
    }
    public void LoginButtonClick(ActionEvent actionEvent) throws IOException {
        String username = usernametxt.getText();
        String password = passwordtxt.getText();
        try {
            if(PasswordValidity(password)) {
                user = database.LoginAuthorization(username, password);
                loadMainView(username, role);
            }
        } catch (ResultNotFoundException ex) {
            lblErrorMessage.setText(ex.getMessage());
        }
    }

    public boolean PasswordValidity(String password){
        boolean hasDigits = false;
        boolean hasLetters = false;
        boolean hasSpecialCharacter = false;

        for (char c : password.toCharArray()){
            if (Character.isDigit(c)) {
                hasDigits = true;
            } else if (Character.isLetter(c)) {
                hasLetters = true;
            } else {
                hasSpecialCharacter = true;
            }
        }
        return password.length() > 7 && (hasDigits && hasLetters && hasSpecialCharacter);
    }

    public void loadMainView(String username, Role role){
        try{
            FXMLLoader mainViewLoader = new FXMLLoader(MusicApplication.class.getResource("MainView.fxml"));
            Parent root = mainViewLoader.load();
            MainViewController controller = mainViewLoader.getController();
            controller.userInstance(user);
            controller.useDatabase(database);
            controller.start(username, role);
            Scene scene = new Scene(root, 1100, 643);
            Stage stage = (Stage) btnLogin.getScene().getWindow();
            stage.setTitle("Wim's Music Shop");
            stage.setScene(scene);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
