package edu.tw.services;

import edu.tw.database.entity.Environment;
import edu.tw.database.entitymanager.EntityManagerProvider;
import edu.tw.database.repository.EnvironmentRepository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class EnvironmentService {
    EntityManager entityManager = EntityManagerProvider.getEntityManagerFactory().createEntityManager();

    public List<Environment> getByMonth(int month) {
        EnvironmentRepository environmentRepository = new EnvironmentRepository(entityManager);
        if (environmentRepository.findByLuna(month).isEmpty()) {
            return new ArrayList<>();
        }
        return environmentRepository.findByLuna(month);
    }

    public List<Environment> getByCounty(String county) {
        EnvironmentRepository environmentRepository = new EnvironmentRepository(entityManager);
        if (environmentRepository.findByCounty(county).isEmpty()) {
            return new ArrayList<>();
        }
        return environmentRepository.findByCounty(county);
    }

    public List<Environment> getByMonthAndCounty(int month, String county) {
        EnvironmentRepository environmentRepository = new EnvironmentRepository(entityManager);
        if (environmentRepository.findByLunaAndCounty(month, county).isEmpty()) {
            return new ArrayList<>();
        }
        return environmentRepository.findByLunaAndCounty(month, county);
    }
    public List<Environment> filterByMonth(int month, String county) {
        EnvironmentRepository environmentRepository = new EnvironmentRepository(entityManager);
        if (environmentRepository.filterByMonths(month, county).isEmpty()) {
            return new ArrayList<>();
        }
        return environmentRepository.filterByMonths(month, county);
    }

    public boolean saveEnvironment(Environment environment) {
        EnvironmentRepository environmentRepository = new EnvironmentRepository(entityManager);
        if (environmentRepository.findByLunaAndCounty(environment.getLuna(), environment.getJudet()).isEmpty()) {
            return false;
        }
        environmentRepository.create(environment);
        return true;
    }

    public boolean deleteEnvironment(int month, String county) {
        EnvironmentRepository environmentRepository = new EnvironmentRepository(entityManager);
        if (environmentRepository.findByLunaAndCounty(month, county).isEmpty()) {
            return false;
        }
        environmentRepository.delete(month, county);
        return true;
    }

    public boolean updateEnvironment(Environment environment) {
        EnvironmentRepository environmentRepository = new EnvironmentRepository(entityManager);
        if (environmentRepository.findByLunaAndCounty(environment.getLuna(), environment.getJudet()).isEmpty()) {
            return false;
        }
        environmentRepository.update(environment);
        return true;
    }

    public List<Environment> getAll() {
        EnvironmentRepository environmentRepository = new EnvironmentRepository(entityManager);
        return environmentRepository.all();
    }
}
