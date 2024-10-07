package com.example.webshop.controller;
import com.example.webshop.DTOS.UserDTO;
import com.example.webshop.model.User;
import com.example.webshop.service.UserService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private UserService userService;
    public void init() throws ServletException {
        userService = new UserService();
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserDTO user = new UserDTO();
        user.setUsername(username);
        user.setPassword(password);
        Boolean registrationSuccess = userService.RegisterUser(user);
        if (registrationSuccess) {
            response.sendRedirect("login.jsp?message=Registrering lyckades, vänligen logga in.");
        } else {
            request.setAttribute("errorMessage", "Användarnamnet är redan upptaget eller ett fel inträffade.");
            request.getRequestDispatcher("WEB-INF/views/register.jsp").forward(request, response);
        }

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/views/register.jsp").forward(request, response);
    }
}
