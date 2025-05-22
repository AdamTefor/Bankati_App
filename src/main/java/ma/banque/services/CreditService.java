package ma.banque.services;

import ma.banque.dao.DemandeDao;
import ma.banque.models.DemandeCredit;

import java.util.List;

public class CreditService {

    private final DemandeDao demandeDao = new DemandeDao();

    public void ajouterDemande(DemandeCredit demande) {
        demandeDao.ajouterDemande(demande);
    }

    public List<DemandeCredit> getDemandesParClient(String idClient) {
        return demandeDao.getDemandesParClient(idClient);
    }

    public List<DemandeCredit> getToutesLesDemandes() {
        return demandeDao.getToutesLesDemandes();
    }

    public boolean supprimerDemande(String idDemande) {
        return demandeDao.supprimerDemande(idDemande);
    }

    public boolean approuverDemande(String idDemande) {
        return demandeDao.changerStatut(idDemande, "APPROUVÉE");
    }

    public boolean refuserDemande(String idDemande) {
        return demandeDao.changerStatut(idDemande, "REFUSÉE");
    }
}
