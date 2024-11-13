package com.project.HospitalManagementSystem.Entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table
@Getter
@Setter
@Builder
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(nullable = false)
   private String firstname;
   private String lastname;
    @Column(nullable = false,unique =true)
   private String emailId;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
   private String contactNo;
   private Integer age;
    private String address;
    @Enumerated(EnumType.STRING)
    private  Role role;  //default handle
    private Integer  experience;
    private String specialist;
    private String bloodGroup;
    private String url;
    private String gender;




}
