package ma.banque.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import ma.banque.models.DemandeCredit;
import ma.banque.models.User;
import ma.banque.services.CreditService;
import ma.banque.utils.Constants;
import ma.banque.utils.PDFExporter;

import java.io.IOException;
import java.util.List;

/**
 * Contrôleur pour exporter les demandes de crédit en format PDF.
 */
@WebServlet(name = "ExportDemandePDFController", urlPatterns = {"/credit/export/pdf"})
public class ExportDemandePDFController extends HttpServlet {

    private CreditService creditService;

    @Override
    public void init() throws ServletException {
        creditService = new CreditService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        User user = (session != null) ? (User) session.getAttribute(Constants.SESSION_USER) : null;

        // Sécurité : uniquement accessible par les clients connectés
        if (user == null || !Constants.ROLE_CLIENT.equals(user.getRole())) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // Récupérer les demandes du client connecté
        List<DemandeCredit> demandes = creditService.getDemandesParClient(user.getId());

        // Configuration de la réponse HTTP pour téléchargement PDF
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=demandes_credit.pdf");

        try {
            PDFExporter.exporterDemandesVersPDF(demandes, response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("erreur", "Erreur lors de l'export PDF : " + e.getMessage());
            request.setAttribute("demandes", demandes);
            request.getRequestDispatcher("/views/client/demandes.jsp").forward(request, response);
        }
    }
}
