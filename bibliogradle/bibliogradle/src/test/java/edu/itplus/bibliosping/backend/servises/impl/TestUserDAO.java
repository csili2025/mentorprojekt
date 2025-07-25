package edu.itplus.bibliosping.backend.servises.impl;

import edu.itplus.bibliosping.backend.model.User;
import edu.itplus.bibliosping.backend.repository.UserDAO;

import java.util.List;

public class TestUserDAO implements UserDAO {
    private User user;
    public TestUserDAO() {
        this.user = new User();
        user.setPassword("123");
        user.setUsername("Psitike");
        user.setId(1L);
        user.setUuid("salt");
    }

    @Override
    public User findByID(Long id) {
        if (user.getId().equals(id)){
            return user;
        }else {
            return null;
        }
    }

    @Override
    public User findbyUsername(String username) {
        if (user.getUsername().equals(username)){
            return user;
        }else {
            return null;
        }
    }

    @Override
    public User create(User user) {
        return null;
    }

    @Override
    public void update(User user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(User user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<User> findAll() {
        return List.of(new User[]{user});
    }
}
