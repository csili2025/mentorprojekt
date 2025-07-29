package edu.itplus.bibliosping.backend.repository;

import edu.itplus.bibliosping.backend.model.User;

import java.util.List;

public interface UserDAO extends BaseDAO<User,Long > {
     User findbyUsername(String username);
}
