package edu.tw;

import edu.tw.database.entity.Varsta;
import edu.tw.database.entitymanager.EntityManagerProvider;
import edu.tw.database.repository.MediuController;
import edu.tw.database.repository.VarstaController;

import javax.persistence.EntityManager;

public class Main {
    public static void main(String[] args) {

        EntityManager entityManager= EntityManagerProvider.getEntityManagerFactory().createEntityManager();
        Varsta varsta=new Varsta();
        VarstaController varstaController=new VarstaController(entityManager);
        System.out.println(varstaController.findByLuna(2));
        MediuController mediuController=new MediuController(entityManager);
        System.out.println(mediuController.findByLuna(1));
        entityManager.close();
    }
}
