package org.example.repository;

import org.example.pojo.Employee;

import java.util.List;

public interface IEmployeeRepo {
    public void addEmployee(Employee employee);

    public List<Employee> getAllEmployees();

}
