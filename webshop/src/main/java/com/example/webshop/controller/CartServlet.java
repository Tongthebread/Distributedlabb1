package com.example.webshop.controller;
import com.example.webshop.DTOS.ProductDTO;
import com.example.webshop.model.Cart;
import com.example.webshop.model.Product;
import com.example.webshop.service.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
    ProductService productService;
    @Override
    public void init(){
        productService = new ProductService();
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }
        request.getRequestDispatcher("WEB-INF/views/cart.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        int productId = Integer.parseInt(request.getParameter("productId"));

        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        if (cart == null) {
            cart = new Cart();
        }

        if ("add".equals(action)) {
            // Lägg till produkt i varukorgen
            ProductDTO product = null;
            try {
                product = productService.searchProduct(productId);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            if (product != null) {
                cart.addItem(product, 1);
            }
        } else if ("remove".equals(action)) {
            // Ta bort produkt från varukorgen
            cart.removeItem(productId);
        } else if ("update".equals(action)) {
            // Uppdatera kvantiteten för en produkt
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            cart.updateItem(productId, quantity);
        }

        session.setAttribute("cart", cart);
        response.sendRedirect("cart");
    }
}
