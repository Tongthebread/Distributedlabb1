package com.example.webshop.controller;
import com.example.webshop.model.User;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebFilter("/*")
public class AuthorizationFilter implements Filter {

    // Mappning av URL-prefix till krävs roll
    private static final Map<String, String> protectedUrls = new HashMap<>();

    static {
        // Lägg till URL-prefix och motsvarande roll som krävs
        protectedUrls.put("/admin", "admin");
        protectedUrls.put("/orders", "staff");
        // Lägg till fler skyddade URL:er vid behov
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Hämta den begärda URL:en
        String requestURI = httpRequest.getRequestURI();
        String contextPath = httpRequest.getContextPath();
        String path = requestURI.substring(contextPath.length());

        // Kontrollera om URL:en kräver en specifik roll
        for (Map.Entry<String, String> entry : protectedUrls.entrySet()) {
            String urlPrefix = entry.getKey();
            String requiredRole = entry.getValue();

            if (path.startsWith(urlPrefix)) {
                // Kontrollera användarens roll
                HttpSession session = httpRequest.getSession(false);
                User user = (session != null) ? (User) session.getAttribute("user") : null;

                if (user == null || !requiredRole.equalsIgnoreCase(String.valueOf(user.getRole()))) {
                    // Användaren har inte rätt roll, visa felmeddelande eller omdirigera
                    httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "Denied");
                    return;
                }
                break; // Rollen är kontrollerad, fortsätt med nästa kontroll
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
