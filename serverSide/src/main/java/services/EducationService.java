package services;


import api.JsonModels.EducationJsonModels.EducationCreate;
import database.entity.Education;
import database.entity.Environment;
import database.entitymanager.EntityManagerProvider;
import database.repository.EducationRepository;
import utils.Utils;

import javax.persistence.EntityManager;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EducationService {
    EntityManager entityManager = Utils.getEntityManager();

    public List<Education> getByMonth(int month) {
        EducationRepository educationRepository = new EducationRepository(entityManager);
        if (educationRepository.findByLuna(month).isEmpty()) {
            return new ArrayList<>();
        }
        return educationRepository.findByLuna(month);
    }

    public List<Education> getByCounty(String county) {
        EducationRepository educationRepository = new EducationRepository(entityManager);
        if (educationRepository.findByCounty(county).isEmpty()) {
            return new ArrayList<>();
        }
        return educationRepository.findByCounty(county);
    }

    public List<Education> getByMonthAndCounty(int month, String county) {
        EducationRepository educationRepository = new EducationRepository(entityManager);
        if (educationRepository.findByLunaAndCounty(month, county).isEmpty()) {
            return new ArrayList<>();
        }
        return educationRepository.findByLunaAndCounty(month, county);
    }
    public List<Education> filterByMonth(int month, String county) {
        EducationRepository educationRepository = new EducationRepository(entityManager);
        if (educationRepository.filterByMonths(month, county).isEmpty()) {
            return new ArrayList<>();
        }
        return educationRepository.filterByMonths(month, county);
    }

    public boolean saveEducation(EducationCreate educationCreate)  {
        int month=0;

        EducationRepository educationRepository = new EducationRepository(entityManager);
        for(int i = 0; i<educationCreate.getGroups().size(); i++){
            Education education=new Education();
            education.setJudet(educationCreate.getGroups().get(i).getVarJudet());
            education.setLuna(month);
            education.setFaraStudii(educationCreate.getGroups().get(i).getVarFarastudii());
            education.setPrimar(educationCreate.getGroups().get(i).getVarInvatamantprimar());
            education.setGimnaziu(educationCreate.getGroups().get(i).getVarInvatamantgimnazial());
            education.setLiceu(educationCreate.getGroups().get(i).getVarInvatamantliceal());
            education.setPostliceala(educationCreate.getGroups().get(i).getVarInvatamantposticeal());
            education.setProfesionala(educationCreate.getGroups().get(i).getVarInvatamantprofesionalartesimeserii());
            education.setUniversitate(educationCreate.getGroups().get(i).getVarInvatamantuniversitar());
            educationRepository.create(education);
        }
        return true;
    }

    public boolean deleteEducation(int month) {
        EducationRepository educationRepository = new EducationRepository(entityManager);
        if (educationRepository.findByLuna(month).isEmpty()) {
            return false;
        }
        educationRepository.delete(month);
        return true;
    }

    public boolean updateEducation(Education education) {
        EducationRepository educationRepository = new EducationRepository(entityManager);
        if (educationRepository.findByLunaAndCounty(education.getLuna(), education.getJudet()).isEmpty()) {
            return false;
        }
        educationRepository.update(education);
        return true;
    }

    public List<Education> getAll() {
        EducationRepository educationRepository = new EducationRepository(entityManager);
        return educationRepository.all();
    }
}
