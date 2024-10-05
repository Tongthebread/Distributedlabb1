package com.example.webshop.controller;
import com.example.webshop.model.Product;
import com.example.webshop.model.User;
import com.example.webshop.service.ProductService;
import com.example.webshop.service.UserService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
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
        ArrayList<Product> products = new ArrayList<>();
        try {
            products = productService.getProducts();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("products", products);
        req.getRequestDispatcher("WEB-INF/views/products.jsp").forward(req, resp);
    }
}
