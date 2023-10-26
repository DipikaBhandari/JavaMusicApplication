package com.example.musicapplication.Controllers;

import com.example.musicapplication.DataObject.Database;
import com.example.musicapplication.MusicApplication;
import com.example.musicapplication.Models.Role;
import com.example.musicapplication.Models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class MainViewController {
    public Button dashboardBtn;
    public Button createOrderBtn;
    public Button productInventoryBtn;
    public Button orderHistoryBtn;
    public Label welcomelbl;
    public Label rolelbl;
    public VBox vbox;
    private Database database;
    private User user;
    public void DashboardBtnClicked(ActionEvent actionEvent) {
    }

    public void CreateOrderBtnClicked(ActionEvent actionEvent) {
    }

    public void ProductInventoryBtnClicked(ActionEvent actionEvent) {
    }

    public void OrderHistoryBtnClicked(ActionEvent actionEvent) {
    }

    public void useDatabase(Database database) {
    }

    public void start(String username, Role role) {
    }

    public void userInstanse(User user) {
        this.user=user;
        try {
            basedOnUserRole();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void basedOnUserRole(){
        try{
            Role role = user.getRole();
            if(role==Role.Manager){
                dashboardBtn.setDisable(false);
                createOrderBtn.setDisable(true);
                productInventoryBtn.setDisable(false);
                orderHistoryBtn.setDisable(false);
            }
            else if (role==Role.Sales){
                dashboardBtn.setDisable(false);
                createOrderBtn.setDisable(false);
                productInventoryBtn.setDisable(true);
                orderHistoryBtn.setDisable(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void switchToOrderView() throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MusicApplication.class.getResource("CreatingOrderView.fxml"));
            Parent root = fxmlLoader.load();
            CreatingOrderViewController orderController = fxmlLoader.getController();
            orderController.useDatabase(database);
            vbox.getChildren().setAll(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }



}
