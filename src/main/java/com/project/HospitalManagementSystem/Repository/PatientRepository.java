package com.project.HospitalManagementSystem.Repository;

import com.project.HospitalManagementSystem.Entity.Patient;
import com.project.HospitalManagementSystem.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PatientRepository  extends JpaRepository<Patient,Integer> {

    //List<Optional<Users>> findByEmailId(String email);
}
