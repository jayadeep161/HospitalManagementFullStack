package com.project.HospitalManagementSystem.Service;

import com.project.HospitalManagementSystem.Entity.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserDetail implements UserDetails {

    private Users users;
    private String user_email;
    private String password;

    public UserDetail(Users users) {
        this.users = users;
        this.user_email = users.getEmailId();
        this.password = users.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(String.valueOf(users.getRole())));
    }

    @Override
    public String getPassword() {
        return  this.password;
    }

    @Override
    public String getUsername() {
        return  this.user_email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
