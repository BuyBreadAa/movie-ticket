package com.movieticket.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "seat_locks")
public class SeatLock {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long showId;

    @Column(nullable = false)
    private String seatNo;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String lockToken;

    @Column(nullable = false)
    private LocalDateTime expireAt;

    public SeatLock() {}

    public Long getId() { return id; }
    public Long getShowId() { return showId; }
    public String getSeatNo() { return seatNo; }
    public Long getUserId() { return userId; }
    public String getLockToken() { return lockToken; }
    public LocalDateTime getExpireAt() { return expireAt; }

    public void setId(Long id) { this.id = id; }
    public void setShowId(Long showId) { this.showId = showId; }
    public void setSeatNo(String seatNo) { this.seatNo = seatNo; }
    public void setUserId(Long userId) { this.userId = userId; }
    public void setLockToken(String lockToken) { this.lockToken = lockToken; }
    public void setExpireAt(LocalDateTime expireAt) { this.expireAt = expireAt; }
}
