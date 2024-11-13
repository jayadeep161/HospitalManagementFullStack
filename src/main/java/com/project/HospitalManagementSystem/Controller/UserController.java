package com.project.HospitalManagementSystem.Controller;

import com.project.HospitalManagementSystem.Dto.ResponseDto;
import com.project.HospitalManagementSystem.Dto.UserRegDto;
import com.project.HospitalManagementSystem.Entity.Doctor;
import com.project.HospitalManagementSystem.Entity.Patient;
import com.project.HospitalManagementSystem.Entity.Users;
import com.project.HospitalManagementSystem.Service.DoctorService;
import com.project.HospitalManagementSystem.Service.PatientService;
import com.project.HospitalManagementSystem.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userservice;

    @Autowired
    private PatientService patientService;

    @Autowired
    private DoctorService doctorService;

    @PostMapping("/api/v1/Doctor/doctorReg")
    public ResponseEntity<ResponseDto> RegisterDoctor(@RequestBody UserRegDto userRegDto){

        return doctorService.RegisterDoctor(userRegDto);
    }

    @GetMapping("/api/v1/Doctor/allDoctors")
    public ResponseEntity<List<Doctor>>  getAllDoctors(){

        return doctorService.getAllDoctors();
    }

    @PutMapping("/api/v1/Doctor/updateDoctor/{id}")
    public ResponseEntity<ResponseDto>  updateDoctor(@PathVariable Integer id, @RequestBody UserRegDto userRegDto){

       return  doctorService.updateDoctor(id,userRegDto);
    }

    @PostMapping("/api/v1/Patient/patientReg")
    public ResponseEntity<ResponseDto>  RegisterPatient(@RequestBody UserRegDto userRegDto){
        return patientService.RegisterPatient(userRegDto);
    }

    @GetMapping("/api/v1/Patient/allPatients")
    public ResponseEntity<List<Patient>>  getAllPatients(){

        return patientService .getAllPatients();
    }

    @PutMapping("/api/v1/Patient/updatePatient/{id}")
    public ResponseEntity<ResponseDto>  updatePatient(@PathVariable Integer id, @RequestBody UserRegDto userRegDto){

        return  patientService.updatePatient(id,userRegDto);
    }

    @PostMapping("/api/v1/auth/login")

    public ResponseEntity<ResponseDto> login(@RequestBody UserRegDto userRegDto){
        return userservice.login(userRegDto);
    }



}
