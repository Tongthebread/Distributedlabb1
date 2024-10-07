package com.example.webshop.model;


import com.example.webshop.DTOS.ProductDTO;
import lombok.Getter;
import lombok.Setter;

public class CartItem {
    @Getter @Setter
    private Product product;
    @Getter @Setter
    private int quantity;

    public CartItem(ProductDTO product, int quantity) {
    }
    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }
}
