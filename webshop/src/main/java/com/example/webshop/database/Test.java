package com.example.webshop.database;

import com.example.webshop.model.Category;
import com.example.webshop.model.Product;
import com.example.webshop.model.Role;
import com.example.webshop.model.User;
import com.example.webshop.service.ProductService;
import com.example.webshop.service.UserService;

import java.sql.SQLException;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws SQLException {
        ProductDAO productDAO = new ProductDAO();
        Product product = new Product(1, "banan", 10, 100, 1);
        ProductService productService = new ProductService();
        System.out.println(productService.getProducts());
    }
}
