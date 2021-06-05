package services;


import api.JsonModels.TotalJsonModels.TotalCreate;
import database.entity.TotalPerMonth;
import database.entitymanager.EntityManagerProvider;
import database.repository.TotalPerMonthRepository;
import utils.Utils;

import javax.persistence.EntityManager;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class TotalPerMonthService {
    EntityManager entityManager = Utils.getEntityManager();


    public List<TotalPerMonth> getByMonth(int month) {
        TotalPerMonthRepository totalRepository = new TotalPerMonthRepository(entityManager);
        if (totalRepository.findByLuna(month).isEmpty()) {
            return new ArrayList<>();
        }
        return totalRepository.findByLuna(month);
    }

    public List<TotalPerMonth> getByCounty(String county) {
        TotalPerMonthRepository totalRepository = new TotalPerMonthRepository(entityManager);
        if (totalRepository.findByCounty(county).isEmpty()) {
            return new ArrayList<>();
        }
        return totalRepository.findByCounty(county);
    }

    public List<TotalPerMonth> getByMonthAndCounty(int month, String county) {
        TotalPerMonthRepository totalRepository = new TotalPerMonthRepository(entityManager);
        if (totalRepository.findByLunaAndCounty(month, county).isEmpty()) {
            return new ArrayList<>();
        }
        return totalRepository.findByLunaAndCounty(month, county);
    }
    public List<TotalPerMonth> filterByMonth(int month, String county) {
        TotalPerMonthRepository totalRepository = new TotalPerMonthRepository(entityManager);
        if (totalRepository.filterByMonths(month, county).isEmpty()) {
            return new ArrayList<>();
        }
        return totalRepository.filterByMonths(month, county);
    }
    public boolean saveTotalPerMonth(TotalCreate totalCreate) {
        TotalPerMonthRepository totalRepository = new TotalPerMonthRepository(entityManager);
        int month=0;
           for(int i =0 ; i< totalCreate.getGroups().size(); i++) {
               TotalPerMonth totalPerMonth = new TotalPerMonth();
               totalPerMonth.setLuna(month);
               totalPerMonth.setJudet(totalCreate.getGroups().get(i).getVarJudet());
               totalPerMonth.setTotal(totalCreate.getGroups().get(i).getVarNumartotalsomeri());
               totalPerMonth.setRata(totalCreate.getGroups().get(i).getVarRatasomajului());
               totalRepository.create(totalPerMonth);
           }
        return true;
    }

    public boolean deleteTotalPerMonth(int month) {
        TotalPerMonthRepository totalRepository = new TotalPerMonthRepository(entityManager);
        if (totalRepository.findByLuna(month).isEmpty()) {
            return false;
        }
        totalRepository.delete(month);
        return true;
    }

    public boolean updateTotalPerMonth(TotalPerMonth totalPerMonth) {
        TotalPerMonthRepository totalRepository = new TotalPerMonthRepository(entityManager);
        if (totalRepository.findByLunaAndCounty(totalPerMonth.getLuna(), totalPerMonth.getJudet()).isEmpty()) {
            return false;
        }
        totalRepository.update(totalPerMonth);
        return true;
    }

    public List<TotalPerMonth> getAll() {
        TotalPerMonthRepository totalRepository = new TotalPerMonthRepository(entityManager);
        return totalRepository.all();
    }
}
