package com.movieticket.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String phone;

    @Column(unique = true)
    private String email;

    @Column(nullable = false)
    private String passwordHash;

    private String nickname;
    private String avatar;

    @Column(nullable = false)
    private String role = "user";  // user | admin

    @Column(nullable = false)
    private String level = "普通会员";  // 普通会员 | VIP会员 | 超级VIP

    @Column(nullable = false)
    private Integer points = 0;

    @Column(nullable = false)
    private String status = "active";  // active | banned

    private LocalDateTime createdAt = LocalDateTime.now();

    public User() {}

    public User(String phone, String email, String passwordHash, String nickname) {
        this.phone = phone;
        this.email = email;
        this.passwordHash = passwordHash;
        this.nickname = nickname;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public String getPasswordHash() { return passwordHash; }
    public String getNickname() { return nickname; }
    public String getAvatar() { return avatar; }
    public String getRole() { return role; }
    public String getLevel() { return level; }
    public Integer getPoints() { return points; }
    public String getStatus() { return status; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public void setId(Long id) { this.id = id; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setEmail(String email) { this.email = email; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
    public void setNickname(String nickname) { this.nickname = nickname; }
    public void setAvatar(String avatar) { this.avatar = avatar; }
    public void setRole(String role) { this.role = role; }
    public void setLevel(String level) { this.level = level; }
    public void setPoints(Integer points) { this.points = points; }
    public void setStatus(String status) { this.status = status; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
