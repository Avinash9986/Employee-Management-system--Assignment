package com.example.Employee.Management.System.assignment.db.mongo.Controller;

import com.example.Employee.Management.System.assignment.db.mongo.Service.DepartmentSerive;
import com.example.Employee.Management.System.assignment.db.mongo.models.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Department")
public class DepartmentController {

    @Autowired
    DepartmentSerive departmentSerive ;

    @PostMapping("/add")
    public ResponseEntity addDepartment(@PathVariable("departmentName") String departmentName){
        Department department = departmentSerive.addDepartment(departmentName) ;
        return new ResponseEntity<>(department, HttpStatus.CREATED) ;
    }

    @GetMapping("/get")
    public ResponseEntity getDepartment(@PathVariable("departmentName") String departmentName){

        try {
            Department department = departmentSerive.getDepartment(departmentName) ;
            return new ResponseEntity<>(department,HttpStatus.FOUND) ;
        }
        catch (Exception e){
            return new ResponseEntity<>("bad Request",HttpStatus.BAD_REQUEST) ;
        }
    }


    @PutMapping("/update")
    public void updateName(@PathVariable("oldName") String oldName,@PathVariable("newName")String newName){
        departmentSerive.updateName(oldName,newName) ;
    }

    @DeleteMapping("Delete")
    public void deleteDepartment(@PathVariable("name") String departmentName){
        departmentSerive.deleteDepartment(departmentName) ;
    }


}
