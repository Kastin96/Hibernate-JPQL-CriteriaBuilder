package com.example.database;

import com.example.Client;
import com.example.Order;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CompoundSelection;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

public class ClientByCriteriaBuilder implements Repository<Client> {
    private static volatile ClientByCriteriaBuilder instance;

    private final EntityManagerHelper helper = EntityManagerHelper.getInstance();

    private ClientByCriteriaBuilder() {
    }

    public static ClientByCriteriaBuilder getInstance() {
        if (instance == null) {
            synchronized (ClientByCriteriaBuilder.class) {
                if (instance == null) {
                    instance = new ClientByCriteriaBuilder();
                }
            }
        }
        return instance;
    }


    @Override
    public Optional<Client> find(Integer id) {
        Optional<Client> result;
        final EntityManager entityManager = helper.getEntityManager();
        final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Client> criteriaQuery = criteriaBuilder.createQuery(Client.class);
        final Root<Client> clientRoot = criteriaQuery.from(Client.class);

        criteriaQuery.select(clientRoot).where(criteriaBuilder.equal(clientRoot.get("id"), id));

        result = Optional.ofNullable(entityManager.createQuery(criteriaQuery).getSingleResult());

        transaction.commit();
        entityManager.close();

        return result;
    }

    @Override
    public List<Client> findAll() {
        List<Client> result;
        final EntityManager entityManager = helper.getEntityManager();
        final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Client> criteriaQuery = criteriaBuilder.createQuery(Client.class);

        final Root<Client> clientRoot = criteriaQuery.from(Client.class);


        criteriaQuery.select(clientRoot);

        result = entityManager.createQuery(criteriaQuery).getResultList();

        transaction.commit();
        entityManager.close();

        return result;
    }

    @Override
    public void save(Client entity) {
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

    @Override
    public void remove(Client entity) {
        final EntityManager entityManager = helper.getEntityManager();
        final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaDelete<Client> criteriaDelete = criteriaBuilder.createCriteriaDelete(Client.class);
        final Root<Client> clientRoot = criteriaDelete.from(Client.class);

        if (!entity.getOrders().isEmpty()) {
            final CriteriaDelete<Order> criteriaDeleteOrders = criteriaBuilder.createCriteriaDelete(Order.class);
            final Root<Order> orderRoot = criteriaDeleteOrders.from(Order.class);
            criteriaDeleteOrders.where(criteriaBuilder.equal(orderRoot.get("userId"), entity.getId()));

            entityManager.createQuery(criteriaDeleteOrders).executeUpdate();
        }

        criteriaDelete.where(criteriaBuilder.equal(clientRoot.get("id"), entity.getId()));

        entityManager.createQuery(criteriaDelete).executeUpdate();

        transaction.commit();
        entityManager.close();
    }

    public Optional<Client> findByLogin(String login) {
        Optional<Client> result;
        final EntityManager entityManager = helper.getEntityManager();
        final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Client> criteriaQuery = criteriaBuilder.createQuery(Client.class);
        final Root<Client> clientRoot = criteriaQuery.from(Client.class);

        criteriaQuery.select(clientRoot).where(criteriaBuilder.equal(clientRoot.get("login"), login));

        result = Optional.ofNullable(entityManager.createQuery(criteriaQuery).getSingleResult());

        transaction.commit();
        entityManager.close();

        return result;
    }

    public List<String> findAllUsersLogin() {
        List<String> result;
        final EntityManager entityManager = helper.getEntityManager();
        final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<String> criteriaQuery = criteriaBuilder.createQuery(String.class);
        final Root<Client> clientRoot = criteriaQuery.from(Client.class);

        final CompoundSelection<String> login = criteriaBuilder.construct(String.class, clientRoot.get("login"));

        criteriaQuery.select(login);

        result = entityManager.createQuery(criteriaQuery).getResultList();

        transaction.commit();
        entityManager.close();

        return result;
    }

    public List<String> findAllUsersName() {
        List<String> result;
        final EntityManager entityManager = helper.getEntityManager();
        final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<String> criteriaQuery = criteriaBuilder.createQuery(String.class);
        final Root<Client> clientRoot = criteriaQuery.from(Client.class);

        final CompoundSelection<String> login = criteriaBuilder.construct(String.class, clientRoot.get("name"));

        criteriaQuery.select(login);

        result = entityManager.createQuery(criteriaQuery).getResultList();

        transaction.commit();
        entityManager.close();

        return result;
    }

    public List<Client> findUserWhereAgeMoreThen(Integer age) {
        List<Client> result;
        final EntityManager entityManager = helper.getEntityManager();
        final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Client> criteriaQuery = criteriaBuilder.createQuery(Client.class);
        final Root<Client> clientRoot = criteriaQuery.from(Client.class);

        criteriaQuery.select(clientRoot).where(criteriaBuilder.gt(clientRoot.get("age"), age));

        result = entityManager.createQuery(criteriaQuery).getResultList();

        transaction.commit();
        entityManager.close();

        return result;
    }

    public List<Client> findUserWhereLoginStartWith(String substring) {
        List<Client> result;
        final EntityManager entityManager = helper.getEntityManager();
        final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Client> criteriaQuery = criteriaBuilder.createQuery(Client.class);
        final Root<Client> clientRoot = criteriaQuery.from(Client.class);

        criteriaQuery.select(clientRoot).where(criteriaBuilder.like(clientRoot.get("login"), substring + "%"));

        result = entityManager.createQuery(criteriaQuery).getResultList();

        transaction.commit();
        entityManager.close();

        return result;
    }

    public Long countAllUsers() {
        Long result;
        final EntityManager entityManager = helper.getEntityManager();
        final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        criteriaQuery.select(criteriaBuilder.count(criteriaQuery.from(Client.class)));

        result = entityManager.createQuery(criteriaQuery).getSingleResult();

        transaction.commit();
        entityManager.close();

        return result;
    }

}
