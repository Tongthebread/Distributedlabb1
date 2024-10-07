package com.example.webshop.controller;

import com.example.webshop.model.User;
import com.example.webshop.model.User;
import com.example.webshop.service.UserService;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {

    // Lista över sidor som inte kräver autentisering
    private static final List<String> excludedUrls = Arrays.asList(
            "/index.jsp",
            "/login.jsp",
            "/register.jsp",
            "/productDetails.jsp",
            "/auth",
            "/register",
            "/resources/", // Exempel på statiska resurser
            "/products" // Om du vill tillåta visning av produkter utan inloggning
    );

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initiering om nödvändigt
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Hämta den begärda URL:en
        String requestURI = httpRequest.getRequestURI();
        String contextPath = httpRequest.getContextPath();
        String path = requestURI.substring(contextPath.length());

        // Kontrollera om URL:en är exkluderad från autentisering
        boolean isExcluded = excludedUrls.stream().anyMatch(path::startsWith);

        if (isExcluded) {
            chain.doFilter(request, response); // Fortsätt utan autentisering
            return;
        }

        // Kontrollera om användaren är inloggad
        HttpSession session = httpRequest.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("user") : null;

        if (user == null) {
            // Användaren är inte inloggad, omdirigera till inloggningssidan
            httpResponse.sendRedirect(contextPath + "/login.jsp?message=Logintocontinue");
        } else {
            // Användaren är inloggad, fortsätt till den begärda resursen
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        // Rensa resurser om nödvändigt
    }
}
