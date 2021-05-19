package edu.tw.services;

import edu.tw.database.entity.TotalPerMonth;
import edu.tw.database.entitymanager.EntityManagerProvider;
import edu.tw.database.repository.TotalPerMonthRepository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class TotalPerMonthService {
    EntityManager entityManager = EntityManagerProvider.getEntityManagerFactory().createEntityManager();


    public List<TotalPerMonth> getByMonth(int month) {
        TotalPerMonthRepository totalRepository = new TotalPerMonthRepository(entityManager);
        if (totalRepository.findByLuna(month).isEmpty()) {
            return new ArrayList<>();
        }
        return totalRepository.findByLuna(month);
    }

    public List<TotalPerMonth> getByCounty(String county) {
        TotalPerMonthRepository totalRepository = new TotalPerMonthRepository(entityManager);
        if (totalRepository.findByCounty(county).isEmpty()) {
            return new ArrayList<>();
        }
        return totalRepository.findByCounty(county);
    }

    public List<TotalPerMonth> getByMonthAndCounty(int month, String county) {
        TotalPerMonthRepository totalRepository = new TotalPerMonthRepository(entityManager);
        if (totalRepository.findByLunaAndCounty(month, county).isEmpty()) {
            return new ArrayList<>();
        }
        return totalRepository.findByLunaAndCounty(month, county);
    }

    public boolean saveTotalPerMonth(TotalPerMonth totalPerMonth) {
        TotalPerMonthRepository totalRepository = new TotalPerMonthRepository(entityManager);
        if (totalRepository.findByLunaAndCounty(totalPerMonth.getLuna(), totalPerMonth.getJudet()).isEmpty()) {
            return false;
        }
        totalRepository.create(totalPerMonth);
        return true;
    }

    public boolean deleteTotalPerMonth(int month, String county) {
        TotalPerMonthRepository totalRepository = new TotalPerMonthRepository(entityManager);
        if (totalRepository.findByLunaAndCounty(month, county).isEmpty()) {
            return false;
        }
        totalRepository.delete(month, county);
        return true;
    }

    public boolean updateTotalPerMonth(TotalPerMonth totalPerMonth) {
        TotalPerMonthRepository totalRepository = new TotalPerMonthRepository(entityManager);
        if (totalRepository.findByLunaAndCounty(totalPerMonth.getLuna(), totalPerMonth.getJudet()).isEmpty()) {
            return false;
        }
        totalRepository.update(totalPerMonth);
        return true;
    }

    public List<TotalPerMonth> getAll() {
        TotalPerMonthRepository totalRepository = new TotalPerMonthRepository(entityManager);
        return totalRepository.all();
    }
}
