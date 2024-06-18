package com.auth.login.demo.service;

import com.auth.login.demo.config.JwtTokenUtil;
import com.auth.login.demo.model.User;
import com.auth.login.demo.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private OtpService otpService;

    @Autowired
    private JwtTokenUtil jwtUtil;

    public String register(User user) {
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());

        if (existingUser.isPresent()) {
            // Existing user found, handle accordingly (e.g., throw an exception or return an error message)
            System.out.println("User already exists with this email");
            return "User already exists";
        }

        // Encode the password and save user with unverified status
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setVerified(false);
        userRepository.save(user);

        // Generate OTP
        return otpService.generateOtp(user.getEmail());
    }

    public boolean verifyOtp(String email, String otp) {
        boolean isVerified = otpService.verifyOtp(email, otp);

        if (isVerified) {
            Optional<User> user = userRepository.findByEmail(email);
            if (user.isPresent()) {
                User verifiedUser = user.get();
                verifiedUser.setVerified(true);
                userRepository.save(verifiedUser);
                return true;
            }
        }
        return false;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public boolean authenticate(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.isPresent() && passwordEncoder.matches(password, user.get().getPassword());
    }
}
