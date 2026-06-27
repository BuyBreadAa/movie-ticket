package com.movieticket.dto;

import jakarta.validation.constraints.NotNull;
import java.util.List;

public class LockSeatsRequest {
    @NotNull
    public Long showId;

    @NotNull
    public List<String> seats;
}
