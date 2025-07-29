package edu.itplus.bibliosping.backend.repository.jpa;

import edu.itplus.bibliosping.backend.model.User;
import edu.itplus.bibliosping.backend.repository.UserDAO;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("JPA")
public class JPAUserDAO extends BaseDAOBean<User,Long> implements UserDAO {

    @Autowired
    private Logger log;

    public JPAUserDAO() {
        super(User.class);
    }

    @Override
    public User findbyUsername(String username) {
        try{
            TypedQuery<User> query = entityManager.createQuery("From User u where u.username = :uservalue", User.class);
            query.setParameter("uservalue", username);
            return query.getSingleResult();
        }catch (PersistenceException e){
            log.error("findbyUsername error",e);
            throw new PersistenceException("findbyUsername error",e);
        }
    }
}
