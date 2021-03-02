package com.example.database;

import com.example.Client;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.Optional;

public class ClientJPQL extends AbstractRepository<Client> {
    private static volatile ClientJPQL instance;

    private ClientJPQL() {
    }

    public static ClientJPQL getInstance() {
        if (instance == null) {
            synchronized (ClientJPQL.class) {
                if (instance == null) {
                    instance = new ClientJPQL();
                }
            }
        }
        return instance;
    }

    @Override
    protected TypedQuery<Client> getQuery() {
        return helper.getEntityManager()
                .createQuery("from Client where id = :id", Client.class);
    }

    @Override
    protected TypedQuery<Client> getAllQuery() {
        return helper.getEntityManager()
                .createQuery("from Client", Client.class);
    }

    @Override
    public void remove(Client entity) {
        removeById(entity.getId());
    }

    public void removeById(Integer id) {
        final EntityManager entityManager = helper.getEntityManager();
        final EntityTransaction transaction = entityManager.getTransaction();

        final Optional<Client> removableUser = find(id);

        if (removableUser.isPresent()) {
            final Client removableClient = removableUser.get();

            transaction.begin();

            if (!removableClient.getOrders().isEmpty()) {
                Query ordersQuery = entityManager.createQuery("Delete from Order where userId = :userId");
                ordersQuery.setParameter("userId", id);
                ordersQuery.executeUpdate();
            }

            Query query = entityManager.createQuery("Delete from Client where id = :id");
            query.setParameter("id", id);
            query.executeUpdate();

            transaction.commit();
        }

        entityManager.close();
    }


    public Optional<Client> findUserByLogin(String login) {
        Optional<Client> result;
        final EntityManager entityManager = helper.getEntityManager();
        final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        final TypedQuery<Client> query = entityManager.createQuery("from Client where login = :login", Client.class);
        query.setParameter("login", login);

        result = Optional.ofNullable(query.getSingleResult());

        transaction.commit();
        entityManager.close();

        return result;
    }
}
