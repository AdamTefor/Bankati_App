package ma.banque.dao;

import ma.banque.models.Compte;
import ma.banque.utils.Database;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class CompteDao {

    public Map<String, Compte> getTousLesComptes() {
        Map<String, Compte> comptes = new HashMap<>();
        String sql = "SELECT * FROM comptes";

        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                comptes.put(
                        rs.getString("id_client"),
                        new Compte(
                                rs.getString("numero"),
                                rs.getDouble("solde"),
                                rs.getString("devise")
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return comptes;
    }

    public Compte getCompteParClient(String idClient) {
        String sql = "SELECT * FROM comptes WHERE id_client = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idClient);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Compte(
                        rs.getString("numero"),
                        rs.getDouble("solde"),
                        rs.getString("devise")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void ajouterOuMettreAJourCompte(String idClient, String numero, double solde, String devise) {
        String sql = "REPLACE INTO comptes (id_client, numero, solde, devise) VALUES (?, ?, ?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idClient);
            stmt.setString(2, numero);
            stmt.setDouble(3, solde);
            stmt.setString(4, devise);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean mettreAJourSolde(String idClient, double nouveauSolde) {
        String sql = "UPDATE comptes SET solde = ? WHERE id_client = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, nouveauSolde);
            stmt.setString(2, idClient);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public void creerComptePourClient(String idClient) {
        ajouterOuMettreAJourCompte(idClient, "CPT-" + idClient, 0.0, "DH");
    }
}
