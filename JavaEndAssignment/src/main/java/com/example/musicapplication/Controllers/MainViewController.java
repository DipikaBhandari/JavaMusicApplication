package com.example.musicapplication.Controllers;

import com.example.musicapplication.DataObject.Database;
import com.example.musicapplication.MusicApplication;
import com.example.musicapplication.Models.Role;
import com.example.musicapplication.Models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class MainViewController {
    public Label welcome;
    public Label userRole;
    @FXML
    private Button dashboardBtn;
    @FXML
    private Button createOrderBtn;
    @FXML
    private Button productInventoryBtn;
    @FXML
    private Button orderHistoryBtn;
    @FXML
    private VBox vbox;
    private Database database;
    private User user;
    public void useDatabase(Database database) {
        this.database=database;
    }

    public void userInstance(User user) {
        this.user=user;
        try {
            basedOnUserRole();
           MainView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void basedOnUserRole(){
        try{
            Role role = user.getRole();
            if (role==Role.Manager){
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
    public void MainView(){
        welcome.setText("Welcome " + user.getUserName()+ "!");;
        userRole.setText("Your role is "+ user.getRole()+ ".");
    }

    public void DashboardBtnClicked(ActionEvent actionEvent) throws IOException {DisplayDashboardView();}
    public void CreateOrderBtnClicked(ActionEvent actionEvent) throws IOException {DisplayOrderView();}

    public void ProductInventoryBtnClicked(ActionEvent actionEvent) throws IOException {DisplayProductInventory();}

    public void OrderHistoryBtnClicked(ActionEvent actionEvent) throws IOException {DisplayOrderHistory();}
    public void DisplayOrderView() throws IOException {
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

    public void DisplayProductInventory() throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MusicApplication.class.getResource("ProductInventoryView.fxml"));
            Parent root = fxmlLoader.load();
            ProductInventoryViewController productInventoryController = fxmlLoader.getController();
            productInventoryController.useDatabase(database);
           productInventoryController.viewProductsList();
            vbox.getChildren().setAll(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void DisplayOrderHistory() throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MusicApplication.class.getResource("OrderHistoryView.fxml"));
            Parent root = fxmlLoader.load();
            OrderHistoryViewController historyController = fxmlLoader.getController();
            historyController.useDatabase(database);
            vbox.getChildren().setAll(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void DisplayDashboardView() throws IOException {
        try {
            FXMLLoader dashboardLoader = new FXMLLoader(MusicApplication.class.getResource("DashboardView.fxml"));
            Parent root = dashboardLoader.load();
            DashboardController dashboardController = dashboardLoader.getController();
            dashboardController.userInstance(user);
            dashboardController.useDatabase(database);
            vbox.getChildren().setAll(root);
            hideMainView();
            dashboardController.start();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void start(String username, Role role) {
    }
    public void hideMainView(){
        welcome.setText("");
        userRole.setText("");
    }
}
