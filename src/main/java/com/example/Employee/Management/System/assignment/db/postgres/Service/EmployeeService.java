package com.example.Employee.Management.System.assignment.db.postgres.Service;

import com.example.Employee.Management.System.assignment.db.postgres.EmployeeNotFoundException;
import com.example.Employee.Management.System.assignment.db.postgres.Repository.EmployeeRepository;
import com.example.Employee.Management.System.assignment.db.postgres.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository ;
    public Employee addEmployee(Employee employee) {

        Employee employee1 = employeeRepository.save(employee) ;
        return employee1 ;
    }


    public Employee getEmployee(String email) {
        Employee employee = employeeRepository.findByemail(email) ;
        if (employee == null){
            throw  new EmployeeNotFoundException("Employee Not Found") ;
        }
        return employee ;
    }

    public void updateemail(String newemail, String oldemail) {
        Employee employee = employeeRepository.findByemail(oldemail) ;
        employee.setEmail(newemail);
    }

    public void deleteEmployee(String email) {
        employeeRepository.deleteByemail(email);
    }
}
