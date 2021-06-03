package services;



import database.entity.Admin;
import database.entitymanager.EntityManagerProvider;
import database.repository.AdminRepository;
import utils.Utils;

import java.util.List;

import javax.persistence.EntityManager;

public class AdminService {
    EntityManager entityManager = Utils.getEntityManager();
    public boolean find(Admin admin) {
        AdminRepository adminRepository = new AdminRepository(entityManager);
        return !adminRepository.find(admin.getUsername(), admin.getPassword()).isEmpty();
    }

    public boolean saveAdmin(Admin admin){
        AdminRepository adminRepository = new AdminRepository(entityManager);
        if(find(admin)) return false;
        adminRepository.create(admin);
        return true;
    }

    public void addToken(Admin admin){
        AdminRepository adminRepository = new AdminRepository(entityManager);
        adminRepository.update(admin);
    }

    public boolean existToken(String token){
        AdminRepository adminRepository = new AdminRepository(entityManager);
        return !adminRepository.findToken(Integer.parseInt(token)).isEmpty();
    }

    public void deleteToken(String token){
        AdminRepository adminRepository = new AdminRepository(entityManager);
        List<Admin> list = adminRepository.findToken(Integer.parseInt(token));
        if(!list.isEmpty()) {
            System.out.println("Adminul din service " + list.get(0));
            adminRepository.deleteToken(list.get(0));
        }
    }
}
