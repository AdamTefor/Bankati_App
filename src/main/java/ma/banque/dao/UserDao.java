package ma.banque.dao;

import ma.banque.models.User;
import ma.banque.utils.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    public void ajouterUser(User user) {
        String sql = "INSERT INTO users (id, nom, email, mot_de_passe, role) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getId());
            stmt.setString(2, user.getNom());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getMotDePasse());
            stmt.setString(5, user.getRole());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Utilisateur inséré : " + user.getEmail());
            } else {
                System.err.println("⚠️ Insertion échouée !");
            }

        } catch (SQLException e) {
            System.err.println("❌ ERREUR INSERT USER : " + e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";

        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                users.add(new User(
                        rs.getString("id"),
                        rs.getString("nom"),
                        rs.getString("email"),
                        rs.getString("mot_de_passe"),
                        rs.getString("role")
                ));
            }

        } catch (SQLException e) {
            System.err.println("❌ ERREUR getAllUsers : " + e.getMessage());
        }

        return users;
    }

    public User getByEmailAndPassword(String email, String password) {
        String sql = "SELECT * FROM users WHERE email = ? AND mot_de_passe = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getString("id"),
                        rs.getString("nom"),
                        rs.getString("email"),
                        rs.getString("mot_de_passe"),
                        rs.getString("role")
                );
            }

        } catch (SQLException e) {
            System.err.println("❌ ERREUR getByEmailAndPassword : " + e.getMessage());
        }

        return null;
    }

    public boolean supprimerUser(String id) {
        String sql = "DELETE FROM users WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("❌ ERREUR suppression user : " + e.getMessage());
        }

        return false;
    }

    public boolean modifierUser(User user) {
        String sql = "UPDATE users SET nom = ?, email = ?, mot_de_passe = ?, role = ? WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getNom());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getMotDePasse());
            stmt.setString(4, user.getRole());
            stmt.setString(5, user.getId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("❌ ERREUR modification user : " + e.getMessage());
        }

        return false;
    }

    public User getById(String id) {
        String sql = "SELECT * FROM users WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new User(
                        rs.getString("id"),
                        rs.getString("nom"),
                        rs.getString("email"),
                        rs.getString("mot_de_passe"),
                        rs.getString("role")
                );
            }

        } catch (SQLException e) {
            System.err.println("❌ ERREUR getById : " + e.getMessage());
        }

        return null;
    }
}
