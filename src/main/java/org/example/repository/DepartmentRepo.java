package org.example.repository;

import org.example.dao.DepartmentDAO;
import org.example.pojo.Department;

import java.util.List;

public class DepartmentRepo implements IDepartmentRepo{
    @Override
    public List<Department> getAllDepartments() {
        DepartmentDAO departmentDAO = new DepartmentDAO();
        return departmentDAO.getAllDepartments();
    }
}
