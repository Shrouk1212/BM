package com.banquemisr.challenge05.TaskManagement.controller;

import com.banquemisr.challenge05.TaskManagement.model.AuthRequest;
import com.banquemisr.challenge05.TaskManagement.model.AuthResponse;
import com.banquemisr.challenge05.TaskManagement.service.Impl.MyUserDetailsService;
import com.banquemisr.challenge05.TaskManagement.utils.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private MyUserDetailsService userDetsildService;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthRequest authRequest) {
        // Authenticate the user
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );

        // Load user details
        final UserDetails userDetails = userDetsildService.loadUserByUsername(authRequest.getUsername());

        // Generate JWT token
        String jwt = jwtTokenProvider.generateToken(userDetails);

        // Return the JWT token in the response
        return ResponseEntity.ok(new AuthResponse(jwt));
    }
}
