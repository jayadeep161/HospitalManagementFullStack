package com.project.HospitalManagementSystem.Repository;

import com.project.HospitalManagementSystem.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UsersRepository  extends JpaRepository<Users,Integer> {

    Optional<Users> findByEmailId(String email);

}
