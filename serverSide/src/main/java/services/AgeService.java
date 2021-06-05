package services;


import api.JsonModels.AgeJsonModels.AgeCreate;
import database.entity.Age;
import database.entitymanager.EntityManagerProvider;
import database.repository.AgeRepository;
import utils.Utils;

import javax.persistence.EntityManager;
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

    public boolean saveAge(AgeCreate ageCreate) {
        int month = 0;
        AgeRepository ageRepository = new AgeRepository(entityManager);
        for(int i =0 ; i < ageCreate.getGroups().size(); i ++){
                Age age = new Age();
                age.setJudet(ageCreate.getGroups().get(i).getVarJudet());
                age.setLuna(month);
                age.setMaiMic25(ageCreate.getGroups().get(i).getVarSub25ani());
                age.setIntre25si29(ageCreate.getGroups().get(i).getVar2529ani());
                age.setIntre30si39(ageCreate.getGroups().get(i).getVar3039ani());
                age.setIntre40si49(ageCreate.getGroups().get(i).getVar4049ani());
                age.setIntre50si55(ageCreate.getGroups().get(i).getVar5055ani());
                age.setPeste55(ageCreate.getGroups().get(i).getVarPeste55ani());
                ageRepository.create(age);
        }
        return true;
    }

    public boolean deleteAge(int month) {
        AgeRepository ageRepository = new AgeRepository(entityManager);
        if (ageRepository.findByLuna(month).isEmpty()) {
            return false;
        }
        ageRepository.delete(month);
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
