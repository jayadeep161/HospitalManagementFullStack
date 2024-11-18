package com.project.HospitalManagementSystem.Dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

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
    private MultipartFile image;
}
