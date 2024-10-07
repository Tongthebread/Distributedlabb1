package com.example.webshop.controller;

import com.example.webshop.DTOS.ProductDTO;
import com.example.webshop.DTOS.UserDTO;
import com.example.webshop.model.Product;
import com.example.webshop.model.User;
import com.example.webshop.service.ProductService;
import com.example.webshop.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    private ProductService productService;
    @Override
    public void init() throws ServletException {
        productService = new ProductService();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user == null || !"admin".equalsIgnoreCase(String.valueOf(user.getRole()))) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Åtkomst nekad");
            return;
        }
        ArrayList<ProductDTO> products = new ArrayList<>();
        try {
            products = productService.getProducts();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("products", products);
        req.getRequestDispatcher("/admin.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Kontrollera om användaren är admin
        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("user");

        if (user == null || !"admin".equalsIgnoreCase(String.valueOf(user.getRole()))) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Åtkomst nekad");
            return;
        }

        String action = request.getParameter("action");

        if ("add".equals(action)) {
            // Lägg till ny produkt
            String name = request.getParameter("name");
            double price = Double.parseDouble(request.getParameter("price"));
            int stock = Integer.parseInt(request.getParameter("stock"));
            int categoryId = Integer.parseInt(request.getParameter("categoryId"));

            ProductDTO product = new ProductDTO();
            product.setName(name);
            product.setPrice(price);
            product.setStock(stock);
            product.setCategoryId(categoryId);

            try {
                productService.addProduct(product);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        } else if ("update".equals(action)) {
            // Uppdatera befintlig produkt
            int productId = Integer.parseInt(request.getParameter("productId"));
            String name = request.getParameter("name");
            double price = Double.parseDouble(request.getParameter("price"));
            int stock = Integer.parseInt(request.getParameter("stock"));
            int categoryId = Integer.parseInt(request.getParameter("categoryId"));

            Product product = new Product();
            product.setId(productId);
            product.setName(name);
            product.setPrice(price);
            product.setStock(stock);
            product.setCategoryId(categoryId);

            try {
                productService.updateProduct(product);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else if ("delete".equals(action)) {
            int productId = Integer.parseInt(request.getParameter("productId"));
            try {
                productService.deleteProduct(productId);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        response.sendRedirect("admin");
    }
}
