package ma.banque.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.time.LocalDateTime;

@WebFilter("/*")
public class RequestLoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        String chemin = req.getRequestURI();
        String ip = request.getRemoteAddr();

        System.out.println("📥 " + LocalDateTime.now() + " - Requête de " + ip + " vers " + chemin);

        chain.doFilter(request, response);
    }
}
