package com.example.musicapplication.DataObject;

import com.example.musicapplication.Models.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private List<User> users = new ArrayList<>();

    private List<Product> products = new ArrayList<>();

    private List<Order> orders = new ArrayList<>();
    private DataSerialization dataSerialization;

    public Database() {

        users.add(new User("Wim", "Wim!1111", Role.Sales));
        users.add(new User("Wim", "Wim@2222", Role.Manager));

        loadDataFromFile();
    }

    public User LoginAuthorization(String userName, String password) throws ResultNotFoundException {
        for (User user : users) {
            if (user.getUserName().equals(userName) && user.getPassword().equals(password)) {
                return user;
            }
        }
        throw new ResultNotFoundException("Invalid username or password combination.");
    }


    public List<Product> getProducts() {
        return products;
    }

    public void saveDataToFile(){
        dataSerialization = new DataSerialization(users, products, orders);
        try{
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Music.dat"));
            oos.writeObject(dataSerialization);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadDataFromFile() {
        File file = new File("Music.dat");
        if (!file.exists())
        {
            saveDataToFile();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            dataSerialization =(DataSerialization)ois.readObject();
            users.clear();
            products.clear();
            orders.clear();
            users.addAll(dataSerialization.users);
            products.addAll(dataSerialization.products);
            orders.addAll(dataSerialization.orders);

        } catch (IOException | ClassNotFoundException e) {
           e.printStackTrace();
        }

    }


    public void addProductToFile(Product product) {
       // products.add(new Product(5, "guitar", "g", 9.00, "yryjgyjgj"));
        products.add(product);
        saveDataToFile();
    }

    public void removeProductFromFile(Product product) {
        products.remove(product);
        saveDataToFile();
    }
    public List<Order> getOrders() {
        return orders;
    }
    public void addOrderToFile(Order order) {
        orders.add(order);
        saveDataToFile();
    }
}


