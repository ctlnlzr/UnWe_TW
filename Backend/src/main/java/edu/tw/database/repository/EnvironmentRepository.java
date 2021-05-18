package edu.tw.database.repository;

import edu.tw.database.entity.Age;
import edu.tw.database.entity.Environment;


import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class EnvironmentRepository {

    private final EntityManager entityManager;

    public EnvironmentRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    public void create(Environment environment){
        entityManager.getTransaction().begin();
        entityManager.persist(environment);
        entityManager.getTransaction().commit();
    }
    public List<Environment> findByLuna(int luna){
        TypedQuery<Environment> query = entityManager.createNamedQuery("Environment.findByLuna", Environment.class);
        query.setParameter("lunaParam", luna);
        return query.getResultList();
    }
    public List<Environment> all(){
        TypedQuery<Environment> query = entityManager.createNamedQuery("Environment.all", Environment.class);
        return query.getResultList();
    }
    public List<Environment> findByLunaAndCounty(int luna, String county){
        TypedQuery<Environment> query = entityManager.createNamedQuery("Environment.findByLunaAndCounty", Environment.class);
        query.setParameter("lunaParam", luna);
        query.setParameter("judetParam", county);
        return query.getResultList();
    }
    public List<Environment> findByCounty(String county){
        TypedQuery<Environment> query = entityManager.createNamedQuery("Environment.findByCounty", Environment.class);
        query.setParameter("judetParam", county);
        return query.getResultList();
    }
    public void delete(int month, String county){
        TypedQuery<Environment> query = entityManager.createNamedQuery("Environment.deleteByMonthAndCounty", Environment.class);
        query.setParameter("lunaParam", month);
        query.setParameter("judetParam", county);
    }
}
