package com.example.webshop.database;

import com.example.webshop.model.OrderItem;

import java.sql.*;
import java.util.ArrayList;

public class OrderItemDAO {
    private final String url = "jdbc:postgresql://localhost:5432/products";
    private final String user = "postgres";
    private final String password = "psyke456SONG";

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
    public void insertOrderItem(OrderItem orderItem) throws SQLException {
        String query = "INSERT INTO order_items (id, order_id, product_id, quantity, price_at_purchase) VALUES (?, ?, ?, ?)";
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, orderItem.getId());
            preparedStatement.setInt(2, orderItem.getOrderId());
            preparedStatement.setInt(3, orderItem.getProductId());
            preparedStatement.setInt(4, orderItem.getQuantity());
            preparedStatement.setDouble(5, orderItem.getPrice());
            preparedStatement.executeUpdate();
        }

    }
    ArrayList<OrderItem> getOrderItems(int orderId) throws SQLException {
        String query = "SELECT * FROM order_items WHERE order_id = ?";
        ArrayList<OrderItem> orderItems = new ArrayList<>();
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, orderId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    OrderItem item = new OrderItem(
                            resultSet.getInt("id"),
                            resultSet.getInt("order_id"),
                            resultSet.getInt("product_id"),
                            resultSet.getInt("quantity"),
                            resultSet.getDouble("price_at_purchase")
                            );
                orderItems.add(item);
                }
            }
        }
        return orderItems;
    }
}
