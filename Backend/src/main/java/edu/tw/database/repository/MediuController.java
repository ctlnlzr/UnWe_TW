package edu.tw.database.repository;

import edu.tw.database.entity.Mediu;


import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class MediuController {

    private final EntityManager entityManager;

    public MediuController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Mediu> findByLuna(int luna){
        TypedQuery<Mediu> query = entityManager.createNamedQuery("Mediu.findByLuna", Mediu.class);
        query.setParameter("lunaParam", luna);
        return query.getResultList();
    }
    public List<Mediu> findByLunaAndCounty(int luna, int county){
        TypedQuery<Mediu> query = entityManager.createNamedQuery("Mediu.findByLunaAndCounty", Mediu.class);
        query.setParameter("lunaParam", luna);
        query.setParameter("judetParam", county);
        return query.getResultList();
    }
    public List<Mediu> findByCounty(String county){
        TypedQuery<Mediu> query = entityManager.createNamedQuery("Mediu.findByCounty", Mediu.class);
        query.setParameter("judetParam", county);
        return query.getResultList();
    }
}
