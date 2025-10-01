package org.example.pojo;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String departmentName;

    @OneToMany(mappedBy = "department")
    private List<Employee>  employees ;

    public Department(String departmentName) {
        this.departmentName = departmentName;
    }

    public Department() {

    }

    public String getDepartmentName() {
        return departmentName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
