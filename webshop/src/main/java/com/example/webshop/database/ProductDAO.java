package com.example.webshop.database;

import com.example.webshop.DTOS.ProductDTO;

import java.sql.*;
import java.util.ArrayList;

public class ProductDAO {
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

    public void insertProduct(ProductDTO product) throws SQLException {
        String query = "INSERT INTO products (name, price, stock, category_id) VALUES (?, ?, ?, ?)";
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setInt(3, product.getStock());
            preparedStatement.setInt(4, product.getCategoryId());
            preparedStatement.executeUpdate();
        }
    }

    public ProductDTO getProductById(int id) throws SQLException {
        String query = "SELECT * FROM products WHERE id = ?";
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new ProductDTO(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("stock"),
                        resultSet.getInt("category_id")
                );
            }
        }
        return null; // Returnerar null om produkten inte hittas
    }

    public void updateProduct(ProductDTO product) throws SQLException {
        String query = "UPDATE products SET name = ?, price = ?, stock = ?, category_id = ? WHERE id = ?";
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setInt(3, product.getStock());
            preparedStatement.setInt(4, product.getCategoryId());
            preparedStatement.setInt(5, product.getId());
            preparedStatement.executeUpdate();
        }
    }

    public void deleteProduct(int id) throws SQLException {
        String query = "DELETE FROM products WHERE id = ?";
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }

    public ArrayList<ProductDTO> getAllProducts() throws SQLException {
        ArrayList<ProductDTO> products = new ArrayList<>();
        String query = "SELECT * FROM products";
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                ProductDTO product = new ProductDTO(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("stock"),
                        resultSet.getInt("category_id")
                );
                products.add(product);
            }
        }
        return products;
    }
}
