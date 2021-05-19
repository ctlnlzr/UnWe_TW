package edu.tw.services;


import edu.tw.database.entity.Education;
import edu.tw.database.entitymanager.EntityManagerProvider;
import edu.tw.database.repository.EducationRepository;

import javax.persistence.EntityManager;
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

    public boolean saveEducation(Education education) {
        EducationRepository educationRepository = new EducationRepository(entityManager);
        if (educationRepository.findByLunaAndCounty(education.getLuna(), education.getJudet()).isEmpty()) {
            return false;
        }
        educationRepository.create(education);
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
