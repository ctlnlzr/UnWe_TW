package edu.tw.services;

import edu.tw.database.entity.Age;
import edu.tw.database.entitymanager.EntityManagerProvider;
import edu.tw.database.repository.AgeRepository;

import javax.persistence.EntityManager;

public class AgeService {
    EntityManager entityManager = EntityManagerProvider.getEntityManagerFactory().createEntityManager();

    public boolean saveAge(Age age) {
        AgeRepository ageRepository = new AgeRepository(entityManager);
        if(ageRepository.findByLunaAndCounty(age.getLuna(), age.getJudet()).isEmpty()){
            return false;
        }
        ageRepository.create(age);
        return true;
    }
    public boolean deleteAge(int month, String county){
        AgeRepository ageRepository = new AgeRepository(entityManager);
        if(ageRepository.findByLunaAndCounty(month,county).isEmpty()){
            return false;
        }
        ageRepository.delete(month, county);
        return true;
    }
}
