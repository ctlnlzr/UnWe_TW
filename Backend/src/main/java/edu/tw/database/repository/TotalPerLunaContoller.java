package edu.tw.database.repository;


import edu.tw.database.entity.TotalPerLuna;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class TotalPerLunaContoller {
    private final EntityManager entityManager;
    public TotalPerLunaContoller(EntityManager entityManager) {
        this.entityManager=entityManager;
    }
    public List<TotalPerLuna> findByLuna(int luna){
        TypedQuery<TotalPerLuna> query = entityManager.createNamedQuery("TotalPerLuna.findByLuna", TotalPerLuna.class);
        query.setParameter("lunaParam", luna);
        return query.getResultList();
    }
    public List<TotalPerLuna> findByLunaAndCounty(int luna, int county){
        TypedQuery<TotalPerLuna> query = entityManager.createNamedQuery("TotalPerLuna.findByLunaAndCounty", TotalPerLuna.class);
        query.setParameter("lunaParam", luna);
        query.setParameter("judetParam", county);
        return query.getResultList();
    }
    public List<TotalPerLuna> findByCounty(String county){
        TypedQuery<TotalPerLuna> query = entityManager.createNamedQuery("TotalPerLuna.findByCounty", TotalPerLuna.class);
        query.setParameter("judetParam", county);
        return query.getResultList();
    }
}
