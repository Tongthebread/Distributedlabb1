package com.example.webshop.database;

import com.example.webshop.model.Product;

import java.sql.SQLException;

public class Test {
    public static void main(String[] args) throws SQLException {
        Product product = new Product(0, "apple", 100, 500);
        ProductDAO dao = new ProductDAO();
        System.out.println(dao.getAllProducts());
        dao.deleteProduct(5);
        System.out.println(dao.getAllProducts());

    }
}
