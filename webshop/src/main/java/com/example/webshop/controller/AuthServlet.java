package com.example.webshop.controller;

import com.example.webshop.model.User;
import com.example.webshop.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/auth")
public class AuthServlet extends HttpServlet {
    private UserService userService;
    @Override
    public void init(){
        userService = new UserService();
    }
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = userService.authenticateUser(username, password);
        if(user != null){
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            resp.sendRedirect("WEB-INF/views/home.jsp");
        }else{
            req.setAttribute("errorMessage", "Invalid username or password");
            req.getRequestDispatcher("WEB-INF/views/login.jsp").forward(req, resp);
        }
    }
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if(session != null){
            session.invalidate();
        }
        resp.sendRedirect("/login.jsp?message=You logged out");
    }
}
