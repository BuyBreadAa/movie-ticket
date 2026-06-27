package com.movieticket.dto;

import jakarta.validation.constraints.NotBlank;

public class LoginRequest {
    @NotBlank
    public String account;  // phone or email

    @NotBlank
    public String password;
}
