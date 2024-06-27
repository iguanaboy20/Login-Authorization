package com.auth.login.demo.repo;

import com.auth.login.demo.model.Otp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OtpRepository extends JpaRepository<Otp, Long> {

    Otp findByUserEmailAndOtp(String email, String otp);
}
