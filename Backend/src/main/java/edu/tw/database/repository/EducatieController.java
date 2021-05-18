package edu.tw.database.repository;

import edu.tw.database.entity.Educatie;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class EducatieController {
    private final EntityManager entityManager;

    public EducatieController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Educatie> findByLuna(int luna){
        TypedQuery<Educatie> query = entityManager.createNamedQuery("Educatie.findByLuna", Educatie.class);
        query.setParameter("lunaParam", luna);
        return query.getResultList();
    }
    public List<Educatie> findByLunaAndCounty(int luna, int county){
        TypedQuery<Educatie> query = entityManager.createNamedQuery("Educatie.findByLunaAndCounty", Educatie.class);
        query.setParameter("lunaParam", luna);
        query.setParameter("judetParam", county);
        return query.getResultList();
    }
    public List<Educatie> findByCounty(String county){
        TypedQuery<Educatie> query = entityManager.createNamedQuery("Educatie.findByCounty", Educatie.class);
        query.setParameter("judetParam", county);
        return query.getResultList();
    }
}
