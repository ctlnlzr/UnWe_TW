package edu.tw.services;

import edu.tw.database.entity.Age;
import edu.tw.database.entity.Education;
import edu.tw.database.entitymanager.EntityManagerProvider;
import edu.tw.database.repository.AgeRepository;
import edu.tw.database.repository.EducationRepository;

import javax.persistence.EntityManager;

public class EducationService {
    EntityManager entityManager= EntityManagerProvider.getEntityManagerFactory().createEntityManager();
    public boolean saveEducation(Education education){
        EducationRepository educationRepository=new EducationRepository(entityManager);
        if(educationRepository.findByLunaAndCounty(education.getLuna(), education.getJudet()).isEmpty()){
            return false;
        }
        educationRepository.create(education);
        return true;
    }
    public boolean deleteEducation(int month, String county){
        EducationRepository educationRepository=new EducationRepository(entityManager);
        if(educationRepository.findByLunaAndCounty(month, county).isEmpty()){
            return false;
        }
        educationRepository.delete(month, county);
        return true;
    }
}
