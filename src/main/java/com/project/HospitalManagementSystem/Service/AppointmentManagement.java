package com.project.HospitalManagementSystem.Service;

import com.project.HospitalManagementSystem.Dto.AppointmentDto;
import com.project.HospitalManagementSystem.Dto.ResponseDto;
import com.project.HospitalManagementSystem.Entity.Appointment;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface AppointmentManagement {

    ResponseEntity<ResponseDto> updateAppointment(long id, AppointmentDto appointmentDto);

}
