package services;

import api.JsonModels.EducationJsonModels.EducationCreate;
import api.JsonModels.EnvironmentJsonModels.EnvironmentCreate;
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

    public boolean saveEnvironment(EnvironmentCreate environmentCreate) {
        int month = 0;
        EnvironmentRepository environmentRepository = new EnvironmentRepository(entityManager);

        for(int i = 0; i < environmentCreate.getGroups().size(); i++){
                Environment environment = new Environment();
                environment.setLuna(month);
                environment.setJudet(environmentCreate.getGroups().get(i).getVarJudet());
                environment.setBarbatiDinMediulRural(environmentCreate.getGroups().get(i).getVarNumarsomeribarbatidinmediulrural());
                environment.setBarbatiDinMediulUrban(environmentCreate.getGroups().get(i).getVarNumarsomeribarbatidinmediulurban());
                environment.setFemeiDinMediulRural(environmentCreate.getGroups().get(i).getVarNumarsomerifemeidinmediulrural());
                environment.setFemeiDinMediulUrban(environmentCreate.getGroups().get(i).getVarNumarsomerifemeidinmediulurban());
                environmentRepository.create(environment);
            }

        return true;
    }

    public boolean deleteEnvironment(int month) {
        EnvironmentRepository environmentRepository = new EnvironmentRepository(entityManager);
        if (environmentRepository.findByLuna(month).isEmpty()) {
            return false;
        }
        environmentRepository.delete(month);
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
