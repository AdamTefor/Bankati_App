package ma.banque.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Filtre qui vérifie si l'utilisateur est connecté avant d'accéder aux pages protégées.
 */
@WebFilter("/*")
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        String uri = req.getRequestURI();
        HttpSession session = req.getSession(false);

        boolean estConnecte = (session != null && session.getAttribute("user") != null);
        boolean pageLibre = uri.contains("login") || uri.contains("css") || uri.contains("js") || uri.contains("images");

        if (estConnecte || pageLibre) {
            chain.doFilter(request, response);
        } else {
            req.getRequestDispatcher("/views/login.jsp").forward(request, response);
        }
    }
}
