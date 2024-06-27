package com.auth.login.demo.controller;

import com.auth.login.demo.model.Otp;
import com.auth.login.demo.model.User;
import com.auth.login.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        String otp = userService.register(user);
        return otp;
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verifyOtp(@RequestBody Otp otp) {
        String email = otp.getEmail();
        String new_otp = otp.getOtp();

        boolean isVerified = userService.verifyOtp(email, new_otp);

        // Log the verification result
        if (isVerified) {
            log.info("OTP verification successful for email: {}", email);
            return ResponseEntity.ok("OTP verified successfully.");
        } else {
            log.warn("Invalid OTP verification attempt for email: {}", email);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid OTP.");
        }
    }
}
