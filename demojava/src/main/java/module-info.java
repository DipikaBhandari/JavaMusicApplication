module com.example.demojava {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.musicapplication to javafx.fxml;
    exports com.example.musicapplication;
    exports com.example.musicapplication.Controllers;
    opens com.example.musicapplication.Controllers to javafx.fxml;
}