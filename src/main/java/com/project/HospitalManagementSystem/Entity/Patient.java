package com.project.HospitalManagementSystem.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name="patient")
@Getter
public class Patient {
    @Id
    private Integer id;
    private String firstname;
    private String lastname;
    private String emailId;
    private String password;
    private String contactNo;
    private String gender;
    private String bloodGroup;
    private int age;
    private String address;
}
