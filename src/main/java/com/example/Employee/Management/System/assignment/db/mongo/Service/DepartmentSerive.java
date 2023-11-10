package com.example.Employee.Management.System.assignment.db.mongo.Service;

import com.example.Employee.Management.System.assignment.db.DepartmentNotFoundExeption;
import com.example.Employee.Management.System.assignment.db.mongo.Repository.DepartmentRepository;
import com.example.Employee.Management.System.assignment.db.mongo.models.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DepartmentSerive {
    @Autowired
    DepartmentRepository departmentRepository ;

    public Department addDepartment(String departmentName) {
        Department department = Department.builder().departmentId(UUID.randomUUID().toString()).departmentName(departmentName).build();
        Department savedDepartment = departmentRepository.save(department) ;
        return savedDepartment ;
    }

    public Department getDepartment(String departmentName) {
        Department department = departmentRepository.findByDepartmentName(departmentName) ;
        if (department == null) {
            throw new DepartmentNotFoundExeption("Department not found") ;
        }
        return department ;
    }

    public void updateName(String oldName, String newName) {
        Department department = departmentRepository.findByDepartmentName(oldName) ;
        department.setDepartmentName(newName);
    }

    public void deleteDepartment(String departmentName) {
        departmentRepository.deleteByDepartmentName(departmentName) ;
    }
}
