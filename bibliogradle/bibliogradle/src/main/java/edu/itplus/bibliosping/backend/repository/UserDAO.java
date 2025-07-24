package edu.itplus.bibliosping.backend.repository;

import edu.itplus.bibliosping.backend.model.User;

import java.util.List;

public interface UserDAO {
     User findByID(Long id);
     User findbyUsername(String username);
     User create(User user);
     void update(User user);
     void delete(User user);
     List<User> findAll();
}
