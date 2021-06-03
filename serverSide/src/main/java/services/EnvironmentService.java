package services;

import database.entity.Environment;
import database.entitymanager.EntityManagerProvider;
import database.repository.EnvironmentRepository;
import utils.Utils;

import javax.persistence.EntityManager;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EnvironmentService {
    EntityManager entityManager = Utils.getEntityManager();

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

    public boolean saveEnvironment(String filePath) {
        int month = 0;
        EnvironmentRepository environmentRepository = new EnvironmentRepository(entityManager);

        try {
            BufferedReader line = new BufferedReader(new FileReader(filePath));
            line.readLine();
            String lineText = null;
            while ((lineText = line.readLine()) != null) {
                String[] data = lineText.split(",");
                String judet = data[0];
                String femeiUrban = data[5];
                String barbatiUrban = data[6];
                String femeiRural = data[8];
                String barbatiRural = data[9];
                Environment environment = new Environment();
                environment.setLuna(month);
                environment.setJudet(judet);
                environment.setBarbatiDinMediulRural(Integer.parseInt(barbatiRural));
                environment.setBarbatiDinMediulUrban(Integer.parseInt(barbatiUrban));
                environment.setFemeiDinMediulRural(Integer.parseInt(femeiRural));
                environment.setFemeiDinMediulUrban(Integer.parseInt(femeiUrban));
                environmentRepository.create(environment);
            }
            line.close();
        } catch (Exception e) {
            return false;
        }

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
