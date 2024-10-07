package com.example.webshop.database;

import com.example.webshop.DTOS.OrderDTO;
import com.example.webshop.model.Order;
import com.example.webshop.model.OrderItem;
import com.example.webshop.model.Status;

import java.sql.*;
import java.util.ArrayList;

public class OrderDAO {
    private final String url = "jdbc:postgresql://localhost:5432/products";
    private final String user = "postgres";
    private final String password = "psyke456SONG";

    private Connection connect() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(url, user, password);
    }
    public void InsertOrder(Order order, ArrayList<OrderItem> orderItems) throws SQLException {
        String insertQuery = "INSERT INTO orders(user_id, order_date, status) VALUES (?, ?, ?)";
        String insertOrderItemSQL = "INSERT INTO order_items (order_id, product_id, quantity, price_at_purchase) VALUES (?, ?, ?, ?)";
        String updateProductStockSQL = "UPDATE products SET stock = stock - ? WHERE id = ?";
        Connection conn = connect();
        PreparedStatement stmt1 = null;
        PreparedStatement stmt2 = null;
        PreparedStatement stmt3 = null;
        try {
            conn.setAutoCommit(false);
            stmt1 = conn.prepareStatement(insertQuery);
            stmt1.setInt(1, order.getUserId());
            stmt1.setTimestamp(2, order.getOrderDate());
            stmt1.setString(3, order.getStatus().toString());
            stmt1.executeUpdate();
            ResultSet rs = stmt1.getGeneratedKeys();
            int orderId = 0;
            if (rs.next()) {
                orderId = rs.getInt(1);
            }
            order.setId(orderId);
            stmt2 = conn.prepareStatement(insertOrderItemSQL);
             stmt3 = conn.prepareStatement(updateProductStockSQL);
            for (OrderItem orderItem : orderItems) {
                stmt2.setInt(1, orderId);
                stmt2.setInt(2, orderItem.getProductId());
                stmt2.setInt(3, orderItem.getQuantity());
                stmt2.setDouble(4, orderItem.getPrice());
                stmt2.addBatch();
                stmt3.setInt(1, orderItem.getQuantity());
                stmt3.setInt(2, orderItem.getProductId());
                stmt3.addBatch();
            }
            stmt2.executeBatch();
            stmt3.executeBatch();
            conn.commit();


        }catch (SQLException e) {
            if (conn != null) {
                conn.rollback();
            }
            throw e;
        }
        finally {
            if( stmt1 != null) stmt1.close();
            if (stmt2 != null) stmt2.close();
            if (stmt3 != null) stmt3.close();
            if (conn != null) conn.close();
        }

    }
    public ArrayList<OrderDTO> getOrdersByUserId(int userId) throws SQLException {
        String query = "SELECT * FROM orders WHERE user_id = ?";
        ArrayList<Order> orders = new ArrayList<>();
        try (Connection connection = connect();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Order order = new Order(rs.getInt("id"),
                            rs.getInt("user_id"),
                            Status.valueOf(rs.getString("status")),
                            rs.getTimestamp("order_date"));
                    orders.add(order);
                }
            }
        }
        return orders;
    }
    public OrderDTO getOrdersById(int id) throws SQLException {
        String query = "SELECT * FROM orders WHERE id = ?";
        OrderDTO order = null;
        try (Connection connection = connect();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    order = new OrderDTO(
                            rs.getInt("id"),
                            rs.getInt("user_id"),
                            Status.valueOf(rs.getString("status")),
                            rs.getTimestamp("order_date")
                    );
                }
            }
        }
        return order;
    }


    public void UpdateOrderState(OrderDTO order) throws SQLException {
        String updateQuery = "UPDATE orders SET status = ? WHERE id = ?";
        try (Connection connection = connect();
        PreparedStatement stmt = connection.prepareStatement(updateQuery)) {
            stmt.setString(1, order.getStatus().toString());
            stmt.setInt(2, order.getId());
            stmt.executeUpdate();
        }
    }
}
