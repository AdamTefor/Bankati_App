package ma.banque.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import ma.banque.models.DemandeCredit;
import ma.banque.models.User;
import ma.banque.services.CreditService;
import ma.banque.utils.Constants;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "CreditController", urlPatterns = {
        "/credit/list",
        "/credit/add",
        "/credit/delete",
        "/credit/approve",
        "/credit/refuse"
})
public class CreditController extends HttpServlet {

    private CreditService creditService;

    @Override
    public void init() {
        creditService = new CreditService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        User user = (session != null) ? (User) session.getAttribute(Constants.SESSION_USER) : null;

        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String action = request.getServletPath();

        switch (action) {
            case "/credit/list":
                if (Constants.ROLE_CLIENT.equals(user.getRole())) {
                    List<DemandeCredit> demandesClient = creditService.getDemandesParClient(user.getId());
                    request.setAttribute("demandes", demandesClient);
                    request.getRequestDispatcher("/views/client/demandes.jsp").forward(request, response);
                } else if (Constants.ROLE_ADMIN.equals(user.getRole())) {
                    List<DemandeCredit> toutes = creditService.getToutesLesDemandes();
                    request.setAttribute("demandes", toutes);
                    request.getRequestDispatcher("/views/admin/demandes.jsp").forward(request, response);
                }
                break;

            case "/credit/add":
                if (Constants.ROLE_CLIENT.equals(user.getRole())) {
                    request.getRequestDispatcher("/views/client/demande_form.jsp").forward(request, response);
                } else {
                    response.sendRedirect(request.getContextPath() + "/login");
                }
                break;

            case "/credit/delete":
                if (Constants.ROLE_CLIENT.equals(user.getRole())) {
                    String idSupp = request.getParameter("id");
                    creditService.supprimerDemande(idSupp);
                    response.sendRedirect(request.getContextPath() + "/credit/list");
                } else {
                    response.sendRedirect(request.getContextPath() + "/login");
                }
                break;

            case "/credit/approve":
                if (Constants.ROLE_ADMIN.equals(user.getRole())) {
                    creditService.approuverDemande(request.getParameter("id"));
                    response.sendRedirect(request.getContextPath() + "/credit/list");
                } else {
                    response.sendRedirect(request.getContextPath() + "/login");
                }
                break;

            case "/credit/refuse":
                if (Constants.ROLE_ADMIN.equals(user.getRole())) {
                    creditService.refuserDemande(request.getParameter("id"));
                    response.sendRedirect(request.getContextPath() + "/credit/list");
                } else {
                    response.sendRedirect(request.getContextPath() + "/login");
                }
                break;

            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Action non reconnue.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        User user = (session != null) ? (User) session.getAttribute(Constants.SESSION_USER) : null;

        if (user == null || !Constants.ROLE_CLIENT.equals(user.getRole())) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        try {
            double montant = Double.parseDouble(request.getParameter("montant"));
            String id = UUID.randomUUID().toString();

            DemandeCredit demande = new DemandeCredit(
                    id,
                    user.getId(),
                    montant,
                    Constants.STATUT_EN_ATTENTE
            );

            creditService.ajouterDemande(demande);
            response.sendRedirect(request.getContextPath() + "/credit/list");

        } catch (NumberFormatException e) {
            request.setAttribute("erreur", "Montant invalide.");
            request.getRequestDispatcher("/views/client/demande_form.jsp").forward(request, response);
        }
    }
}
