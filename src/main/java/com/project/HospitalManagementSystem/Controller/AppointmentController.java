package com.project.HospitalManagementSystem.Controller;

import com.project.HospitalManagementSystem.Dto.AppointmentDto;
import com.project.HospitalManagementSystem.Dto.ResponseDto;
import com.project.HospitalManagementSystem.Service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/api/v1/Appointment/appointmentReg")
    public ResponseEntity<ResponseDto> registerAppointment(@RequestBody AppointmentDto appointmentDto){
        return appointmentService.RegisterAppointment(appointmentDto);
    }

    @PutMapping("/api/v1/Appointment/updateappointmentReg/{id}")
    public ResponseEntity<ResponseDto> updateAppointment(@RequestBody AppointmentDto appointmentDto,@PathVariable  long id){
        return appointmentService.updateAppointment(id,appointmentDto);
    }
}
