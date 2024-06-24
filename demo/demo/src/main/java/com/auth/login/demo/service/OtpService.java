package com.auth.login.demo.service;

import com.auth.login.demo.model.Otp;
import com.auth.login.demo.model.User;
import com.auth.login.demo.repo.OtpRepository;
import com.auth.login.demo.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class OtpService {

    @Autowired
    private OtpRepository otpRepository;

    @Autowired
    private UserRepository userRepository;

    public String generateOtp(String email) {
        // Generate OTP
        String otp = generateRandomOtp();

        // Find the user by email
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Create OTP entity and associate it with the user
        Otp otpEntity = new Otp();
        otpEntity.setOtp(otp);
        otpEntity.setExpirationTime(LocalDateTime.now().plusMinutes(1)); // OTP valid for 1 minute
        otpEntity.setUser(user); // Associate the OTP with the user
        otpRepository.save(otpEntity);

        // Associate the OTP entity with the user
        user.setOtp(otpEntity);
        userRepository.save(user);

        return otp;
    }

    public boolean verifyOtp(String email, String otp) {
        Otp otpEntity = otpRepository.findByUserEmailAndOtp(email, otp);

        if (otpEntity != null && otpEntity.getExpirationTime().isAfter(LocalDateTime.now())) {
            // OTP is valid
            return true;
        }

        return false;
    }

    private String generateRandomOtp() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }
}
