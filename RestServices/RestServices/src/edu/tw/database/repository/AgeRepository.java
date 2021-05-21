package edu.tw.database.repository;

import edu.tw.database.entity.Age;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class AgeRepository {
    public AgeRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    private final EntityManager entityManager;

    public void create(Age age) {
        entityManager.getTransaction().begin();
        entityManager.persist(age);
        entityManager.getTransaction().commit();

    }

    public List<Age> findByLuna(int luna) {
        TypedQuery<Age> query = entityManager.createNamedQuery("Age.findByLuna", Age.class);
        query.setParameter("lunaParam", luna);
        return query.getResultList();
    }

    public List<Age> all() {
        TypedQuery<Age> query = entityManager.createNamedQuery("Age.all", Age.class);
        return query.getResultList();
    }

    public List<Age> findByLunaAndCounty(int luna, String county) {
        TypedQuery<Age> query = entityManager.createNamedQuery("Age.findByLunaAndCounty", Age.class);
        query.setParameter("lunaParam", luna);
        query.setParameter("judetParam", county);
        return query.getResultList();
    }

    public List<Age> filterByMonths(int luna, String county) {
        TypedQuery<Age> query = entityManager.createNamedQuery("Age.filterByMonths", Age.class);
        query.setParameter("lunaParam", luna);
        query.setParameter("judetParam", county);
        return query.getResultList();
    }
    public List<Age> findByCounty(String county) {
        TypedQuery<Age> query = entityManager.createNamedQuery("Age.findByCounty", Age.class);
        query.setParameter("judetParam", county);
        return query.getResultList();
    }

    public void delete(int month, String county) {
        TypedQuery<Age> query = entityManager.createNamedQuery("Age.deleteByMonthAndCounty", Age.class);
        query.setParameter("lunaParam", month);
        query.setParameter("judetParam", county);
    }

    public void update(Age age) {
        TypedQuery<Age> query = entityManager.createNamedQuery("Age.update", Age.class);
        query.setParameter("lunaParam", age.getLuna());
        query.setParameter("judetParam", age.getJudet());
        query.setParameter("maiMic25Param", age.getMaiMic25());
        query.setParameter("intre25si29Param", age.getIntre25si29());
        query.setParameter("intre30si39Param", age.getIntre30si39());
        query.setParameter("intre40si49Param", age.getIntre40si49());
        query.setParameter("intre50si55Param", age.getIntre50si55());
        query.setParameter("peste55Param", age.getPeste55());
        query.getResultList();
    }
}
