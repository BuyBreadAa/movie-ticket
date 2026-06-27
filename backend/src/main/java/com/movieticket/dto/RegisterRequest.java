package com.movieticket.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegisterRequest {
    @NotBlank
    public String phone;

    public String email;

    @NotBlank @Size(min = 8, message = "密码长度至少8位")
    public String password;

    public String nickname;
}
