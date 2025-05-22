package ma.banque.services;

import ma.banque.dao.UserDao;
import ma.banque.models.User;

import java.util.List;

public class UserService {

    private final UserDao userDao = new UserDao();

    public User authentifier(String email, String motDePasse) {
        return userDao.getByEmailAndPassword(email, motDePasse);
    }

    public List<User> listerTousLesUtilisateurs() {
        return userDao.getAllUsers();
    }

    public User chercherParId(String id) {
        return userDao.getById(id);
    }

    public void ajouterUtilisateur(User user) {
        userDao.ajouterUser(user);
    }

    public boolean modifierUtilisateur(User user) {
        return userDao.modifierUser(user);
    }

    public boolean supprimerUtilisateur(String id) {
        return userDao.supprimerUser(id);
    }

    public void ajouterUtilisateurAvecCompte(User user) {
        userDao.ajouterUser(user);
        new ma.banque.dao.CompteDao().creerComptePourClient(user.getId());
    }
}
