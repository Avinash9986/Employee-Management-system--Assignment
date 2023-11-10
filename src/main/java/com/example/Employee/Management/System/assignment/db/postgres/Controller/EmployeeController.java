package com.example.Employee.Management.System.assignment.db.postgres.Controller;

import com.example.Employee.Management.System.assignment.db.postgres.Service.EmployeeService;
import com.example.Employee.Management.System.assignment.db.postgres.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService ;

    @PostMapping("/add")
    public ResponseEntity addEmployee(@RequestBody Employee employee){
        Employee employee1 = employeeService.addEmployee(employee) ;
        return new ResponseEntity<>(employee1, HttpStatus.CREATED) ;
        
    }

    @GetMapping("/get")
    public ResponseEntity getEmployee(@PathVariable("email") String email){
        try {
            Employee employee = employeeService.getEmployee(email) ;
            return new ResponseEntity<>(employee,HttpStatus.FOUND) ;
        }
        catch (Exception e){
            return new ResponseEntity<>("Employee Not Found" , HttpStatus.BAD_REQUEST) ;
        }
    }

    @PutMapping("/update")
    public void updateemail(@PathVariable("newemail") String newemail, @PathVariable("oldemail") String oldemail){
        employeeService.updateemail(newemail,oldemail) ;
    }

    @DeleteMapping("/delete")
    public void deleteEmployee(@PathVariable("email") String email){
        employeeService.deleteEmployee(email) ;
    }
}
