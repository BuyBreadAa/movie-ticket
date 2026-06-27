package com.movieticket.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long showId;

    @Column(nullable = false)
    private String seats;  // JSON: ["5-6","5-7"]

    @Column(nullable = false)
    private Double totalPrice;

    private Double payPrice;
    private Integer pointsUsed = 0;

    @Column(unique = true)
    private String payToken;

    @Column(unique = true)
    private String lockToken;

    @Column(nullable = false)
    private String status = "PENDING";  // PENDING | PAID | CANCELLED | EXPIRED | REFUNDED

    private String ticketCode;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime paidAt;

    public Order() {}

    public Long getId() { return id; }
    public Long getUserId() { return userId; }
    public Long getShowId() { return showId; }
    public String getSeats() { return seats; }
    public Double getTotalPrice() { return totalPrice; }
    public Double getPayPrice() { return payPrice; }
    public Integer getPointsUsed() { return pointsUsed; }
    public String getPayToken() { return payToken; }
    public String getLockToken() { return lockToken; }
    public String getStatus() { return status; }
    public String getTicketCode() { return ticketCode; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getPaidAt() { return paidAt; }

    public void setId(Long id) { this.id = id; }
    public void setUserId(Long userId) { this.userId = userId; }
    public void setShowId(Long showId) { this.showId = showId; }
    public void setSeats(String seats) { this.seats = seats; }
    public void setTotalPrice(Double totalPrice) { this.totalPrice = totalPrice; }
    public void setPayPrice(Double payPrice) { this.payPrice = payPrice; }
    public void setPointsUsed(Integer pointsUsed) { this.pointsUsed = pointsUsed; }
    public void setPayToken(String payToken) { this.payToken = payToken; }
    public void setLockToken(String lockToken) { this.lockToken = lockToken; }
    public void setStatus(String status) { this.status = status; }
    public void setTicketCode(String ticketCode) { this.ticketCode = ticketCode; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public void setPaidAt(LocalDateTime paidAt) { this.paidAt = paidAt; }
}
