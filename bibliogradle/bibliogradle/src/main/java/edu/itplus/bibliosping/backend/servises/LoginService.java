package edu.itplus.bibliosping.backend.servises;

import edu.itplus.bibliosping.backend.model.User;

public interface LoginService {
    boolean login(User user);
    void register(User user);
}
