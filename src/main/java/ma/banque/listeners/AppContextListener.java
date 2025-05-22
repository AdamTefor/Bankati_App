package ma.banque.listeners;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class AppContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("🚀 L'application Bankati est démarrée !");
        // Ici on peut ajouter de l'initialisation comme charger des taux depuis un fichier
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("🛑 L'application Bankati est arrêtée.");
    }
}
