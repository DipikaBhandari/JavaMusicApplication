package com.example.musicapplication.Controllers;

import com.example.musicapplication.DataObject.Database;
import com.example.musicapplication.Models.Order;
import com.example.musicapplication.Models.Product;
import com.example.musicapplication.Models.User;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class OrderHistoryViewController {

    @FXML
    private TableColumn<Order, String> nameColumn;

    @FXML
    private TableColumn<Order, String> priceColumn;
    @FXML
    private TableView<Product> orderedProductsTable;
    @FXML
    private TableView<Order> orderHistoryTable;

    @FXML
    private Label messageLabel;

    private ObservableList<Product> products;
    private ObservableList<Order> orders;

    private Database database;

    public void useDatabase(Database database) {
        this.database=database;
        loadOrder();
    }

    public void initialize() {
        orderHistoryTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        nameColumn.setCellValueFactory(cellDataFeatures -> {
            Order order = cellDataFeatures.getValue();
            User user = order.getUser();
            if (user != null) {
                return new SimpleStringProperty(user.getCustomerFirstName());
            } else {
                return new SimpleStringProperty("");
            }
        });

        priceColumn.setCellValueFactory (cellData-> new SimpleDoubleProperty(cellData.getValue().getProducts()
                .stream().mapToDouble(Product::getFinalPrice).sum()).asObject().asString());
        orderHistoryTable.getSelectionModel().
                selectedItemProperty().
                addListener((observableValue, oldOrder, newOrder) -> {
                    if (newOrder != null) {
                        loadProduct(newOrder);
                    }
                });
    }

    private void loadProduct(Order order) {
        products = FXCollections.observableArrayList(order.getProducts());
        orderedProductsTable.setItems(products);
    }

    private void loadOrder() {
        try {
            orders = FXCollections.observableArrayList(database.getOrders());
            orderHistoryTable.setItems(orders);
        } catch (Exception e) {
            messageLabel.setText("An Error Occurred While Loading Orders");
        }
    }
}




