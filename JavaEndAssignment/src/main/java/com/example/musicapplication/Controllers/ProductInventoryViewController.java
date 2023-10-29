package com.example.musicapplication.Controllers;

import com.example.musicapplication.DataObject.Database;
import com.example.musicapplication.Models.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

public class ProductInventoryViewController {
    public Button AddProductbtn;
    public Button DeleteProductbtn;
    public Button EditProductbtn;
    public TableView <Product> ProductInventoryTableView;
    public TextField stocktxt;
    public TextField productnametxt;
    public TextField productcategorytxt;
    public TextField pricetxt;
    public TextField descriptiontxt;
    public Label MessageLabel;
    private Product product;
    private ObservableList<Product> products;

    private Database database;

    public void useDatabase(Database database) {
        this.database = database;
    }

    public void initialize() {
        try {
            ProductInventoryTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        } catch (Exception e) {
            // MessageLabel.setText("Initialization Failed");
            e.printStackTrace();
        }
    }

    public void viewProductsList() {
        try {
            products = FXCollections.observableArrayList(database.getProducts());
            ProductInventoryTableView.setItems(products);
        } catch (Exception ex) {
            MessageLabel.setText("Error loading products");
            ex.printStackTrace();
        }
    }

    public void AddProductButton(ActionEvent actionEvent) {
        try {
            int stock = Integer.parseInt(stocktxt.getText());
            String name = productnametxt.getText();
            String category = productcategorytxt.getText();
            double price = Double.parseDouble(pricetxt.getText());
            String description = descriptiontxt.getText();

            Product product = new Product(stock, name, category, price, description);
            database.addProductToFile(product);
            products.add(product);
            emptyTextFields();
            MessageLabel.setText(product.getProductName() + " added successfully.");
        } catch (Exception e) {
            MessageLabel.setText("Error occurred");
            e.printStackTrace();
        }
    }

    public void DeleteButton(ActionEvent actionEvent) {
        try {
            ObservableList<Product> selectedProducts = ProductInventoryTableView.getSelectionModel().getSelectedItems();
            for (Product product : selectedProducts) {
                database.removeProductFromFile(product);
            }
            products.removeAll(selectedProducts);
            MessageLabel.setText(" Product removed successfully.");
        } catch (Exception e) {
            MessageLabel.setText("Error occurred");
            e.printStackTrace();
        }
    }

    public void EditButton(ActionEvent actionEvent) {
        try {
            if (product == null) {
                setProductDetails();
            } else {
                  updateProductDetails();
                  PromptText();
            }
        } catch (Exception ex) {
            MessageLabel.setText("No Product Selected");
        }
    }

    private void setProductDetails() {
        try {
            Product selectedProduct = ProductInventoryTableView.getSelectionModel().getSelectedItem();
            if (selectedProduct != null) {
                stocktxt.setPromptText(String.valueOf(selectedProduct.getStock()));
                productnametxt.setPromptText(selectedProduct.getProductName());
                productcategorytxt.setPromptText(selectedProduct.getProductCategory());
                pricetxt.setPromptText(String.valueOf(selectedProduct.getPrice()));
                descriptiontxt.setPromptText(selectedProduct.getProductDescription());
                product = selectedProduct;
            }
        } catch (Exception e) {
            MessageLabel.setText("Error setting product details.");
        }
    }
    public void emptyTextFields() {
        try {
            stocktxt.clear();
            productnametxt.clear();
            productcategorytxt.clear();
            pricetxt.clear();
            descriptiontxt.clear();
        } catch (Exception e) {
            MessageLabel.setText("Error clearing fields.");
        }
    }

    private void PromptText() {
        try {
            stocktxt.setPromptText("Stock");
            productnametxt.setPromptText("Product Name");
            productcategorytxt.setPromptText("Category");
            pricetxt.setPromptText("Price");
            descriptiontxt.setPromptText("Description");
        } catch (Exception e) {
            MessageLabel.setText("Error resetting prompt text.");
        }
    }

    public void updateProductDetails() {
        String stock = stocktxt.getText();
        String name = productnametxt.getText();
        String category = productcategorytxt.getText();
        String  price = pricetxt.getText();
        String description = descriptiontxt.getText();
        try {

            int updatedStock = stock.isEmpty()? product.getStock(): Integer.parseInt(stock);
            String updatedName = name.isEmpty()? product.getProductName(): name;
            String updatedCategory = category.isEmpty()? product.getProductCategory(): category;
            double updatedPrice = price.isEmpty()? product.getPrice(): Double.parseDouble(price);
            String updatedDescription = description.isEmpty()? product.getProductDescription(): description;

                Product updatedProduct = new Product(updatedStock, updatedName, updatedCategory, updatedPrice, updatedDescription);
                database.removeProductFromFile(product);
                database.addProductToFile(updatedProduct);

                int selectionIndex = products.indexOf(product);
                products.set(selectionIndex, updatedProduct);

                 emptyTextFields();
                product = null;
                MessageLabel.setText("Product Successfully Edited");
            } catch (NumberFormatException ex) {
            throw new RuntimeException(ex);
        }

    }


}
