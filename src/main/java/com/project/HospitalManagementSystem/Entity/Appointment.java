package com.project.HospitalManagementSystem.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String patientName;
    private String patientContact;
    private String Problem;
    private String doctorName;
    private String prescription;
    private Date appointmentTaken;
    private Date appointment;
    private String appointmentStatus;
    private String appointmentPrice;

}
