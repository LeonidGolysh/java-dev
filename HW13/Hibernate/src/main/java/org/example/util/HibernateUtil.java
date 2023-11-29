package org.example.util;

import lombok.Getter;
import org.example.model.Client;
import org.example.model.Planet;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    public static final HibernateUtil INSTANCE;

    @Getter
    private SessionFactory sessionFactory;
    static {
        INSTANCE = new HibernateUtil();
    }

    public HibernateUtil() {

        try {
            Configuration configuration = new Configuration();
            configuration.addAnnotatedClass(Client.class);
            configuration.addAnnotatedClass(Planet.class);

            sessionFactory = configuration.buildSessionFactory();

        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static HibernateUtil getInstance() {
        return INSTANCE;
    }

    public void close() {
        sessionFactory.close();
    }
}