package com.example.webshop.model;


import lombok.Getter;
import lombok.Setter;

public class CartItem {
    @Getter @Setter
    private Product product;
    @Getter @Setter
    private int quantity;

    public CartItem() {
    }
    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }
}
