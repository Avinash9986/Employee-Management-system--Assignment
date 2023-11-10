package com.example.Employee.Management.System.assignment.db.postgres.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.security.Timestamp;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employee")
public class Employee {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long Id ;

    String firstName ;

    String lastName ;

    String email ;

    String departmentId ;

    Timestamp createdAt ;

    Timestamp updatedAt ;
}
