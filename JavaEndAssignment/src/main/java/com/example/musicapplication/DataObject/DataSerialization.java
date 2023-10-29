package com.example.musicapplication.DataObject;

import com.example.musicapplication.Models.Order;
import com.example.musicapplication.Models.Product;
import com.example.musicapplication.Models.User;

import java.io.Serializable;
import java.util.List;

public class DataSerialization implements Serializable {

    public List<User> users;
    public List<Product> products;
    public List<Order> orders;

    public DataSerialization(List<User> users, List<Product> products, List<Order> orders) {
        this.users = users;
        this.products = products;
        this.orders = orders;
    }
}
