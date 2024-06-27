package com.auth.login.demo.controller;

import com.auth.login.demo.model.JwtRequest;
import com.auth.login.demo.model.JwtResponse;
import com.auth.login.demo.model.User;
import com.auth.login.demo.repo.UserRepository;
import com.auth.login.demo.security.JwtHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class AuthController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private JwtHelper helper;

    private Logger logger = LoggerFactory.getLogger(AuthController.class);


    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) throws NoSuchAlgorithmException {

//        this.doAuthenticate(request.getEmail(), request.getPassword());

        Optional<User> existingUser = userRepository.findByEmail(request.getEmail());



//        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = this.helper.generateToken(existingUser.get().getUsername());
        JwtResponse response = JwtResponse.builder()
                .jwtToken(token)
                .username(existingUser.get().getUsername()).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
