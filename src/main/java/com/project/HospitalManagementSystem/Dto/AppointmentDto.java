package com.project.HospitalManagementSystem.Dto;

import lombok.Data;

import java.util.Date;

@Data
public class AppointmentDto {
    private String patientName;
    private String patientContact;
    private String problem;
    private String doctorName;
    private String prescription;
    private Date appointmentTaken;
    private Date appointment;
    private String appointmentStatus;
    private String appointmentPrice;
}
