package org.example.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.pojo.Employee;

import java.util.List;

public class EmployeeDAO {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("EmployeePU");
    EntityManager em  = emf.createEntityManager();

    public void addEmployee(Employee employee) {
        try {
            em.getTransaction().begin();
            em.persist(employee);
            em.getTransaction().commit();
        }catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
    }

    public List<Employee> getAllEmployees() {
        return em.createQuery("select e from Employee e", Employee.class).getResultList();
    }

    public Employee getEmployeeById(int id) {
        return em.find(Employee.class, id);
    }

    public void updateEmployee(Employee employee) {
        try{
            em.getTransaction().begin();
            em.merge(employee);
            em.getTransaction().commit();
        }catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
    }

    public void deleteEmployeeById(int id) {
        try{
            em.getTransaction().begin();
            em.remove(getEmployeeById(id));
            em.getTransaction().commit();
        }catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
    }

}
