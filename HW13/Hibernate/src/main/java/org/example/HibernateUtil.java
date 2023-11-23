package org.example;

import lombok.Getter;
import org.flywaydb.core.Flyway;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.net.URL;

//public class HibernateUtil {
//    private static SessionFactory sessionFactory;
//
//    static {
//        try {
////            Flyway flyway = Flyway.configure().load();
//            Flyway flyway = Flyway.configure().dataSource("jdbc:h2:mem:testdb", "sa", "password").load();
//            flyway.migrate();
//
////            sessionFactory = new Configuration().configure().buildSessionFactory();
//            Configuration configuration = new Configuration();
////            URL url = configuration.getClass().getClassLoader().getResource("hibernate.properties");
//
//            URL url = configuration.getClass().getClassLoader().getResource("hibernate.cnf.xml");
//
//            if (url != null) {
//                configuration = configuration.configure(url);
//            }
//
//            sessionFactory = configuration.buildSessionFactory();
//        } catch (Throwable ex) {
//            System.err.println("Initial SessionFactory creation failed." + ex);
//            throw new ExceptionInInitializerError(ex);
//        }
//    }
//
//    public static SessionFactory getSessionFactory() {
//        if (sessionFactory == null) {
//            initializeSessionFactory();
//        }
//        return sessionFactory;
//    }
//
//    private static synchronized void initializeSessionFactory() {
//        if (sessionFactory != null) {
//            return;
//        }
//    }
//
//    public static void shutdown() {
//        // Закриваємо кеші та інші ресурси при завершенні роботи
//        if (sessionFactory != null && sessionFactory.isClosed()) {
//            sessionFactory.close();
//        }
////        getSessionFactory().close();
//    }
//}



public class HibernateUtil {
    public static final HibernateUtil INSTANCE;

    @Getter
    private SessionFactory sessionFactory;
    static {
        INSTANCE = new HibernateUtil();
    }

    public HibernateUtil() {
//        sessionFactory = new Configuration()
//                .addAnnotatedClass(Client.class)
//                .addAnnotatedClass(Planet.class)
//                .buildSessionFactory();
        try {
            Configuration configuration = new Configuration();
            configuration.configure();
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