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

    public void sampleData(){
        User customer= new User("Dipika", "Bhandari", "dipika1@gmail.com", 2345345);

        products.add(new Product(10, "Flute", "Flute", 300.0, "It's a nice flute ehe." ));
        products.add(new Product(10, "Trumpet", "Trumpet", 600.0, "It's a nice trumpet ehe." ));
        List<Product> orderedProduct = new ArrayList<>();
        orders.add(new Order("01:00:00 30-10-2023", customer, (ArrayList<Product>) orderedProduct));

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
            sampleData();
            saveDataToFile();
        }
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
            dataSerialization =(DataSerialization)objectInputStream.readObject();
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
    public void reduceStockProducts(String productname, int quantity) {
        Product product = findItemByName(productname);
        int stock = product.getStock();
        product.setStock(stock-quantity);

    }

    private Product findItemByName(String productName) {
        for(Product product: products){
            if(product.getProductName().equals(productName)){
                return product;
            }
        } return null;

    }
    public void increaseStockProducts(String productname, int quantity){
        Product product = findItemByName(productname);
        int stock = product.getStock();
        product.setStock(stock+quantity);
    }
}


