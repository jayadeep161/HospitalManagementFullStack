package com.project.HospitalManagementSystem.Dto;

import com.project.HospitalManagementSystem.Entity.Role;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class UserRegDto {
    private String firstname;
    private String lastname;
    private String emailId;
    private String password;
    private String contactNo;
    private Integer age;
    private String address;
    private String role;  //default handle
    private Integer  experience;
    private String specialist;
    private String bloodGroup;
    private String url;
    private String gender;
}
