package com.example.webshop.controller;

import com.example.webshop.database.ProductDAO;
import com.example.webshop.model.Cart;
import com.example.webshop.model.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ShoppingCartServlet", value = "/ShoppingCart-servlet")
public class ShoppingCartServlet extends HttpServlet { ;
    private ProductDAO productDAO = new ProductDAO();
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }
        request.setAttribute("cartItems", cart.getItems());
        request.setAttribute("totalPrice", cart.getTotalPrice());

        try {
            request.getRequestDispatcher("/WEB-INF/views/cart.jsp").forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int productId = Integer.parseInt(request.getParameter("productId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));;
        Product product = null;
        try {
            product = productDAO.getProductById(productId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (product != null) {
            // Get the session, or create a new one if it doesn't exist
            HttpSession session = request.getSession();

            // Retrieve the cart from the session, or create a new cart if not present
            Cart cart = (Cart) session.getAttribute("cart");
            if (cart == null) {
                cart = new Cart();
                session.setAttribute("cart", cart);
            }

            // Add the product to the cart
            cart.addProduct(product, quantity);
        }
        response.sendRedirect("/shoppingcart");
    }
    public void destroy() {
    }
}