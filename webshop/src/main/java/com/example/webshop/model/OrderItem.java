package com.example.webshop.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class OrderItem {
    @Getter @Setter
    private int id;
    @Getter @Setter
    private int orderId;
    @Getter @Setter
    private int productId;
    @Getter @Setter
    private int quantity;
    @Getter @Setter
    private double price;
    public OrderItem(){

    }
    public OrderItem(int id, int orderId, int productId, int quantity, double price) {
        this.id = id;
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }
}
