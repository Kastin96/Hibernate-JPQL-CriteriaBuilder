package com.example.start;

import com.example.Booking;
import com.example.Client;
import com.example.Order;
import com.example.database.ClientByCriteriaBuilder;
import com.example.database.ClientHibernate;
import com.example.database.ClientJPQL;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class Demo {

    public static void main(String[] args) {

        /* ClientHibernate */
//        Configuration cfg = new Configuration().configure();
//        final SessionFactory sessionFactory = cfg.buildSessionFactory();
//        final ClientHibernate clientHibernate = new ClientHibernate(sessionFactory);

        /* find */
//        final Optional<Client> clientFromHibernate = clientHibernate.find(99);
//        clientFromHibernate.ifPresent(client -> System.out.println("!!! - " + client));

        /* findAll */
//        final List<Client> allClientFromHibernate = clientHibernate.findAll();
//        allClientFromHibernate.forEach(client -> System.out.println("@@@ - " + client));

        /* save */
//        final Client clientForSave = new Client()
//                .withLogin("testDean1")
//                .withPassword("testDean1")
//                .withName("testDean1")
//                .withAge(105)
//                .withRoleNumber(2)
//                .withOrders(Set.of(new Order()
//                        .withCost(4000)
//                        .withUserId(1)))
//                .withBookings(Set.of(new Booking()
//                        .withProductName("i5-9900")
//                        .withCost(360)));
//
//        clientHibernate.save(clientForSave);

        /* update */
//        final Optional<Client> clientForUpdate = clientHibernate.find(103);
//        clientForUpdate.ifPresent(client -> client.setAge(999));
//        clientForUpdate.ifPresent(clientHibernate::update);

        /* remove */
//        final Optional<Client> clientForRemove = clientHibernate.find(102);
//        clientForRemove.ifPresent(clientHibernate::remove);
//
//        sessionFactory.close();


        /* ClientJPQL */
        /* find */
//        final Optional<Client> clientFromClientJPQL = ClientJPQL.getInstance().find(99);
//        clientFromClientJPQL.ifPresent(client -> System.out.println("!!!! - " + client));

        /* findAll */
//        final List<Client> allClientsFromJPQL = ClientJPQL.getInstance().findAll();
//        allClientsFromJPQL.forEach(client -> System.out.println("@@@ - " + client));

        /* save(persist) */
//        final Client clientForSave = new Client()
//                .withAge(100)
//                .withName("DeanJPQL1")
//                .withRoleNumber(2)
//                .withOrders(Set.of(new Order().withCost(2000).withUserId(99)))
//                .withBookings(Set.of(new Booking().withProductName("Nvidia GTX 2070").withCost(620)))
//                .withLogin("deanJPQL1")
//                .withPassword("deanJPQL1");
//        ClientJPQL.getInstance().save(clientForSave);

        /* save(merge) */
//        final Optional<Client> clientForMerge = ClientJPQL.getInstance().find(107);
//        clientForMerge.ifPresent(client -> client.setAge(777));
//        clientForMerge.ifPresent(client -> ClientJPQL.getInstance().save(client));

        /* remove */
//        final Optional<Client> clientForRemove = ClientJPQL.getInstance().find(107);
//        clientForRemove.ifPresent(client -> ClientJPQL.getInstance().remove(client));

        /* findUserByLogin */
//        final Optional<Client> foundClientByLogin = ClientJPQL.getInstance().findUserByLogin("dean1");
//        foundClientByLogin.ifPresent(client -> System.out.println("### - " + client));


        /* ClientByCriteriaBuilder (Criteria API) */
        /* find */
//        final Optional<Client> clientFromCriteriaBuilder = ClientByCriteriaBuilder.getInstance().find(99);
//        clientFromCriteriaBuilder.ifPresent(client -> System.out.println("!!!!! - " + client));

        /* findAll */
//        final List<Client> findByCriteriaBuilderResult = ClientByCriteriaBuilder.getInstance().findAll();
//        for (Client client : findByCriteriaBuilderResult) {
//            System.out.println("@@@@@ - " + client);
//        }

        /* save */
//        final Client clientForSave = new Client()
//                .withLogin("deanCriteria1")
//                .withPassword("deanCriteria1")
//                .withName("DeanCriteria1")
//                .withAge(555)
//                .withRoleNumber(2)
//                .withOrders(Set.of(new Order().withCost(11).withUserId(99)))
//                .withBookings(Set.of(new Booking().withProductName("Nvidia GTX 2060 Super").withCost(590)));
//
//        ClientByCriteriaBuilder.getInstance().save(clientForSave);

        /* remove */
//        final Optional<Client> clientForRemove = ClientByCriteriaBuilder.getInstance().find(108);
//        clientForRemove.ifPresent(ClientByCriteriaBuilder.getInstance()::remove);

        /* findByLogin */
//        final Optional<Client> userByLogin = ClientByCriteriaBuilder.getInstance().findByLogin("admin");
//        userByLogin.ifPresent(client -> System.out.println("%%%%% - " + client));

        /* findAllUsersLogin */
//        final List<String> allUsersLogin = ClientByCriteriaBuilder.getInstance().findAllUsersLogin();
//        allUsersLogin.forEach(client -> System.out.println("^^^^^ - " + client));

        /* findAllUsersName GOOD! */
//        final List<String> allUsersName = ClientByCriteriaBuilder.getInstance().findAllUsersName();
//        allUsersName.forEach(client -> System.out.println("***** - " + client));

        /* findUserWhereAgeMoreThen */
//        final List<Client> userWhereAgeMoreThen = ClientByCriteriaBuilder.getInstance().findUserWhereAgeMoreThen(300);
//        userWhereAgeMoreThen.forEach(client -> System.out.println("::::: - " + client));

        /* findUserWhereLoginStartWith */
//        final List<Client> userLoginStartWith = ClientByCriteriaBuilder.getInstance()
//                .findUserWhereLoginStartWith("de");
//        userLoginStartWith.forEach(client -> System.out.println("&&&&& - " + client));

        /* countAllUsers */
//        final Long countedAllUsers = ClientByCriteriaBuilder.getInstance().countAllUsers();
//        System.out.println("????? - " + countedAllUsers);
    }
}
