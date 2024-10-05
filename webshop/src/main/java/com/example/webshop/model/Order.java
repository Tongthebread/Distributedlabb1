package com.example.webshop.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@ToString
public class Order {
    @Getter
    @Setter
    private int id;
    @Getter
    @Setter
    private int userId;
    @Getter
    @Setter
    private Timestamp orderDate;
    @Getter
    @Setter
    private Status status;

    public Order(int id, int userId, Status status, Timestamp orderDate) {
        this.id = id;
        this.userId = userId;
        this.status = status;
        this.orderDate = orderDate;
    }
}
