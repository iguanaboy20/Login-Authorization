package com.auth.login.demo.model;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class JwtRequest {
    private String email;
    private String password;

}