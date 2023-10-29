module com.example.musicapplication {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.musicapplication to javafx.fxml;
    exports com.example.musicapplication;
    exports com.example.musicapplication.Controllers;
    opens com.example.musicapplication.Controllers to javafx.fxml;
    exports com.example.musicapplication.hellofile;
    opens com.example.musicapplication.hellofile to javafx.fxml;
    opens com.example.musicapplication.Models to javafx.base;
}