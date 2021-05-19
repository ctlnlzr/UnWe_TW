package edu.tw.services;

import edu.tw.database.entity.Age;
import edu.tw.database.entitymanager.EntityManagerProvider;
import edu.tw.database.repository.AgeRepository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class AgeService {
    EntityManager entityManager = EntityManagerProvider.getEntityManagerFactory().createEntityManager();

    public List<Age> getByMonth(int month) {
        AgeRepository ageRepository = new AgeRepository(entityManager);
        if (ageRepository.findByLuna(month).isEmpty()) {
            return new ArrayList<>();
        }
        return ageRepository.findByLuna(month);
    }

    public List<Age> getByCounty(String county) {
        AgeRepository ageRepository = new AgeRepository(entityManager);
        if (ageRepository.findByCounty(county).isEmpty()) {
            return new ArrayList<>();
        }
        return ageRepository.findByCounty(county);
    }

    public List<Age> getByMonthAndCounty(int month, String county) {
        AgeRepository ageRepository = new AgeRepository(entityManager);
        if (ageRepository.findByLunaAndCounty(month, county).isEmpty()) {
            return new ArrayList<>();
        }
        return ageRepository.findByLunaAndCounty(month, county);
    }
    public List<Age> filterByMonth(int month, String county) {
        AgeRepository ageRepository = new AgeRepository(entityManager);
        if (ageRepository.filterByMonths(month, county).isEmpty()) {
            return new ArrayList<>();
        }
        return ageRepository.filterByMonths(month, county);
    }
    public boolean saveAge(Age age) {
        AgeRepository ageRepository = new AgeRepository(entityManager);
        if (ageRepository.findByLunaAndCounty(age.getLuna(), age.getJudet()).isEmpty()) {
            return false;
        }
        ageRepository.create(age);
        return true;
    }

    public boolean deleteAge(int month, String county) {
        AgeRepository ageRepository = new AgeRepository(entityManager);
        if (ageRepository.findByLunaAndCounty(month, county).isEmpty()) {
            return false;
        }
        ageRepository.delete(month, county);
        return true;
    }

    public boolean updateAge(Age age) {
        AgeRepository ageRepository = new AgeRepository(entityManager);
        if (ageRepository.findByLunaAndCounty(age.getLuna(), age.getJudet()).isEmpty()) {
            return false;
        }
        ageRepository.update(age);
        return true;
    }

    public List<Age> getAll() {
        AgeRepository ageRepository = new AgeRepository(entityManager);
        return ageRepository.all();
    }

}
