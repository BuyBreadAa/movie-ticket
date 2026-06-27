package com.movieticket.dto;

import jakarta.validation.constraints.NotBlank;

public class PayRequest {
    @NotBlank
    public String payToken;
    public Integer pointsUsed = 0;
}
