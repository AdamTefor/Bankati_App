package ma.banque.services;

import ma.banque.dao.CompteDao;
import ma.banque.models.Compte;

import java.util.HashMap;
import java.util.Map;

public class CompteService {

    private final CompteDao compteDao = new CompteDao();

    public Compte getCompteParClient(String idClient) {
        return compteDao.getCompteParClient(idClient);
    }

    public void mettreAJourSolde(String idClient, double montant) {
        compteDao.mettreAJourSolde(idClient, montant);
    }

    public void creerCompte(String idClient, String numero, double solde, String devise) {
        compteDao.ajouterOuMettreAJourCompte(idClient, numero, solde, devise);
    }
    public Map<String, Double> getTauxDisponibles() {
        Map<String, Double> taux = new HashMap<>();
        taux.put("DH", 1.0);
        taux.put("EUR", 0.091);
        taux.put("USD", 0.10);
        taux.put("GBP", 0.078);
        return taux;
    }

}
