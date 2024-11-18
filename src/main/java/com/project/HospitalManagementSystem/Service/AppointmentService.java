package com.project.HospitalManagementSystem.Service;

import com.project.HospitalManagementSystem.Dto.AppointmentDto;
import com.project.HospitalManagementSystem.Dto.ResponseDto;
import com.project.HospitalManagementSystem.Repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AppointmentService {

        @Autowired
        private AppointmentRepository appointmentRepository;

        private final Map<String,AppointmentManagement>AppointmentStratergy;



        @Autowired
        public AppointmentService(List<AppointmentManagement> stratergy){
            AppointmentStratergy=stratergy.stream()
                    .collect(Collectors.toMap(strat->strat.getClass().getSimpleName().replace("Service",""),strat->strat));

            System.out.println(stratergy);

    }


    public ResponseEntity<ResponseDto> RegisterAppointment(AppointmentDto appointmentDto){

        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();

        try{
            PatientService  appointmentManagement= (PatientService) authentication
                    .getAuthorities()
                    .stream()
                    .filter(authority->authority.toString().equals("ROLE_Patient"))
                    .map(role->AppointmentStratergy.get("Patient"))
                    .findFirst().orElseThrow(()-> new RuntimeException("you are not authorised"));
            return  appointmentManagement.
                    registerAppointment(appointmentDto,authentication);
        }
        catch (Exception e){
            return new ResponseEntity<>(new ResponseDto("403",e.getMessage()), HttpStatus.FORBIDDEN);
        }



    }

    public ResponseEntity<ResponseDto> updateAppointment(long id,AppointmentDto appointmentDto){


        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();

        if(authentication!=null){

            AppointmentManagement appointmentManagement= authentication
                    .getAuthorities()
                    .stream()
                    .map(role->AppointmentStratergy.get(role.toString().replace("ROLE_","")))
                   .peek(System.out::println)
                    .findFirst()
                    .orElseThrow(()-> new RuntimeException("no statergy found"));

            return  appointmentManagement.updateAppointment(id,appointmentDto);

        }

        return new ResponseEntity<>(new ResponseDto("403","login with certain credentials"),HttpStatus.FORBIDDEN);



    }
}
