package ma.banque.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import ma.banque.models.User;
import ma.banque.utils.Constants;

import java.io.IOException;

@WebFilter("/*")
public class AuthorizationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession(false);
        User utilisateur = (session != null) ? (User) session.getAttribute(Constants.SESSION_USER) : null;
        String uri = req.getRequestURI();

        boolean estAdmin = utilisateur != null && Constants.ROLE_ADMIN.equals(utilisateur.getRole());
        boolean estClient = utilisateur != null && Constants.ROLE_CLIENT.equals(utilisateur.getRole());

        // Autorisations par rôle
        if (uri.contains("/admin/") && !estAdmin) {
            req.getRequestDispatcher("/views/errors/403.jsp").forward(request, response);
            return;
        }

        if (uri.contains("/client/") && !estClient) {
            req.getRequestDispatcher("/views/errors/403.jsp").forward(request, response);
            return;
        }

        chain.doFilter(request, response);
    }
}
