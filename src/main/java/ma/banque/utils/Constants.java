package ma.banque.utils;

/**
 * Classe de constantes générales utilisées dans l'application Bankati.
 */
public class Constants {

    // Rôles
    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_CLIENT = "CLIENT";

    // Statuts de demande
    public static final String STATUT_EN_ATTENTE = "EN_ATTENTE";
    public static final String STATUT_APPROUVEE = "APPROUVÉE";
    public static final String STATUT_REFUSEE = "REFUSÉE";

    // Chemins des fichiers (file base)
    public static final String USERS_FILE = "data/users.txt";
    public static final String COMPTES_FILE = "data/comptes.txt";
    public static final String DEMANDES_FILE = "data/demandes.txt";

    // Attributs de session
    public static final String SESSION_USER = "user";

    // Devises
    public static final String DEVISE_DH = "DH";
    public static final String DEVISE_USD = "USD";
    public static final String DEVISE_EUR = "EUR";
    public static final String DEVISE_GBP = "GBP";

    // Nom de l'application
    public static final String APP_NAME = "Bankati";

    private Constants() {
        // Empêche l’instanciation de cette classe utilitaire
    }
}
