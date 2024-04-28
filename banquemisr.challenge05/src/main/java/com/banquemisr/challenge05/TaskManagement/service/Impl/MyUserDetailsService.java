package com.banquemisr.challenge05.TaskManagement.service.Impl;

import com.banquemisr.challenge05.TaskManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Retrieve user details from the database based on the provided username
        com.banquemisr.challenge05.TaskManagement.Entity.User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
System.out.println(user.getRole().name());
        // Construct a UserDetails object with the retrieved user details and roles
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword()) // Assuming password is stored securely (e.g., hashed)
                .authorities(Collections.singleton(new SimpleGrantedAuthority(user.getRole().name()))) // Set user role as a GrantedAuthority
                .build();
    }
}
