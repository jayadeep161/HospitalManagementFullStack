package com.project.HospitalManagementSystem.Service;

import com.project.HospitalManagementSystem.Dto.AppointmentDto;
import com.project.HospitalManagementSystem.Dto.ResponseDto;
import com.project.HospitalManagementSystem.Entity.Appointment;
import com.project.HospitalManagementSystem.Repository.AppointmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static com.project.HospitalManagementSystem.HospitalConstants.ASSIGNED_TO_DOCTOR;

@Service
public class AdminService implements AppointmentManagement{


    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResponseEntity<ResponseDto> updateAppointment(long id, AppointmentDto appointmentDto) {

        try{
            Appointment pendingpatientappointment=appointmentRepository.findById(id).orElseThrow(()->new RuntimeException("not found patient record"));

            pendingpatientappointment.setDoctorName(appointmentDto.getDoctorName());
            pendingpatientappointment.setAppointment(appointmentDto.getAppointment());

            pendingpatientappointment.setPrescription(ASSIGNED_TO_DOCTOR);

            pendingpatientappointment.setAppointmentStatus(ASSIGNED_TO_DOCTOR);

            pendingpatientappointment.setAppointmentPrice(ASSIGNED_TO_DOCTOR);

            appointmentRepository.save(pendingpatientappointment);

            return new ResponseEntity<>(new ResponseDto("200","appointment updated by admin"), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(new ResponseDto("404",e.getMessage()),HttpStatus.NOT_FOUND);

        }
    }
}
