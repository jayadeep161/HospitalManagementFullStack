package com.project.HospitalManagementSystem.Service;


import com.project.HospitalManagementSystem.Entity.Users;
import com.project.HospitalManagementSystem.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsInfo implements UserDetailsService {


    @Autowired
    private UsersRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> user=userRepository.findByEmailId(username);
       // System.out.println(user.get().getName());
        return user.map(UserDetail::new).orElseThrow(()-> new RuntimeException("user not found"));
    }

}
