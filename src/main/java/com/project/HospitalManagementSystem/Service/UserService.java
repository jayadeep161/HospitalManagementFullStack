package com.project.HospitalManagementSystem.Service;

import com.project.HospitalManagementSystem.Config.JWTUtil;
import com.project.HospitalManagementSystem.Dto.ResponseDto;
import com.project.HospitalManagementSystem.Dto.UserRegDto;

import com.project.HospitalManagementSystem.Entity.Doctor;
import com.project.HospitalManagementSystem.Entity.Patient;
import com.project.HospitalManagementSystem.Entity.Role;
import com.project.HospitalManagementSystem.Entity.Users;
import com.project.HospitalManagementSystem.Repository.PatientRepository;
import com.project.HospitalManagementSystem.Repository.UsersRepository;
import com.project.HospitalManagementSystem.Repository.DoctorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UsersRepository usersRepo;

    @Autowired
    private DoctorRepository doctorRepo;

    @Autowired
    private PatientRepository patientRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private ModelMapper modelMapper;





    public ResponseEntity<ResponseDto> login(UserRegDto userRegDto){
        try{
            Authentication authentication= authenticationManager.
                    authenticate(new UsernamePasswordAuthenticationToken(userRegDto.getEmailId(),userRegDto.getPassword()));
           Users loginuser=usersRepo.findByEmailId(userRegDto.getEmailId())
                    .orElseThrow(()-> new RuntimeException("user not found"));

            String token=jwtUtil.generateToken(loginuser.getEmailId());

            return new ResponseEntity<>(new ResponseDto("200",token),HttpStatus.OK);

        }
        catch (Exception e){
            return new ResponseEntity<>(new ResponseDto("500",e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }


}
