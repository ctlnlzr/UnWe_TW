package database.repository;


import database.entity.Environment;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class EnvironmentRepository {

    private final EntityManager entityManager;

    public EnvironmentRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void create(Environment environment) {
        entityManager.getTransaction().begin();
        entityManager.persist(environment);
        entityManager.getTransaction().commit();
    }

    public List<Environment> findByLuna(int luna) {
        TypedQuery<Environment> query = entityManager.createNamedQuery("Environment.findByLuna", Environment.class);
        query.setParameter("lunaParam", luna);
        return query.getResultList();
    }

    public List<Environment> all() {
        TypedQuery<Environment> query = entityManager.createNamedQuery("Environment.all", Environment.class);
        return query.getResultList();
    }

    public List<Environment> findByLunaAndCounty(int luna, String county) {
        TypedQuery<Environment> query = entityManager.createNamedQuery("Environment.findByLunaAndCounty", Environment.class);
        query.setParameter("lunaParam", luna);
        query.setParameter("judetParam", county);
        System.out.println("yes");
        return query.getResultList();
    }

    public List<Environment> filterByMonths(int luna, String county) {
        TypedQuery<Environment> query = entityManager.createNamedQuery("Environment.filterByMonths", Environment.class);
        query.setParameter("lunaParam", luna);
        query.setParameter("judetParam", county);
        return query.getResultList();
    }
    public List<Environment> findByCounty(String county) {
        TypedQuery<Environment> query = entityManager.createNamedQuery("Environment.findByCounty", Environment.class);
        query.setParameter("judetParam", county);
        return query.getResultList();
    }

    public void delete(int month) {
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("DELETE FROM Environment g WHERE g.luna=:lunaParam").setParameter("lunaParam", month);
        query.executeUpdate();
        entityManager.getTransaction().commit();
    }

    public void update(Environment environment) {
        TypedQuery<Environment> query = entityManager.createNamedQuery("Environment.update", Environment.class);
        query.setParameter("lunaParam", environment.getLuna());
        query.setParameter("judetParam", environment.getJudet());
        query.setParameter("femeiDinMediulUrbanParam", environment.getFemeiDinMediulUrban());
        query.setParameter("femeiDinMediulRuralParam", environment.getFemeiDinMediulRural());
        query.setParameter("barbatiDinMediulUrbanParam", environment.getBarbatiDinMediulUrban());
        query.setParameter("barbatiDinMediulRuralParam", environment.getBarbatiDinMediulRural());
        query.getResultList();
    }
}
