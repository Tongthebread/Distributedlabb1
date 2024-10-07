package com.example.webshop.DTOS;

import com.example.webshop.model.Status;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class OrderDTO implements Serializable {
    private int id;
    private int userId;
    private Date orderDate;
    private Status status;
    private List<OrderItemDTO> orderItems;

    public OrderDTO(int id, int userId, Status status, Timestamp orderDate) {
    }

    public OrderDTO(int id, int userId, Date orderDate, Status status, List<OrderItemDTO> orderItems) {
        this.id = id;
        this.userId = userId;
        this.orderDate = orderDate;
        this.status = status;
        this.orderItems = orderItems;
    }

    // Getters och Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<OrderItemDTO> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemDTO> orderItems) {
        this.orderItems = orderItems;
    }
}
