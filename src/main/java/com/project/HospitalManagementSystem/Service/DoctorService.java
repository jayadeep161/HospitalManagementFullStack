package com.project.HospitalManagementSystem.Service;

import com.project.HospitalManagementSystem.Dto.AppointmentDto;
import com.project.HospitalManagementSystem.Dto.ResponseDto;
import com.project.HospitalManagementSystem.Dto.UserRegDto;
import com.project.HospitalManagementSystem.Entity.Appointment;
import com.project.HospitalManagementSystem.Entity.Doctor;
import com.project.HospitalManagementSystem.Entity.Role;
import com.project.HospitalManagementSystem.Entity.Users;
import com.project.HospitalManagementSystem.Repository.AppointmentRepository;
import com.project.HospitalManagementSystem.Repository.DoctorRepository;
import com.project.HospitalManagementSystem.Repository.UsersRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class DoctorService implements AppointmentManagement{

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsersRepository usersRepo;


    @Autowired
    private  ModelMapper modelMapper;

    @Autowired
    private DoctorRepository doctorRepo;

    @Override
    public ResponseEntity<ResponseDto> updateAppointment(long id, AppointmentDto appointmentDto) {

        try{
            Appointment doctorassignedpatientappointment=appointmentRepository.findById(id).orElseThrow(()->new RuntimeException("not found patient record"));


            modelMapper.map(appointmentDto,doctorassignedpatientappointment);

            appointmentRepository.save(doctorassignedpatientappointment);

            return new ResponseEntity<>(new ResponseDto("200","appointment updated by doctor"), HttpStatus.OK);
        }
       catch (Exception e){
           return new ResponseEntity<>(new ResponseDto("404",e.getMessage()),HttpStatus.NOT_FOUND);

       }
    }

    public ResponseEntity<ResponseDto> RegisterDoctor(MultipartFile file, UserRegDto userRegDto){

        System.out.println("name of the file "+file.getOriginalFilename());

        try{
            Users user=new Users();
            System.out.println(userRegDto.getEmailId());
            user.setFirstname(userRegDto.getFirstname());
            user.setLastname(userRegDto.getLastname());
            user.setEmailId(userRegDto.getEmailId());
            user.setPassword(userRegDto.getPassword());
            user.setAddress(userRegDto.getAddress());
            user.setContactNo(userRegDto.getContactNo());
            user.setAge(userRegDto.getAge());
            user.setExperience(userRegDto.getExperience());
            user.setSpecialist(userRegDto.getSpecialist());
            user.setRole(Role.valueOf("ROLE_Doctor"));
            user.setUrl(file.getOriginalFilename());
            user.setPassword(passwordEncoder.encode(userRegDto.getPassword()));

            usersRepo.save(user);


            return new ResponseEntity<>(new ResponseDto("Doctor Registered","201"), HttpStatus.CREATED);
        }
        catch (DataIntegrityViolationException e){
            return new ResponseEntity<>(new ResponseDto("409","email already exists"), HttpStatus.CONFLICT);
        }
        catch (Exception e){

            return new ResponseEntity<>(new ResponseDto("500",e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
    public ResponseEntity<ResponseDto> updateDoctor(Integer id,UserRegDto userRegDto){

        try{
            Users user=usersRepo.findById(id).orElseThrow(RuntimeException::new);

            modelMapper.map(userRegDto,user);
            user.setRole(Role.valueOf("ROLE_Doctor"));
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            usersRepo.save(user);
            return new ResponseEntity<>(new ResponseDto("200","Doctor updated successfully"),HttpStatus.OK);


        }
        catch (Exception e){
            return new ResponseEntity<>(new ResponseDto("500",e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    public ResponseEntity<List<Doctor>> getAllDoctors(){

        List<Doctor> doctors=doctorRepo.findAll();
        return ResponseEntity.ok(doctors);
    }

}
