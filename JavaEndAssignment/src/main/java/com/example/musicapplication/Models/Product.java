package com.example.musicapplication.Models;

import java.io.Serializable;

public class Product implements Serializable {
    private String productName;
    private String productCategory;
    private String productDescription;
    private int stock;
    private double price;
    private int quantity;
    private double finalPrice;

    public Product(int stock, String productName, String  productCategory, double price, String productDescription){
        this.stock = stock;
        this.productName = productName;
        this.productCategory = productCategory;
        this.price = price;
        this.productDescription = productDescription;
    }

    public Product(int quantity, String productName, String productCategory,double finalPrice ){
        this(0,productName,productCategory,0,null);
        this.quantity = quantity;
        this.finalPrice = finalPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getFinalPrice() {
        return finalPrice*quantity;
    }

    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }
    public void stockManagement(int quantity){
        if (stock> quantity){
            stock -= quantity;
        }
    }


}
