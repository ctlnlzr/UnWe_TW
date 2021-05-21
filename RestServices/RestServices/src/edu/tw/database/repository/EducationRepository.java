package edu.tw.database.repository;

import edu.tw.database.entity.Education;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class EducationRepository {
    private final EntityManager entityManager;

    public EducationRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void create(Education education) {
        entityManager.getTransaction().begin();
        entityManager.persist(education);
        entityManager.getTransaction().commit();
    }

    public List<Education> findByLuna(int luna) {
        TypedQuery<Education> query = entityManager.createNamedQuery("Education.findByLuna", Education.class);
        query.setParameter("lunaParam", luna);
        return query.getResultList();
    }

    public List<Education> all() {
        TypedQuery<Education> query = entityManager.createNamedQuery("Education.all", Education.class);
        return query.getResultList();
    }

    public List<Education> findByLunaAndCounty(int luna, String county) {
        TypedQuery<Education> query = entityManager.createNamedQuery("Education.findByLunaAndCounty", Education.class);
        query.setParameter("lunaParam", luna);
        query.setParameter("judetParam", county);
        return query.getResultList();
    }
    public List<Education> filterByMonths(int luna, String county) {
        TypedQuery<Education> query = entityManager.createNamedQuery("Education.filterByMonths", Education.class);
        query.setParameter("lunaParam", luna);
        query.setParameter("judetParam", county);
        return query.getResultList();
    }

    public List<Education> findByCounty(String county) {
        TypedQuery<Education> query = entityManager.createNamedQuery("Education.findByCounty", Education.class);
        query.setParameter("judetParam", county);
        return query.getResultList();
    }

    public void delete(int month, String county) {
        TypedQuery<Education> query = entityManager.createNamedQuery("Education.deleteByMonthAndCounty", Education.class);
        query.setParameter("lunaParam", month);
        query.setParameter("judetParam", county);
    }

    public void update(Education education) {
        TypedQuery<Education> query = entityManager.createNamedQuery("Education.update", Education.class);
        query.setParameter("lunaParam", education.getLuna());
        query.setParameter("judetParam", education.getJudet());
        query.setParameter("faraStudiiParam", education.getFaraStudii());
        query.setParameter("primarParam", education.getPrimar());
        query.setParameter("gimnaziuParam", education.getGimnaziu());
        query.setParameter("liceuParam", education.getLiceu());
        query.setParameter("postlicealaParam", education.getPostliceala());
        query.setParameter("profesionalaParam", education.getProfesionala());
        query.setParameter("universitateParam", education.getUniversitate());
        query.getResultList();
    }
}
