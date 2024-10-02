package com.example.webshop.controller;

import com.example.webshop.database.ProductDAO;
import com.example.webshop.model.Shop;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "ShopServlet", value = "/Shop-servlet")
public class ShopServlet extends HttpServlet {
    private ProductDAO productDAO = new ProductDAO();
    private Shop shop = new Shop();
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("products", shop.getProducts());

        // Forward the products to the index.jsp page
        request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

    }

}
