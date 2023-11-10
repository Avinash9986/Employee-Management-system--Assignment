package com.example.Employee.Management.System.assignment.db.mongo.models;


import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.mongodb.core.mapping.Document;

import java.security.Timestamp;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Document(collection = "department")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department  {

    @Id
    String departmentId ;

    String departmentName ;

    Timestamp createdAt ;

    Timestamp updatedAt ;
}
