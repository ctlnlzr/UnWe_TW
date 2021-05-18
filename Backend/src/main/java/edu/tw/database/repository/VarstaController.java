package edu.tw.database.repository;

import edu.tw.database.entity.Varsta;
import edu.tw.database.entitymanager.EntityManagerProvider;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class VarstaController {
    public VarstaController() {}
    private final EntityManager entityManager = EntityManagerProvider.getEntityManagerFactory().createEntityManager();
    public List<Varsta> findByLuna(int luna){
        TypedQuery<Varsta> query = entityManager.createNamedQuery("Varsta.findByLuna", Varsta.class);
        query.setParameter("lunaParam", luna);
        return query.getResultList();
     //   return entityManager.find(Varsta.class, luna);
    }
}
