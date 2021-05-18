package edu.tw.services;

import edu.tw.database.entity.Age;
import edu.tw.database.entity.Environment;
import edu.tw.database.entitymanager.EntityManagerProvider;
import edu.tw.database.repository.AgeRepository;
import edu.tw.database.repository.EnvironmentRepository;

import javax.persistence.EntityManager;

public class EnvironmentService {
    EntityManager entityManager= EntityManagerProvider.getEntityManagerFactory().createEntityManager();
    public boolean saveEnvironment(Environment environment){
        EnvironmentRepository environmentRepository=new EnvironmentRepository(entityManager);
       if(environmentRepository.findByLunaAndCounty(environment.getLuna(), environment.getJudet()).isEmpty()){
           return false;
       }
        environmentRepository.create(environment);
        return true;
    }
    public boolean deleteEnvironment(int month, String county){
        EnvironmentRepository environmentRepository=new EnvironmentRepository(entityManager);
        if(environmentRepository.findByLunaAndCounty(month, county).isEmpty()){
            return false;
        }
        environmentRepository.delete(month, county);
        return true;
    }
}
