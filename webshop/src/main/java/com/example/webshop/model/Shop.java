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
    }
    public void addProduct(Product product) {
        this.products.add(product.getId(), product);
    }

    public void addToCart(Product product, int quantity) {
        this.cart.addProduct(product, quantity);
    }
}
