package edu.tw.database.repository;

import edu.tw.database.entity.Varsta;


import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class VarstaController {
    public VarstaController(EntityManager entityManager) {
        this.entityManager=entityManager;
    }
    private final EntityManager entityManager;
    public List<Varsta> findByLuna(int luna){
        TypedQuery<Varsta> query = entityManager.createNamedQuery("Varsta.findByLuna", Varsta.class);
        query.setParameter("lunaParam", luna);
        return query.getResultList();
    }
    public List<Varsta> findByLunaAndCounty(int luna, int county){
        TypedQuery<Varsta> query = entityManager.createNamedQuery("Varsta.findByLunaAndCounty", Varsta.class);
        query.setParameter("lunaParam", luna);
        query.setParameter("judetParam", county);
        return query.getResultList();
    }
    public List<Varsta> findByCounty(String county){
        TypedQuery<Varsta> query = entityManager.createNamedQuery("Varsta.findByCounty", Varsta.class);
        query.setParameter("judetParam", county);
        return query.getResultList();
    }
}
