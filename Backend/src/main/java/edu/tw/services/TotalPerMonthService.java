package edu.tw.services;

import edu.tw.database.entity.Age;
import edu.tw.database.entity.TotalPerMonth;
import edu.tw.database.entitymanager.EntityManagerProvider;
import edu.tw.database.repository.AgeRepository;
import edu.tw.database.repository.TotalPerMonthRepository;

import javax.persistence.EntityManager;

public class TotalPerMonthService {
    EntityManager entityManager= EntityManagerProvider.getEntityManagerFactory().createEntityManager();
    public boolean saveTotalPerMonth(TotalPerMonth totalPerMonth){
        TotalPerMonthRepository totalRepository=new TotalPerMonthRepository(entityManager);
        if(totalRepository.findByLunaAndCounty(totalPerMonth.getLuna(), totalPerMonth.getJudet()).isEmpty()){
            return false;
        }
        totalRepository.create(totalPerMonth);
        return true;
    }
    public boolean deleteTotalPerMonth(int month, String county){
        TotalPerMonthRepository totalRepository=new TotalPerMonthRepository(entityManager);
        if(totalRepository.findByLunaAndCounty(month, county).isEmpty()){
            return false;
        }
        totalRepository.delete(month, county);
        return true;
    }
}
