package com.oauth2.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Dégi János on 2017.10.08..
 */
public interface IDao<T extends Serializable> {
    T findOne(long id);
    T findOne(String userName);
    List<T> findAll();
    T findByField(Class<T> clazz, String fieldName, String fieldValue);
    void persist(T entity);
    void merge(final T entity);
    T update(T entity);
    void delete(T entity);
    void deleteById(long entityId);
}
