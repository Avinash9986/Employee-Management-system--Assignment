package com.example.Employee.Management.System.assignment.db.postgres;

public class EmployeeNotFoundException extends RuntimeException{
    public EmployeeNotFoundException(String message){

        super(message) ;
    }
}
