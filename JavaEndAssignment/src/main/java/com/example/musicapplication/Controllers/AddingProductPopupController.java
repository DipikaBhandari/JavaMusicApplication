package com.example.musicapplication.Controllers;

import com.example.musicapplication.DataObject.Database;
import com.example.musicapplication.Models.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddingProductPopupController {
    public Button AddToOrderButton;
    public TextField quantity;
    @FXML
    private TableView<Product> SelectProductTableView;
    public Button CancelButton;
    private Database database;
    private ObservableList<Product> products;

    private CreatingOrderViewController creatingOrderViewController;

    public void setCreatingOrderViewController(CreatingOrderViewController creatingOrderViewController){
        this.creatingOrderViewController=creatingOrderViewController;
    }

    public void useDatabase(Database database) {
        this.database=database;
        try{
            loadProducts();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void initialize(){
        try{
            SelectProductTableView .getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void loadProducts(){
        try {
            products = FXCollections.observableArrayList(database.getProducts());
            SelectProductTableView.setItems(products);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }


    public void AddButton(ActionEvent actionEvent) {
        try{
            int numberOfItem = Integer.parseInt(quantity.getText());
            Product selectProduct = SelectProductTableView.getSelectionModel().getSelectedItem();

            if (selectProduct.getStock() >= numberOfItem){
                Product newproduct = new Product(numberOfItem, selectProduct.getProductName(), selectProduct.getProductCategory(), selectProduct.getPrice());
                creatingOrderViewController.getOrderedProduct(newproduct);
                quantity.clear();
            }

        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void CancelButton(ActionEvent actionEvent) {
        try{
            Stage stage = (Stage)CancelButton.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
