package com.example.webshop.model;

import java.util.HashMap;
import java.util.Map;

public class Cart {

    // A map to store products and their quantities
    private Map<Product, Integer> items;

    public Cart() {
        items = new HashMap<>();
    }
    public void addProduct(Product product, int quantity) {
        // If the product already exists in the cart, update the quantity
        if (items.containsKey(product)) {
            items.put(product, items.get(product) + quantity);
        } else {
            items.put(product, quantity);
        }
    }
    public Product getProductById(int id) {
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            if (entry.getValue() == id) {
                return entry.getKey();
            }
        }
        return null;
    }

    public void removeProduct(Product product) {
        items.remove(product);
    }

    // Method to get the total price of items in the cart
    public double getTotalPrice() {
        double total = 0;
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            total += product.getPrice() * quantity;
        }
        return total;
    }

    // Method to clear the cart
    public void clearCart() {
        items.clear();
    }

    // Method to get all items in the cart
    public Map<Product, Integer> getItems() {
        return items;
    }

    // Method to get the total number of products in the cart
    public int getTotalItems() {
        int totalItems = 0;
        for (int quantity : items.values()) {
            totalItems += quantity;
        }
        return totalItems;
    }
}

