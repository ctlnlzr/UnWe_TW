package edu.tw;

import edu.tw.database.entity.Age;
import edu.tw.database.entitymanager.EntityManagerProvider;
import edu.tw.database.repository.EnvironmentRepository;
import edu.tw.database.repository.AgeRepository;

import javax.persistence.EntityManager;

public class Main {
    public static void main(String[] args) {

        EntityManager entityManager= EntityManagerProvider.getEntityManagerFactory().createEntityManager();
        Age age =new Age();
        AgeRepository ageRepository =new AgeRepository(entityManager);
        System.out.println(ageRepository.findByLuna(2));
        EnvironmentRepository environmentRepository =new EnvironmentRepository(entityManager);
        System.out.println(environmentRepository.findByLuna(1));
        entityManager.close();
    }
}
