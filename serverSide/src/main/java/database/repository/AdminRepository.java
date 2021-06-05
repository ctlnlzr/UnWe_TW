package database.repository;


import database.entity.Admin;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class AdminRepository {
    public AdminRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    private final EntityManager entityManager;

    public List<Admin> find(String username, String password) {
        TypedQuery<Admin> query = entityManager.createNamedQuery("Admin.find", Admin.class);
        query.setParameter("username", username);
        query.setParameter("password", password);
        return query.getResultList();
    }

    public void create(Admin admin) {
        entityManager.getTransaction().begin();
        entityManager.persist(admin);
        entityManager.getTransaction().commit();
    }

    public void update(Admin admin) {
        entityManager.getTransaction().begin();
        entityManager.merge(admin);
        entityManager.getTransaction().commit();
    }

    public void deleteToken(Admin admin) {
        entityManager.getTransaction().begin();
        admin.setToken(String.valueOf(0));
        entityManager.merge(admin);
        entityManager.getTransaction().commit();
    }

    public List<Admin> findToken(Integer token) {
        TypedQuery<Admin> query = entityManager.createNamedQuery("Admin.findToken", Admin.class);
        query.setParameter("token", token);
        return query.getResultList();
    }

}
