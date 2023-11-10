package com.example.Employee.Management.System.assignment.db;

public class DepartmentNotFoundExeption extends RuntimeException{
    public DepartmentNotFoundExeption(String message){
        super(message);
    }
}
