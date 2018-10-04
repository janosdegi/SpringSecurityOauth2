package com.oauth2.dao;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Dégi János on 2017.09.23..
 */
@Transactional
public abstract class AbstractJpaDao<T extends Serializable> {

    private Class<T> clazz;

//    private EntityManagerFactory emf;

    @PersistenceContext//(name = "jpa_envisy")
    protected EntityManager entityManager; // = emf.createEntityManager();

    public final void setClazz(final Class<T> clazzToSet) {
        this.clazz = clazzToSet;
    }

    public T findOne(final long id) {
        return entityManager.find(clazz, id);
    }

    public T findOne(final String id) {
        return entityManager.find(clazz, id);
    }

    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        return entityManager.createQuery("from " + clazz.getName()).getResultList();
    }

    public void persist(final T entity) {
        entityManager.persist(entity);
    }

    public void merge(final T entity) {
        entityManager.merge(entity);
    }

    public T update(final T entity) {
        return entityManager.merge(entity);
    }

    public void delete(final T entity) {
        entityManager.remove(entity);
    }

    public void deleteById(final long entityId) {
        final T entity = findOne(entityId);
        delete(entity);
    }

    public T findByField(Class<T> clazz, String fieldName, String fieldValue) {
        Query query = entityManager.createQuery("select e from " + clazz.getName() + " e" +
                " where e."+fieldName+" = :"+fieldName);
        query.setParameter(fieldName, fieldValue);
        return (T)query.getSingleResult();
    }

}
