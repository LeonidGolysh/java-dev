package org.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class App {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("osbb");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        List<Object[]> nonResidentsInfo = CriteriaAPI.getNonResidentsInfo(entityManager);

        for (Object[] row : nonResidentsInfo) {
            String name = (String) row[0];
            byte autoAllow = (byte) row[1];
            String role = (String) row[2];
            String flatNumber = (String) row[3];
            int flatLevel = (int) row[4];
            double flatSquare = (double) row[5];
            String buildingAddress = (String) row[6];

            System.out.println("Name: " + name);
            System.out.println("Auto Allow: " + autoAllow);
            System.out.println("Role: " + role);
            System.out.println("Flat Number: " + flatNumber);
            System.out.println("Flat Level: " + flatLevel);
            System.out.println("Flat Square: " + flatSquare);
            System.out.println("Building Address: " + buildingAddress);
            System.out.println();
        }

        entityManager.close();
        entityManagerFactory.close();
    }
}
