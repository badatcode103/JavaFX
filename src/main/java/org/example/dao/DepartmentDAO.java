package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.example.pojo.Department;

import java.util.List;

public class DepartmentDAO {

    private EntityManagerFactory emf =  Persistence.createEntityManagerFactory("EmployeePU");
    private EntityManager em = emf.createEntityManager();

    public void addDepartment(Department department) {
        try{
            em.getTransaction().begin();
            em.persist(department);
            em.getTransaction().commit();
        }catch(Exception e){
            em.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
    }

    public void deleteDepartment(Department department) {
        try{
            em.getTransaction().begin();
            em.remove(department);
            em.getTransaction().commit();
        }catch(Exception e){
            em.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
    }

    public List<Department> getAllDepartments() {
        return em.createQuery("select d from Department d", Department.class).getResultList();
    }

}
