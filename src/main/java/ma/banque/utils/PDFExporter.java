package ma.banque.utils;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.*;
import ma.banque.models.DemandeCredit;

import java.awt.*;
import java.io.OutputStream;
import java.util.List;

/**
 * Classe utilitaire pour générer un PDF à partir d'une liste de demandes de crédit.
 */
public class PDFExporter {

    public static void exporterDemandesVersPDF(List<DemandeCredit> demandes, OutputStream out) throws Exception {
        Document document = new Document();
        PdfWriter.getInstance(document, out);
        document.open();

        // Titre
        Font titreFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
        Paragraph titre = new Paragraph("Liste des demandes de crédit", titreFont);
        titre.setAlignment(Element.ALIGN_CENTER);
        titre.setSpacingAfter(20);
        document.add(titre);

        // Tableau
        PdfPTable table = new PdfPTable(4); // ID, Client, Montant, Statut
        table.setWidthPercentage(100);
        table.setSpacingBefore(10);

        ajouterCelluleEnTete(table, "ID");
        ajouterCelluleEnTete(table, "Client");
        ajouterCelluleEnTete(table, "Montant");
        ajouterCelluleEnTete(table, "Statut");

        for (DemandeCredit d : demandes) {
            table.addCell(d.getId());
            table.addCell(d.getIdClient());
            table.addCell(String.valueOf(d.getMontant()));
            table.addCell(d.getStatut());
        }

        document.add(table);
        document.close();
    }

    private static void ajouterCelluleEnTete(PdfPTable table, String texte) {
        PdfPCell cellule = new PdfPCell(new Phrase(texte));
        cellule.setBackgroundColor(Color.LIGHT_GRAY);
        cellule.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cellule);
    }
}
