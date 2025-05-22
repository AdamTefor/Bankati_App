package ma.banque.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import ma.banque.models.User;
import ma.banque.services.UserService;
import ma.banque.utils.Constants;

import java.io.IOException;

/**
 * Contrôleur chargé de gérer la connexion des utilisateurs (admin ou client).
 */
@WebServlet(name = "LoginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {

    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Vérifie si l'utilisateur est déjà connecté
        HttpSession session = request.getSession(false);
        User utilisateur = (session != null) ? (User) session.getAttribute(Constants.SESSION_USER) : null;

        if (utilisateur != null) {
            switch (utilisateur.getRole()) {
                case Constants.ROLE_ADMIN:
                    response.sendRedirect(request.getContextPath() + "/admin/dashboard");
                    return;
                case Constants.ROLE_CLIENT:
                    response.sendRedirect(request.getContextPath() + "/client/dashboard");
                    return;
                default:
                    session.invalidate();
            }
        }

        // Sinon, afficher la page de login
        request.getRequestDispatcher("/views/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Récupère les champs du formulaire
        String email = request.getParameter("email");
        String motDePasse = request.getParameter("motDePasse");

        // Vérifie que les champs ne sont pas vides
        if (email == null || email.isBlank() || motDePasse == null || motDePasse.isBlank()) {
            request.setAttribute("erreur", "Veuillez remplir tous les champs.");
            request.getRequestDispatcher("/views/login.jsp").forward(request, response);
            return;
        }

        // Authentification via UserService
        User utilisateur = userService.authentifier(email.trim(), motDePasse.trim());

        if (utilisateur != null) {
            // Création d'une nouvelle session
            HttpSession session = request.getSession(true);
            session.setAttribute(Constants.SESSION_USER, utilisateur);

            // Redirection selon le rôle
            switch (utilisateur.getRole()) {
                case Constants.ROLE_ADMIN:
                    response.sendRedirect(request.getContextPath() + "/admin/dashboard");
                    break;
                case Constants.ROLE_CLIENT:
                    response.sendRedirect(request.getContextPath() + "/client/dashboard");
                    break;
                default:
                    session.invalidate();
                    request.setAttribute("erreur", "Rôle inconnu. Contactez un administrateur.");
                    request.getRequestDispatcher("/views/login.jsp").forward(request, response);
            }

        } else {
            // Authentification échouée
            request.setAttribute("erreur", "Email ou mot de passe incorrect.");
            request.getRequestDispatcher("/views/login.jsp").forward(request, response);
        }
    }
}
