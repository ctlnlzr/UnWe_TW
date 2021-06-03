package services;


import database.entity.Age;
import database.entitymanager.EntityManagerProvider;
import database.repository.AgeRepository;
import utils.Utils;

import javax.persistence.EntityManager;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AgeService {
    EntityManager entityManager = Utils.getEntityManager();

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

    public boolean saveAge(String filePath) {
        int month = 0;
        AgeRepository ageRepository = new AgeRepository(entityManager);
        try {
            BufferedReader line = new BufferedReader(new FileReader(filePath));
            line.readLine();
            String lineText = null;
            while ((lineText = line.readLine()) != null) {
                String[] data = lineText.split(",");
                String judet = data[0];
                String sub25 = data[2];
                String intre25Si29 = data[3];
                String intre30Si39 = data[4];
                String intre40Si49 = data[5];
                String intre50Si55 = data[6];
                String peste55 = data[7];
                Age age = new Age();
                age.setJudet(judet);
                age.setLuna(month);
                age.setMaiMic25(Integer.parseInt(sub25));
                age.setIntre25si29(Integer.parseInt(intre25Si29));
                age.setIntre30si39(Integer.parseInt(intre30Si39));
                age.setIntre40si49(Integer.parseInt(intre40Si49));
                age.setIntre50si55(Integer.parseInt(intre50Si55));
                age.setPeste55(Integer.parseInt(peste55));
                ageRepository.create(age);
            }
            line.close();
        } catch (Exception e) {
            return false;
        }
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
