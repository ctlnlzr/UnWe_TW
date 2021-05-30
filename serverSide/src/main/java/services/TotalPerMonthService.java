package services;


import database.entity.TotalPerMonth;
import database.entitymanager.EntityManagerProvider;
import database.repository.TotalPerMonthRepository;

import javax.persistence.EntityManager;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class TotalPerMonthService {
    EntityManager entityManager = EntityManagerProvider.getEntityManagerFactory().createEntityManager();


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
    public boolean saveTotalPerMonth(String filePath) {
        TotalPerMonthRepository totalRepository = new TotalPerMonthRepository(entityManager);
        int month=0;
      try {
          BufferedReader line = new BufferedReader(new FileReader(filePath));
          line.readLine();
          String lineText = null;
          while ((lineText = line.readLine()) != null) {
              String[] data = lineText.split(",");
              String judet = data[0];
              String[] data3 = lineText.split("\", ");
              String[] data2 = lineText.split("\"");
              // String maiMic25 = data[11];
              // StringTokenizer token = new StringTokenizer(maiMic25, " ");
              StringTokenizer token2 = new StringTokenizer(data3[1], ",");
              String maiMic29 = data2[1];
              String corect = maiMic29.replace(",", "");
              String corect2 = corect.replace(" ", "");
              TotalPerMonth totalPerMonth = new TotalPerMonth();
              totalPerMonth.setLuna(month);
              totalPerMonth.setJudet(judet);
              totalPerMonth.setTotal(Integer.parseInt(corect2));
              totalPerMonth.setRata(Float.parseFloat(token2.nextToken()));
              totalRepository.create(totalPerMonth);
          }
          line.close();
      }catch (Exception e){
          return  false;
      }
        return true;
    }

    public boolean deleteTotalPerMonth(int month, String county) {
        TotalPerMonthRepository totalRepository = new TotalPerMonthRepository(entityManager);
        if (totalRepository.findByLunaAndCounty(month, county).isEmpty()) {
            return false;
        }
        totalRepository.delete(month, county);
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
