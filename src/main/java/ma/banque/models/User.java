package ma.banque.models;

import java.io.Serializable;

/**
 * Représente un utilisateur (admin ou client) du système.
 */
public class User implements Serializable {

    private String id;
    private String nom;
    private String email;
    private String motDePasse;
    private String role;

    // 🔧 Constructeur par défaut (requis pour JSP/EL)
    public User() {}

    // ✅ Constructeur complet
    public User(String id, String nom, String email, String motDePasse, String role) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.motDePasse = motDePasse;
        this.role = role;
    }

    // 🟢 Getters et Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // 🔎 Pour affichage console (debug)
    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", nom='" + nom + '\'' +
                ", email='" + email + '\'' +
                ", motDePasse='" + motDePasse + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
