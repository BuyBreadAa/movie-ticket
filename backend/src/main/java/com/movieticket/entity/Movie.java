package com.movieticket.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "movies")
public class Movie {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String poster;
    private String trailer;
    private String director;
    @Column(length = 1000)
    private String castList;
    private String type;
    private String region;
    private Integer year;
    @Column(length = 2000)
    private String synopsis;
    private Double rating = 0.0;
    private String status = "showing";  // showing | coming | ended
    private LocalDateTime createdAt = LocalDateTime.now();

    public Movie() {}

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getPoster() { return poster; }
    public String getTrailer() { return trailer; }
    public String getDirector() { return director; }
    public String getCastList() { return castList; }
    public String getType() { return type; }
    public String getRegion() { return region; }
    public Integer getYear() { return year; }
    public String getSynopsis() { return synopsis; }
    public Double getRating() { return rating; }
    public String getStatus() { return status; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public void setId(Long id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setPoster(String poster) { this.poster = poster; }
    public void setTrailer(String trailer) { this.trailer = trailer; }
    public void setDirector(String director) { this.director = director; }
    public void setCastList(String castList) { this.castList = castList; }
    public void setType(String type) { this.type = type; }
    public void setRegion(String region) { this.region = region; }
    public void setYear(Integer year) { this.year = year; }
    public void setSynopsis(String synopsis) { this.synopsis = synopsis; }
    public void setRating(Double rating) { this.rating = rating; }
    public void setStatus(String status) { this.status = status; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
