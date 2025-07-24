package edu.itplus.bibliosping.backend.servises.impl;

import edu.itplus.bibliosping.backend.model.User;
import edu.itplus.bibliosping.backend.repository.UserDAO;
import edu.itplus.bibliosping.backend.servises.LoginService;
import edu.itplus.bibliosping.backend.utils.PasswordHasher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private PasswordHasher passwordHasher;

    @Override
    public boolean login(User user) {

        User dbUser = userDAO.findbyUsername(user.getUsername());
        if (dbUser == null) {
            return false;
        } else if (dbUser.getPassword().equals(passwordHasher.hashPassword(user.getPassword(),user.getUuid()))) {
            return true;
        }
        String hashedInputPassword = passwordHasher.hashPassword(user.getPassword(), dbUser.getUuid());

        if (dbUser.getPassword().equals(hashedInputPassword)) {
            System.out.println("Sikeres bejelentkezés: " + user.getUsername());
            return true;
        } else {
            System.out.println("Helytelen jelszó a felhasználó számára: " + user.getUsername());
            return false;
        }
    }

    @Override
    public void register(User user) {
        user.setPassword(passwordHasher.hashPassword(user.getPassword(),user.getUuid()));
        userDAO.create(user);
    }
}
