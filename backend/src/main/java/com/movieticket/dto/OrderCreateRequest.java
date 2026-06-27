package com.movieticket.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public class OrderCreateRequest {
    @NotNull
    public Long showId;

    @NotNull
    public List<String> seats;

    @NotBlank
    public String lockToken;
}
