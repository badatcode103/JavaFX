package org.example.repository;

import org.example.dao.EmployeeDAO;
import org.example.pojo.Employee;

import java.util.List;

public class EmployeeRepo implements IEmployeeRepo {
    @Override
    public void addEmployee(Employee employee) {
        EmployeeDAO employeeDAO = new EmployeeDAO();
        employeeDAO.addEmployee(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        EmployeeDAO employeeDAO = new EmployeeDAO();
        return employeeDAO.getAllEmployees();
    }
}
