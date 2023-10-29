package com.example.musicapplication;

import com.example.musicapplication.Controllers.LoginController;
import com.example.musicapplication.DataObject.Database;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MusicApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {

      Database database= new Database();

        FXMLLoader loginLoader = new FXMLLoader(MusicApplication.class.getResource("login.fxml"));
        Scene scene = new Scene(loginLoader.load(), 401, 256);
        LoginController controller = loginLoader.getController();
        stage.setTitle("Wim's Music Shop");
        stage.setScene(scene);
        controller.start(database);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
