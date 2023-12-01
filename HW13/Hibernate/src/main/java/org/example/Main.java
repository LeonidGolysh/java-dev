package org.example;

import org.example.crud.ClientCrudService;
import org.example.crud.TicketCrudService;

import org.example.model.Client;
import org.example.model.Planet;
import org.example.model.Ticket;

import org.example.util.HibernateUtil;
import org.flywaydb.core.Flyway;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        Flyway flyway = Flyway.configure().dataSource("jdbc:h2:./test", "sa", "password").load();
        flyway.migrate();

        SessionFactory sessionFactory = HibernateUtil.getInstance().getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            ClientCrudService clientCrudService = new ClientCrudService(sessionFactory);
            Client newClient = new Client();
            newClient.setName("Big Bob");
            clientCrudService.saveClient(newClient);

            //Create a ticket for the client
            Ticket ticket = new Ticket();
            ticket.setCreatedAt(LocalDateTime.now());
            ticket.setClient(newClient);

            Planet fromPlanet = new Planet("MARS", "Mars");
            Planet toPlanet = new Planet("VEN", "Venus");

            ticket.setFromPlanet(fromPlanet);
            ticket.setToPlanet(toPlanet);

            //Save the ticket
            TicketCrudService ticketCrudService = new TicketCrudService(sessionFactory);
            ticketCrudService.saveTicket(ticket);

            //Retrieve and print the ticket details
            Ticket retrievedTicket = ticketCrudService.getTicketById(ticket.getId());
            System.out.println("Found ticket: " + retrievedTicket);

            retrievedTicket.setCreatedAt(LocalDateTime.now().minusDays(1));
            ticketCrudService.updateTicket(retrievedTicket);

            ticketCrudService.deleteTicket(retrievedTicket);

            clientCrudService.deleteClient(newClient);

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