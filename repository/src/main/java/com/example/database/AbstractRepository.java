package com.example.database;

import com.example.AbstractUser;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;


public abstract class AbstractRepository<T extends AbstractUser> implements Repository<T> {
    protected final EntityManagerHelper helper = EntityManagerHelper.getInstance();

    protected abstract TypedQuery<T> getQuery();

    protected abstract TypedQuery<T> getAllQuery();

    @Override
    public Optional<T> find(Integer id) {
        Optional<T> result;

        final EntityManager entityManager = helper.getEntityManager();
        final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        result = Optional.ofNullable(getQuery().setParameter("id", id).getSingleResult());

        transaction.commit();

        entityManager.close();
        return result;
    }

    @Override
    public List<T> findAll() {
        List<T> result;
        final EntityManager entityManager = helper.getEntityManager();
        final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        result = getAllQuery().getResultList();

        transaction.commit();
        entityManager.close();
        return result;
    }

    @Override
    public void save(T entity) {
        final EntityManager entityManager = helper.getEntityManager();
        final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        if (entity.getId() == null) {
            entityManager.persist(entity);
        } else {
            entityManager.merge(entity);
        }

        transaction.commit();
        entityManager.close();
    }
}
