package services;


import database.entity.Education;
import database.entity.Environment;
import database.entitymanager.EntityManagerProvider;
import database.repository.EducationRepository;

import javax.persistence.EntityManager;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EducationService {
    EntityManager entityManager = EntityManagerProvider.getEntityManagerFactory().createEntityManager();

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

    public boolean saveEducation(String filePath)  {
        int month=0;

        EducationRepository educationRepository = new EducationRepository(entityManager);
        try{
        BufferedReader line = new BufferedReader(new FileReader(filePath));
        line.readLine();
        String lineText = null;
        while ((lineText = line.readLine()) != null) {
            String[] data = lineText.split(",");
            String judet = data[0];
            String faraStudii=data[2];
            String invatamantPrimar=data[3];
            String invatamantGimnazial=data[4];
            String invatamantLiceal=data[5];
            String invatamantPostliceal=data[6];
            String invatamantProfesional=data[7];
            String invatamantUniversitar=data[8];
            Education education=new Education();
            education.setJudet(judet);
            education.setLuna(month);
            education.setFaraStudii(Integer.parseInt(faraStudii));
            education.setPrimar(Integer.parseInt(invatamantPrimar));
            education.setGimnaziu(Integer.parseInt(invatamantGimnazial));
            education.setLiceu(Integer.parseInt(invatamantLiceal));
            education.setPostliceala(Integer.parseInt(invatamantPostliceal));
            education.setProfesionala(Integer.parseInt(invatamantProfesional));
            education.setUniversitate(Integer.parseInt(invatamantUniversitar));
            educationRepository.create(education);

        }
        line.close();
        }catch (Exception e){
            return  false;
        }
        return true;
    }

    public boolean deleteEducation(int month, String county) {
        EducationRepository educationRepository = new EducationRepository(entityManager);
        if (educationRepository.findByLunaAndCounty(month, county).isEmpty()) {
            return false;
        }
        educationRepository.delete(month, county);
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
