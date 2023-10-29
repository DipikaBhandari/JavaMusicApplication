package com.example.musicapplication.Models;

import java.io.Serializable;
import java.util.ArrayList;

public class Order implements Serializable {

    private User user;
    private Product product;
    private ArrayList<Product> productList;
    private String orderDate;
    public Order(String orderDate,User user,  ArrayList <Product> productList){
        this.user = user;
        this.productList = productList;
        this.orderDate= orderDate;
    }
    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
    public String getOrderDate() {
        return orderDate;
    }
    public User getUser() {
        return user;
    }
    public ArrayList<Product> getProducts() {
        return productList;
    }
    public void setProducts(ArrayList<Product> products) {
        this.productList = productList;
    }
}
