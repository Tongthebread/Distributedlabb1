package com.example.webshop.controller;
import com.example.webshop.DTOS.ProductDTO;
import com.example.webshop.model.Product;
import com.example.webshop.model.User;
import com.example.webshop.service.ProductService;
import com.example.webshop.service.UserService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import lombok.SneakyThrows;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {
    private ProductService productService;

    @Override
    public void init() throws ServletException {
        productService = new ProductService();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            ArrayList<ProductDTO> products = productService.getProducts();
            req.setAttribute("productList", products);
            req.getRequestDispatcher("/products.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
