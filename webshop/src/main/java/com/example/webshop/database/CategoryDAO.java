package com.example.webshop.database;

import com.example.webshop.model.Category;

import java.sql.*;
import java.util.ArrayList;

public class CategoryDAO {
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
    public void insert(Category category) throws SQLException {
        String query = "INSERT INTO categories (category_name) VALUES (?)";
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, category.getCategoryName());
            preparedStatement.executeUpdate();
        }
    }
    Category getCategoryById(int id) throws SQLException {
        String query = "SELECT * FROM categories WHERE id = ?";
        try (Connection connection = connect();
        PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Category(resultSet.getInt("id"), resultSet.getString("category_name") );
            }
        }
        return null;
    }
    public void updateCategory(Category category) throws SQLException {
        String query = "UPDATE categories SET category_name = ? WHERE id = ?";
        try (Connection connection = connect();
        PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, category.getCategoryName());
            preparedStatement.setInt(2, category.getId());
            preparedStatement.executeUpdate();
        }
    }
    public void deleteCategory(int id) throws SQLException {
        String query = "DELETE FROM categories WHERE id = ?";
        try (Connection connection = connect();
        PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }
    public ArrayList<Category> getAllCategories() throws SQLException {
        ArrayList<Category> categories = new ArrayList<>();
        String query = "SELECT * FROM categories";
        try (Connection connection = connect();
        PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Category category = new Category(resultSet.getInt("id"), resultSet.getString("category_name"));
                categories.add(category);
            }
        }
        return categories;
    }
}
