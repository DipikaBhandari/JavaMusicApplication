package com.example.musicapplication.Controllers;

import com.example.musicapplication.DataObject.Database;
import com.example.musicapplication.Models.Order;
import com.example.musicapplication.Models.Product;
import com.example.musicapplication.Models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
    public Button DeleteProductButton;
    public Button AddProductButton;
    public Button CreateOrderButton;
    public TextField txtPhoneNumber;
    public TextField txtFirstName;
    public TextField txtLastName;
    public TextField txtEmailAddress;
    public TableView <Product> OrdersTable;
    private Database database;

  //  private final List<Product> selectedItems = new ArrayList<>();
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

            }
        } catch (Exception e){
         e.printStackTrace();
        }
    }

    public void CreateOrder(ActionEvent actionEvent) {
        String customerFirstName = txtFirstName.getText();
        String customerLastName = txtLastName.getText();
        String email = txtEmailAddress.getText();
        int phoneNumber = parseInt(txtPhoneNumber.getText());

        try{
            User customer = new User(customerFirstName, customerLastName, email, phoneNumber);
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String currentDateTime = now.format(formatter);

            reduceStockProducts();

            Order order = new Order(currentDateTime, customer, new ArrayList<>(selectedItems));
            database.addOrderToFile(order);
            clearTextbox();
            OrdersTable.setItems(FXCollections.observableArrayList());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Product findItemByName(String productName) {
        for(Product product:database.getProducts()){
            if(product.getProductName().equals(productName)){
                return product;
            }
        } return null;

    }

    private void reduceStockProducts() {
        for (Product product : selectedItems) {
            Product selectedProduct = findItemByName(product.getProductName());
            if (selectedProduct != null) {
                selectedProduct.stockManagement(product.getQuantity());
            }
        }
    }

    public void clearTextbox() {
        txtFirstName.clear();
        txtLastName.clear();
        txtPhoneNumber.clear();
        txtEmailAddress.clear();
    }


}
