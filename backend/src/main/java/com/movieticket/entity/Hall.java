package com.movieticket.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "halls")
public class Hall {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long cinemaId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String type;  // IMAX | 杜比 | 普通

    @Column(columnDefinition = "TEXT")
    private String seatLayout;  // JSON: {"rows":8,"cols":12}

    public Hall() {}

    public Long getId() { return id; }
    public Long getCinemaId() { return cinemaId; }
    public String getName() { return name; }
    public String getType() { return type; }
    public String getSeatLayout() { return seatLayout; }

    public void setId(Long id) { this.id = id; }
    public void setCinemaId(Long cinemaId) { this.cinemaId = cinemaId; }
    public void setName(String name) { this.name = name; }
    public void setType(String type) { this.type = type; }
    public void setSeatLayout(String seatLayout) { this.seatLayout = seatLayout; }
}
