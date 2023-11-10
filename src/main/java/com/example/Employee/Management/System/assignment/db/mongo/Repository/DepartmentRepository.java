package com.example.Employee.Management.System.assignment.db.mongo.Repository;

import com.example.Employee.Management.System.assignment.db.mongo.models.Department;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends MongoRepository<Department,String> {
     Department findByDepartmentName(String departmentName) ;
     void deleteByDepartmentName(String departmentName) ;
}
