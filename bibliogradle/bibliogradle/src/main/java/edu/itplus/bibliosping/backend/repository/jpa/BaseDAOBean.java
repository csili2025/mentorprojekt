package edu.itplus.bibliosping.backend.repository.jpa;

import edu.itplus.bibliosping.backend.model.AbstractModel;
import edu.itplus.bibliosping.backend.model.BaseEntity;
import edu.itplus.bibliosping.backend.model.User;
import edu.itplus.bibliosping.backend.repository.BaseDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class BaseDAOBean<T extends AbstractModel,I> implements BaseDAO<T,I> {

    @Autowired
    private Logger log;

    @PersistenceContext
    protected EntityManager entityManager;

    private Class<T> type;

    public BaseDAOBean(Class<T> type) {
        this.type = type;
    }

    @Override
    public T findByID(I id) {
        try {
            return entityManager.find(type, id);
        }catch(PersistenceException e) {
            log.error("FindById field",e);
            throw new PersistenceException("FindById field",e);
        }
    }

    @Override
    public T create(T model) {
        try {
            entityManager.persist(model);
            entityManager.flush();
            return model;
        }catch(PersistenceException e) {
            log.error("Create field",e);
            throw new PersistenceException("Create field",e);
        }
    }

    @Override
    public void update(T model) {
        try{
            entityManager.merge(model);
        }catch (PersistenceException e) {
            log.error("Update field",e);
            throw new PersistenceException("Update field",e);
        }
    }

    @Override
    public void delete(T model) {
        try{
            entityManager.remove(model);
        }catch (PersistenceException e) {
            log.error("Delete field",e);
            throw new PersistenceException("Delete field",e);
        }
    }

    @Override
    public List<T> findAll() {
        try{
            //TypedQuery<T> query = entityManager.createQuery("from "+type.getSimpleName(),type);
            //return query.getResultList();
            CriteriaBuilder cb = entityManager.getCriteriaBuilder ();
            CriteriaQuery<T> criteriaQuery = cb.createQuery (type);
            Root<T> rootEntry = criteriaQuery.from (type);
            CriteriaQuery<T> all = criteriaQuery.select (rootEntry);
            TypedQuery<T> allQuery = entityManager.createQuery (all);
            return allQuery.getResultList ();

        }catch (PersistenceException e) {
            log.error("FindAll field",e);
            throw new PersistenceException("FindAll field",e);
        }
    }
}
