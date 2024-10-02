package com.example.webshop.model;

import lombok.Getter;

import java.util.ArrayList;

public class Shop {
    private Cart cart;
    @Getter
    private ArrayList<Product> products;
    public Shop() {
        this.cart = new Cart();
        this.products = new ArrayList<>();
        loadInitialProducts();
    }
    public void addProduct(Product product) {
        this.products.add(product.getId(), product);
    }
    private void loadInitialProducts() {
        products.add(new Product(1, "Laptop", 1500.99, 10));
        products.add(new Product(2, "Smartphone", 799.99, 25));
        products.add(new Product(3, "Headphones", 199.99, 50));
        products.add(new Product(4, "Tablet", 399.99, 15));
    }
    public Product getProduct(int id) {
        return this.products.get(id);
    }

    public void addToCart(Product product, int quantity) {
        this.cart.addProduct(product, quantity);
    }
}
