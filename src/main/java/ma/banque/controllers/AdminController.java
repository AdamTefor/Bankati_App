package ma.banque.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import ma.banque.models.User;
import ma.banque.services.CompteService;
import ma.banque.services.UserService;
import ma.banque.utils.Constants;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "AdminController", urlPatterns = {
        "/admin/dashboard",
        "/admin/users",
        "/admin/user/add",
        "/admin/user/edit",
        "/admin/user/save",
        "/admin/user/delete",
        "/admin/compte/form",
        "/admin/compte/save"
})
public class AdminController extends HttpServlet {

    private UserService userService;
    private CompteService compteService;

    @Override
    public void init() {
        userService = new UserService();
        compteService = new CompteService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        User admin = (session != null) ? (User) session.getAttribute(Constants.SESSION_USER) : null;

        if (admin == null || !Constants.ROLE_ADMIN.equals(admin.getRole())) {
            System.out.println("🔒 Accès refusé : non connecté ou non admin");
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String action = request.getServletPath();
        System.out.println("📥 GET reçu : " + action);

        switch (action) {
            case "/admin/dashboard":
                request.setAttribute("user", admin);
                request.getRequestDispatcher("/views/admin/dashboard.jsp").forward(request, response);
                break;

            case "/admin/users":
                List<User> utilisateurs = userService.listerTousLesUtilisateurs();
                request.setAttribute("utilisateurs", utilisateurs);
                if (request.getParameter("message") != null) {
                    request.setAttribute("message", request.getParameter("message"));
                }
                request.getRequestDispatcher("/views/admin/users.jsp").forward(request, response);
                break;

            case "/admin/user/add":
                request.setAttribute("mode", "ajout");
                request.getRequestDispatcher("/views/admin/user_form.jsp").forward(request, response);
                break;

            case "/admin/user/edit":
                String idEdit = request.getParameter("id");
                User userEdit = userService.chercherParId(idEdit);
                request.setAttribute("utilisateur", userEdit);
                request.setAttribute("mode", "edition");
                request.getRequestDispatcher("/views/admin/user_form.jsp").forward(request, response);
                break;

            case "/admin/user/delete":
                String idDelete = request.getParameter("id");
                userService.supprimerUtilisateur(idDelete);
                response.sendRedirect(request.getContextPath() + "/admin/users?message=Utilisateur supprimé");
                break;

            case "/admin/compte/form":
                request.getRequestDispatcher("/views/admin/compte_form.jsp").forward(request, response);
                break;

            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Action non reconnue");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        User admin = (session != null) ? (User) session.getAttribute(Constants.SESSION_USER) : null;

        if (admin == null || !Constants.ROLE_ADMIN.equals(admin.getRole())) {
            System.out.println("🔐 POST refusé : utilisateur non connecté");
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String action = request.getServletPath();
        System.out.println("📥 POST reçu : " + action);

        switch (action) {

            case "/admin/user/save":
                String id = request.getParameter("id");
                String nom = request.getParameter("nom");
                String email = request.getParameter("email");
                String motDePasse = request.getParameter("motDePasse");
                String role = request.getParameter("role");

                if (nom == null || email == null || motDePasse == null || role == null ||
                        nom.isBlank() || email.isBlank() || motDePasse.isBlank() || role.isBlank()) {
                    response.sendRedirect(request.getContextPath() + "/admin/users?message=Champs manquants");
                    return;
                }

                User utilisateur = new User(
                        (id == null || id.isEmpty()) ? UUID.randomUUID().toString() : id,
                        nom.trim(),
                        email.trim(),
                        motDePasse.trim(),
                        role.trim().toUpperCase()
                );

                if (id == null || id.isEmpty()) {
                    System.out.println("➕ Ajout utilisateur : " + utilisateur.getEmail());
                    userService.ajouterUtilisateurAvecCompte(utilisateur);
                    response.sendRedirect(request.getContextPath() + "/admin/users?message=Ajout+réussi");
                } else {
                    System.out.println("✏️ Modification utilisateur : " + utilisateur.getEmail());
                    userService.modifierUtilisateur(utilisateur);
                    response.sendRedirect(request.getContextPath() + "/admin/users?message=Modification+réussie");
                }
                break;

            case "/admin/compte/save":
                String idClient = request.getParameter("idClient");
                String numero = request.getParameter("numero");
                double solde = Double.parseDouble(request.getParameter("solde"));
                String devise = request.getParameter("devise");

                System.out.println("🏦 Création compte pour " + idClient);
                compteService.creerCompte(idClient, numero, solde, devise);
                response.sendRedirect(request.getContextPath() + "/admin/dashboard");
                break;

            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Action POST non reconnue");
        }
    }
}
