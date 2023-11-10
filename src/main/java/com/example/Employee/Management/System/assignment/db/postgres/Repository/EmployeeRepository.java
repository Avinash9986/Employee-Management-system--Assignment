package com.example.Employee.Management.System.assignment.db.postgres.Repository;

import com.example.Employee.Management.System.assignment.db.postgres.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    Employee findByemail(String email) ;
    void deleteByemail(String email) ;
}
