package edu.itplus.bibliosping.backend.repository;

import edu.itplus.bibliosping.backend.model.AbstractModel;
import edu.itplus.bibliosping.backend.model.User;

import java.util.List;

public interface BaseDAO <T extends AbstractModel,I> {
    T findByID(I id);
    T create(T model);
    void update(T model);
    void delete(T model);
    List<T> findAll();
}
