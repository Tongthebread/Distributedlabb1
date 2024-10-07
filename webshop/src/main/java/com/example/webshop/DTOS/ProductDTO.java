package com.example.webshop.DTOS;
import java.io.Serializable;

public class ProductDTO implements Serializable {
    private int id;
    private String name;
    private double price;
    private int stock;
    private int categoryId;

    public ProductDTO(int id, String name, double price, int stock, int categoryId) {
    }

    public ProductDTO(int id, String name, String description, double price, int stock, String imageUrl, int categoryId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.categoryId = categoryId;
    }

    public ProductDTO() {

    }

    // Getters och Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }


    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
