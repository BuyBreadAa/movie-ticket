package com.movieticket.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "cinemas")
public class Cinema {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String address;
    private String contact;
    private String facilities;
    private String city;
    private String region;

    public Cinema() {}

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getAddress() { return address; }
    public String getContact() { return contact; }
    public String getFacilities() { return facilities; }
    public String getCity() { return city; }
    public String getRegion() { return region; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setAddress(String address) { this.address = address; }
    public void setContact(String contact) { this.contact = contact; }
    public void setFacilities(String facilities) { this.facilities = facilities; }
    public void setCity(String city) { this.city = city; }
    public void setRegion(String region) { this.region = region; }
}
