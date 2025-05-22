package ma.banque.models;

/**
 * Représente une demande de crédit faite par un client.
 */
public class DemandeCredit {
    private String id;
    private String idClient;
    private double montant;
    private String statut; // EN_ATTENTE, APPROUVÉE, REFUSÉE

    public DemandeCredit(String id, String idClient, double montant, String statut) {
        this.id = id;
        this.idClient = idClient;
        this.montant = montant;
        this.statut = statut;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdClient() {
        return idClient;
    }

    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
}
