package com.example.webshop.controller;
import com.example.webshop.model.Cart;
import com.example.webshop.model.CartItem;
import com.example.webshop.model.OrderItem;
import com.example.webshop.model.User;
import com.example.webshop.service.OrderService;
import com.example.webshop.service.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {
    private OrderService orderService;
    @Override
    public void init() throws ServletException {
        this.orderService = new OrderService();
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/views/checkout.jsp").forward(request, response);
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Cart cart = (Cart) session.getAttribute("cart");
        if (user == null) {
            response.sendRedirect("login");
            return;
        }
        if (cart == null || cart.getItems().isEmpty()) {
            response.sendRedirect("cart?message=Din varukorg är tom.");
            return;
        }
        ArrayList<OrderItem> orderItems = new ArrayList<>();
        for (CartItem cartItem : cart.getItems().values()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(cartItem.getProduct().getId());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getProduct().getPrice());
            orderItems.add(orderItem);
        }
        Boolean orderPlaced = false;
        try {
            orderPlaced = orderService.placeOrder(user, orderItems);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (orderPlaced) {
            cart.clear();
            response.sendRedirect("orderConfirmation.jsp");
        }
        else {
            response.sendRedirect("checkout?error=Something went wrong.");
        }

    }
}
