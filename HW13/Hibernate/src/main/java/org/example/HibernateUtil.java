package org.example;

import lombok.Getter;
import org.flywaydb.core.Flyway;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.net.URL;

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
//            configuration.configure("./hibernate.properties");
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