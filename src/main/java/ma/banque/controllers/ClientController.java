package ma.banque.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import ma.banque.models.Compte;
import ma.banque.models.User;
import ma.banque.services.CompteService;
import ma.banque.utils.Constants;

import java.io.IOException;
import java.util.Map;

@WebServlet(name = "ClientController", urlPatterns = {"/client/dashboard"})
public class ClientController extends HttpServlet {

    private CompteService compteService;

    @Override
    public void init() {
        compteService = new CompteService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Vérifie la session et le rôle
        HttpSession session = request.getSession(false);
        User client = (session != null) ? (User) session.getAttribute(Constants.SESSION_USER) : null;

        if (client == null || !Constants.ROLE_CLIENT.equals(client.getRole())) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // Récupération du compte du client
        Compte compte = compteService.getCompteParClient(client.getId());

        // Récupération des taux de change simulés (à définir dans le service si pas encore fait)
        Map<String, Double> taux = compteService.getTauxDisponibles();

        // Envoi des données à la vue
        request.setAttribute("client", client);
        request.setAttribute("compte", compte);
        request.setAttribute("taux", taux);

        // Affichage du dashboard client
        request.getRequestDispatcher("/views/client/dashboard.jsp").forward(request, response);
    }
}
