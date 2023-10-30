package com.example.musicapplication.Controllers;

import com.example.musicapplication.DataObject.Database;
import com.example.musicapplication.Models.Order;
import com.example.musicapplication.Models.Product;
import com.example.musicapplication.Models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;

//import java.awt.Label;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class CreatingOrderViewController {
    @FXML
    private Label messageLabel;
    @FXML
    private TextField txtPhoneNumber;
    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtEmailAddress;
    @FXML
    private TableView <Product> OrdersTable;
    private Database database;

    private ObservableList<Product> selectedItems = FXCollections.observableArrayList();

    private User user;

    public void useDatabase(Database database) {
        this.database= database;
    }

    public void userInstance(User user) {this.user = user;}

    public void initialize() {
        OrdersTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        OrdersTable.setItems(selectedItems);
    }

    public void getOrderedProduct(Product product) {
        selectedItems.add(product);
    }
    public void AddProduct(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/musicapplication/AddingProductPopup.fxml"));
            Parent root = fxmlLoader.load();
            AddingProductPopupController popupViewControllerController = fxmlLoader.getController();
            popupViewControllerController.useDatabase(database);
            popupViewControllerController.setCreatingOrderViewController(this);

            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("Add product To order");
            dialog.getDialogPane().setContent(root);
            dialog.showAndWait();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void DeleteProduct(ActionEvent actionEvent) {
        try {
            Product selectedProduct = OrdersTable.getSelectionModel().getSelectedItem();
            if (selectedProduct != null) {
                selectedItems.remove(selectedProduct);
                database.increaseStockProducts(selectedProduct.getProductName(), selectedProduct.getQuantity());
                messageLabel.setText("");
            }
        } catch (Exception e){
            messageLabel.setText("Error: Deleting Product");
         e.printStackTrace();
        }
    }

    public void CreateOrder(ActionEvent actionEvent) {
        String customerFirstName = txtFirstName.getText();
        String customerLastName = txtLastName.getText();
        String email = txtEmailAddress.getText();
        String phoneNumber = txtPhoneNumber.getText();

        if(customerFirstName.isEmpty() || customerLastName.isEmpty() || email.isEmpty() || phoneNumber.isEmpty()){
            messageLabel.setText("Error: Please fill in all the fields.");
        }
        if(OrdersTable.getItems().isEmpty()){
            messageLabel.setText("Please click on 'Add Product' and choose item to create your order.");
        }
        try{
            int number = Integer.parseInt(phoneNumber);
            User customer = new User(customerFirstName, customerLastName, email, number);
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String currentDateTime = now.format(formatter);

            Order order = new Order(currentDateTime, customer, new ArrayList<>(selectedItems));
            database.addOrderToFile(order);
            clearTextbox();
            OrdersTable.setItems(FXCollections.observableArrayList());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    public void clearTextbox() {
        txtFirstName.clear();
        txtLastName.clear();
        txtPhoneNumber.clear();
        txtEmailAddress.clear();
    }


}
