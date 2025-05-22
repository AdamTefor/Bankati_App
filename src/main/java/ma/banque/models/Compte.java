package ma.banque.models;

import jakarta.persistence.Entity;

/**
 * Représente un compte bancaire.
 */
public class Compte {
    private String numero;
    private double solde;
    private String devise; // Exemple : "DH", "EUR", "USD", "GBP"

    public Compte(String numero, double solde, String devise) {
        this.numero = numero;
        this.solde = solde;
        this.devise = devise;
    }
public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public String getDevise() {
        return devise;
    }

    public void setDevise(String devise) {
        this.devise = devise;
    }
}
