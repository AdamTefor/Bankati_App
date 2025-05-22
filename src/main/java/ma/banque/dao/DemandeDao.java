package ma.banque.dao;

import ma.banque.models.DemandeCredit;
import ma.banque.utils.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DemandeDao {

    public void ajouterDemande(DemandeCredit demande) {
        String sql = "INSERT INTO demandes (id, id_client, montant, statut) VALUES (?, ?, ?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, demande.getId());
            stmt.setString(2, demande.getIdClient());
            stmt.setDouble(3, demande.getMontant());
            stmt.setString(4, demande.getStatut());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<DemandeCredit> getDemandesParClient(String idClient) {
        List<DemandeCredit> demandes = new ArrayList<>();
        String sql = "SELECT * FROM demandes WHERE id_client = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idClient);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                demandes.add(new DemandeCredit(
                        rs.getString("id"),
                        rs.getString("id_client"),
                        rs.getDouble("montant"),
                        rs.getString("statut")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return demandes;
    }

    public List<DemandeCredit> getToutesLesDemandes() {
        List<DemandeCredit> demandes = new ArrayList<>();
        String sql = "SELECT * FROM demandes";

        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                demandes.add(new DemandeCredit(
                        rs.getString("id"),
                        rs.getString("id_client"),
                        rs.getDouble("montant"),
                        rs.getString("statut")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return demandes;
    }

    public boolean supprimerDemande(String id) {
        String sql = "DELETE FROM demandes WHERE id = ? AND statut = 'EN_ATTENTE'";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean changerStatut(String id, String nouveauStatut) {
        String sql = "UPDATE demandes SET statut = ? WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nouveauStatut);
            stmt.setString(2, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
