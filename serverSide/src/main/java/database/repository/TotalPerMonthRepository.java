package database.repository;



import database.entity.TotalPerMonth;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class TotalPerMonthRepository {
    private final EntityManager entityManager;

    public TotalPerMonthRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void create(TotalPerMonth totalPerMonth) {
        entityManager.getTransaction().begin();
        entityManager.persist(totalPerMonth);
        entityManager.getTransaction().commit();
    }

    public List<TotalPerMonth> findByLuna(int luna) {
        TypedQuery<TotalPerMonth> query = entityManager.createNamedQuery("TotalPerMonth.findByLuna", TotalPerMonth.class);
        query.setParameter("lunaParam", luna);
        return query.getResultList();
    }

    public List<TotalPerMonth> all() {
        TypedQuery<TotalPerMonth> query = entityManager.createNamedQuery("TotalPerMonth.all", TotalPerMonth.class);
        return query.getResultList();
    }

    public List<TotalPerMonth> findByLunaAndCounty(int luna, String county) {
        TypedQuery<TotalPerMonth> query = entityManager.createNamedQuery("TotalPerMonth.findByLunaAndCounty", TotalPerMonth.class);
        query.setParameter("lunaParam", luna);
        query.setParameter("judetParam", county);
        return query.getResultList();
    }

    public List<TotalPerMonth> filterByMonths(int luna, String county) {
        TypedQuery<TotalPerMonth> query = entityManager.createNamedQuery("TotalPerMonth.filterByMonths", TotalPerMonth.class);
        query.setParameter("lunaParam", luna);
        query.setParameter("judetParam", county);
        return query.getResultList();
    }
    public List<TotalPerMonth> findByCounty(String county) {
        TypedQuery<TotalPerMonth> query = entityManager.createNamedQuery("TotalPerMonth.findByCounty", TotalPerMonth.class);
        query.setParameter("judetParam", county);
        return query.getResultList();
    }

    public void delete(int month, String county) {
        TypedQuery<TotalPerMonth> query = entityManager.createNamedQuery("TotalPerMonth.deleteByMonthAndCounty", TotalPerMonth.class);
        query.setParameter("lunaParam", month);
        query.setParameter("judetParam", county);
    }

    public void update(TotalPerMonth totalPerMonth) {
        TypedQuery<TotalPerMonth> query = entityManager.createNamedQuery("TotalPerMonth.update", TotalPerMonth.class);
        query.setParameter("lunaParam", totalPerMonth.getLuna());
        query.setParameter("judetParam", totalPerMonth.getJudet());
        query.setParameter("totalParam", totalPerMonth.getTotal());
        query.setParameter("rataParam", totalPerMonth.getRata());
        query.getResultList();
    }
}
