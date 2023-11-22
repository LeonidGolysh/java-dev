package org.example;

import org.example.crud.ClientCrudService;
import org.flywaydb.core.Flyway;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.io.ObjectInputFilter;

public class Main {
    public static void main(String[] args) {
//        Flyway flyway = Flyway.configure().load();
        Flyway flyway = Flyway.configure().dataSource("jdbc:h2:./mydb", null, null).load();
        flyway.migrate();

//        Session session = HibernateUtil.getSessionFactory().openSession();
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
//            ClientCrudService clientCrudService = new ClientCrudService((SessionFactory) session);
            ClientCrudService clientCrudService = new ClientCrudService(sessionFactory);
            Client newClient = new Client();
            newClient.setName("Big Bob");
            clientCrudService.saveClient(newClient);

            Client client = clientCrudService.getClientById(1L);
            System.out.println("Found client: " + client.getName());

            client.setName("Big Bob");
            clientCrudService.updateClient(client);

            clientCrudService.deleteClient(client);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}