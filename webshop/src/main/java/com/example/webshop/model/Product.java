package com.example.webshop.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@ToString
public class Product {
    @Getter
    @Setter
    private int id;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private double price;
    @Getter
    @Setter
    private int stock;
    @Getter
    @Setter
    private int categoryId;
    public Product(){

    }
    public Product(int id, String name, double price, int stock, int categoryId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.categoryId = categoryId;
    }

}
