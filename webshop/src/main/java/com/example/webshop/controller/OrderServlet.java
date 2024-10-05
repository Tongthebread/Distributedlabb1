package com.example.webshop.controller;
import com.example.webshop.model.Order;
import com.example.webshop.model.Status;
import com.example.webshop.model.User;
import com.example.webshop.service.OrderService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
@WebServlet("/orders")
public class OrderServlet extends HttpServlet {
    private OrderService orderService;
    @Override
    public void init() throws ServletException {
        OrderService orderService = new OrderService();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null||"staff".equalsIgnoreCase(String.valueOf(user.getRole()))) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Denied");
        }
        ArrayList<Order> orders = new ArrayList<>();
        req.setAttribute("orders", orders);
        req.getRequestDispatcher("WEB-INF/views/orders.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user==null||"staff".equalsIgnoreCase(String.valueOf(user.getRole()))) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Denied");
        }
        int orderId = Integer.parseInt(req.getParameter("orderId"));
        String status = req.getParameter("status");
        try {
            orderService.updateOrderStatus(orderId, Status.valueOf(status));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        resp.sendRedirect("orders");

    }
}
