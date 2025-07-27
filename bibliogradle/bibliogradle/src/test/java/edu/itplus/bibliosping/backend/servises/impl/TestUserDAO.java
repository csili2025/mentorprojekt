package edu.itplus.bibliosping.backend.servises.impl;

import edu.itplus.bibliosping.backend.model.User;
import edu.itplus.bibliosping.backend.repository.UserDAO;

import java.util.List;

public class TestUserDAO implements UserDAO {
    public static User nonDbUser;

    public static User dbuser;

    private String lastSerachUser;
    public String getLastSerachUser() {
        return lastSerachUser;
    }

    public TestUserDAO() {
        this.nonDbUser = new User();
        nonDbUser.setPassword("123");
        nonDbUser.setUsername("Psitike");
        nonDbUser.setId(1L);
        nonDbUser.setUuid("salt");

        dbuser = new User();
        dbuser.setPassword(TestPasswordDAO.hashedPassword);
        dbuser.setUsername("Psitike");
        dbuser.setId(1L);
        dbuser.setUuid(TestPasswordDAO.salt);
    }

    @Override
    public User findByID(Long id) {
        if (nonDbUser.getId().equals(id)){
            return dbuser;
        }else {
            return null;
        }
    }

    @Override
    public User findbyUsername(String username) {
        lastSerachUser = username;
        if (nonDbUser.getUsername().equals(username)){
            return dbuser;
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
        return List.of(new User[]{dbuser});
    }
}
