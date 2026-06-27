package com.movieticket.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "shows")
public class Show {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long movieId;

    @Column(nullable = false)
    private Long hallId;

    @Column(nullable = false)
    private Long cinemaId;

    @Column(nullable = false)
    private LocalDateTime startTime;

    @Column(nullable = false)
    private LocalDateTime endTime;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private String status = "available";  // available | cancelled

    public Show() {}

    public Long getId() { return id; }
    public Long getMovieId() { return movieId; }
    public Long getHallId() { return hallId; }
    public Long getCinemaId() { return cinemaId; }
    public LocalDateTime getStartTime() { return startTime; }
    public LocalDateTime getEndTime() { return endTime; }
    public Double getPrice() { return price; }
    public String getStatus() { return status; }

    public void setId(Long id) { this.id = id; }
    public void setMovieId(Long movieId) { this.movieId = movieId; }
    public void setHallId(Long hallId) { this.hallId = hallId; }
    public void setCinemaId(Long cinemaId) { this.cinemaId = cinemaId; }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }
    public void setEndTime(LocalDateTime endTime) { this.endTime = endTime; }
    public void setPrice(Double price) { this.price = price; }
    public void setStatus(String status) { this.status = status; }
}
