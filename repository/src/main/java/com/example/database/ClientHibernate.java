package com.example.database;

import com.example.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
public class ClientHibernate implements Repository<Client> {
    private final SessionFactory factory;

    @Override
    public Optional<Client> find(Integer id) {
        try (final Session session = factory.openSession()) {
            Optional<Client> result;
            session.beginTransaction();

            result = Optional.ofNullable(session.get(Client.class, id));

            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public List<Client> findAll() {

        try (final Session session = factory.openSession()) {
            List<Client> result;
            session.beginTransaction();

            final Query<Client> query = session.createQuery("from Client", Client.class);
            result = query.getResultList();

            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public void save(Client entity) {
        try (final Session session = factory.openSession()) {
            session.beginTransaction();

            session.save(entity);

            session.getTransaction().commit();
        }
    }

    public void update(Client entity) {
        try (final Session session = factory.openSession()) {
            session.beginTransaction();

            session.update(entity);

            session.getTransaction().commit();
        }
    }

    @Override
    public void remove(Client entity) {
        try (final Session session = factory.openSession()) {
            session.beginTransaction();

            session.remove(entity);

            session.getTransaction().commit();
        }
    }
}
