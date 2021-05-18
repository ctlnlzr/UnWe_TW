package edu.tw.database.repository;

import edu.tw.database.entity.Age;


import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class AgeRepository {
    public AgeRepository(EntityManager entityManager) {
        this.entityManager=entityManager;
    }
    private final EntityManager entityManager;

    public void create(Age age){
        entityManager.getTransaction().begin();
        entityManager.persist(age);
        entityManager.getTransaction().commit();

    }
    public List<Age> findByLuna(int luna){
        TypedQuery<Age> query = entityManager.createNamedQuery("Age.findByLuna", Age.class);
        query.setParameter("lunaParam", luna);
        return query.getResultList();
    }
    public List<Age> all(){
        TypedQuery<Age> query = entityManager.createNamedQuery("Age.all", Age.class);
        return query.getResultList();
    }
    public List<Age> findByLunaAndCounty(int luna, String county){
        TypedQuery<Age> query = entityManager.createNamedQuery("Age.findByLunaAndCounty", Age.class);
        query.setParameter("lunaParam", luna);
        query.setParameter("judetParam", county);
        return query.getResultList();
    }
    public List<Age> findByCounty(String county){
        TypedQuery<Age> query = entityManager.createNamedQuery("Age.findByCounty", Age.class);
        query.setParameter("judetParam", county);
        return query.getResultList();
    }
    public void delete(int month, String county){
        TypedQuery<Age> query = entityManager.createNamedQuery("Age.deleteByMonthAndCounty", Age.class);
        query.setParameter("lunaParam", month);
        query.setParameter("judetParam", county);
    }
}
