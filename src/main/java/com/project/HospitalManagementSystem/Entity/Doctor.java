package com.project.HospitalManagementSystem.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import org.hibernate.annotations.Immutable;


@Entity
@Table(name="doctor")
@Immutable
@Getter
public class Doctor {
    @Id
    private Integer id;
    private String firstname;
    private String lastname;
    private String emailId;
    private String password;
    private String contactNo;
    private Integer age;
    private String address;
    private Integer  experience;
    private String specialist;
    private String url;
}
