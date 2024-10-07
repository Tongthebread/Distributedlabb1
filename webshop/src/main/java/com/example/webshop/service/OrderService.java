package com.example.webshop.service;

import com.example.webshop.DTOS.OrderDTO;
import com.example.webshop.DTOS.UserDTO;
import com.example.webshop.database.OrderDAO;
import com.example.webshop.database.OrderItemDAO;
import com.example.webshop.model.Order;
import com.example.webshop.model.OrderItem;
import com.example.webshop.model.Status;
import com.example.webshop.model.User;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class OrderService {
    private OrderDAO orderDAO;

    public OrderService() {
        this.orderDAO = new OrderDAO();
    }

    public Boolean placeOrder(UserDTO user, ArrayList<OrderItem> orderItem) throws SQLException {
        Order order = new Order(1, user.getId(), Status.PENDING, new Timestamp(System.currentTimeMillis()));
        orderDAO.InsertOrder(order, orderItem);
        return true;
    }

    public ArrayList<OrderDTO> getUserOrders(int userId) throws SQLException {
        return orderDAO.getOrdersByUserId(userId);
    }

    public Boolean updateOrder(OrderDTO order) throws SQLException {
        orderDAO.UpdateOrderState(order);
        return true;
    }
    public Boolean updateOrderStatus(int orderId, Status status) throws SQLException {
        OrderDTO orderDTO = orderDAO.getOrdersById(orderId);
        orderDTO.setStatus(status);
        orderDAO.UpdateOrderState(orderDTO);
        return true;
    }
}
