package com.project.HospitalManagementSystem.Service;

import com.project.HospitalManagementSystem.Dto.AppointmentDto;
import com.project.HospitalManagementSystem.Dto.ResponseDto;
import com.project.HospitalManagementSystem.Dto.UserRegDto;
import com.project.HospitalManagementSystem.Entity.Appointment;
import com.project.HospitalManagementSystem.Entity.Patient;
import com.project.HospitalManagementSystem.Entity.Role;
import com.project.HospitalManagementSystem.Entity.Users;
import com.project.HospitalManagementSystem.Exception.ResourceNotFound;
import com.project.HospitalManagementSystem.Repository.AppointmentRepository;
import com.project.HospitalManagementSystem.Repository.PatientRepository;
import com.project.HospitalManagementSystem.Repository.UsersRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


import static com.project.HospitalManagementSystem.HospitalConstants.CANCEL;
import static com.project.HospitalManagementSystem.HospitalConstants.NOT_ASSIGNED_TO_DOCTOR;

@Service
public class PatientService implements AppointmentManagement{

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsersRepository usersRepo;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PatientRepository patientRepo;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public ResponseEntity<ResponseDto> updateAppointment(long id, AppointmentDto appointmentDto) {
        try{
            Appointment patientappointment=appointmentRepository.findById(id).orElseThrow(()->new RuntimeException("not found patient record"));


            modelMapper.map(appointmentDto,patientappointment);

            patientappointment.setAppointmentStatus(CANCEL);

            appointmentRepository.save(patientappointment);

            return new ResponseEntity<>(new ResponseDto("200","appointment updated by patient"), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(new ResponseDto("404",e.getMessage()),HttpStatus.NOT_FOUND);

        }
    }

    public ResponseEntity<ResponseDto> registerAppointment(AppointmentDto appointmentDto, Authentication authentication){
        Users appointment_initiated_patient=null;
        try{

            appointment_initiated_patient=usersRepo.findByEmailId(authentication.getName()).orElseThrow(()->new ResourceNotFound("patient doesnt exist"));

                Appointment appointment=new Appointment();
                modelMapper.map(appointmentDto,appointment);
                appointment.setPatientName(appointment_initiated_patient.getFirstname() +" "+appointment_initiated_patient.getLastname());
                appointment.setPatientContact(appointment_initiated_patient.getContactNo());
                appointment.setDoctorName(NOT_ASSIGNED_TO_DOCTOR);
                appointment.setPrescription(NOT_ASSIGNED_TO_DOCTOR);
                appointment.setAppointmentStatus(NOT_ASSIGNED_TO_DOCTOR);
                appointment.setAppointmentPrice(NOT_ASSIGNED_TO_DOCTOR);
                appointmentRepository.save(appointment);
            return new ResponseEntity<>(new ResponseDto("201","appointment created"),HttpStatus.CREATED);
        }
        catch (ResourceNotFound e){
            return new ResponseEntity<>(new ResponseDto("404",e.getMessage()),HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return  new ResponseEntity<>(new ResponseDto("406",e.getMessage()),HttpStatus.NOT_ACCEPTABLE);
        }
    }

    public ResponseEntity<ResponseDto> RegisterPatient(UserRegDto userRegDto){

        try{
            Users user=new Users();
            modelMapper.map(userRegDto,user);
            user.setPassword(passwordEncoder.encode(userRegDto.getPassword()));
            user.setRole(Role.valueOf("ROLE_Patient")); // string -> role conversion //enum.valueOf()
            usersRepo.save(user);
            return new ResponseEntity<>(new ResponseDto("Patient Registered","201"), HttpStatus.CREATED);
        }
        catch (DataIntegrityViolationException e){
            return new ResponseEntity<>(new ResponseDto("409","email already exists"), HttpStatus.CONFLICT);
        }
        catch (Exception e){

            return new ResponseEntity<>(new ResponseDto("500",e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    public ResponseEntity<ResponseDto> updatePatient (Integer id,UserRegDto userRegDto){

        try{
            Users user=usersRepo.findById(id).orElseThrow(RuntimeException::new);
            modelMapper.map(userRegDto,user);
            user.setRole(Role.valueOf("ROLE_Patient"));
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            usersRepo.save(user);
            return new ResponseEntity<>(new ResponseDto("200","user updated successfully"),HttpStatus.OK);

        }
        catch (Exception e){
            return new ResponseEntity<>(new ResponseDto("500",e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    public ResponseEntity<List<Patient>> getAllPatients(){

        List<Patient> patients=patientRepo.findAll();
        return ResponseEntity.ok(patients);
    }
}
