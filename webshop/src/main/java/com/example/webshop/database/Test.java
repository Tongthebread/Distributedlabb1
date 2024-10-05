package com.example.webshop.database;

import com.example.webshop.model.Product;
import com.example.webshop.model.Role;
import com.example.webshop.model.User;
import com.example.webshop.service.UserService;

import java.sql.SQLException;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws SQLException {
        UserDAO userDAO = new UserDAO();
        System.out.println(userDAO.getAllUsers());
    }
}
