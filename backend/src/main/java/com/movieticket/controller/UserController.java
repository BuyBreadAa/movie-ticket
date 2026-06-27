package com.movieticket.controller;

import com.movieticket.dto.*;
import com.movieticket.entity.User;
import com.movieticket.repo.UserRepo;
import com.movieticket.util.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserRepo userRepo;
    private final PasswordEncoder encoder;
    private final JwtUtil jwtUtil;

    public UserController(UserRepo userRepo, PasswordEncoder encoder, JwtUtil jwtUtil) {
        this.userRepo = userRepo;
        this.encoder = encoder;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ApiResponse<?> register(@Valid @RequestBody RegisterRequest req) {
        if (!req.password.matches(".*[a-zA-Z].*") || !req.password.matches(".*\\d.*")) {
            return ApiResponse.fail(400, "密码需包含字母和数字");
        }
        if (userRepo.existsByPhone(req.phone)) {
            return ApiResponse.fail(400, "手机号已注册");
        }
        User user = new User(req.phone, req.email, encoder.encode(req.password),
                req.nickname != null ? req.nickname : req.phone);
        userRepo.save(user);
        return ApiResponse.ok("注册成功", Map.of("ok", true));
    }

    @PostMapping("/login")
    public ApiResponse<?> login(@Valid @RequestBody LoginRequest req) {
        User user = userRepo.findByPhoneOrEmail(req.account, req.account).orElse(null);
        if (user == null || !encoder.matches(req.password, user.getPasswordHash())) {
            return ApiResponse.fail(401, "账号或密码错误");
        }
        if ("banned".equals(user.getStatus())) {
            return ApiResponse.fail(403, "账号已被封禁");
        }
        String token = jwtUtil.generate(user.getId(), user.getRole());
        return ApiResponse.ok(Map.of("token", token));
    }

    @GetMapping("/profile")
    public ApiResponse<?> getProfile(Authentication auth) {
        User user = userRepo.findById((Long) auth.getPrincipal()).orElse(null);
        if (user == null) return ApiResponse.fail(404, "用户不存在");
        return ApiResponse.ok(user);
    }

    @PutMapping("/profile")
    public ApiResponse<?> updateProfile(Authentication auth, @RequestBody Map<String, String> body) {
        User user = userRepo.findById((Long) auth.getPrincipal()).orElse(null);
        if (user == null) return ApiResponse.fail(404, "用户不存在");
        // 白名单字段，禁止修改 role/points
        if (body.containsKey("nickname")) user.setNickname(body.get("nickname"));
        if (body.containsKey("avatar")) user.setAvatar(body.get("avatar"));
        if (body.containsKey("phone")) user.setPhone(body.get("phone"));
        if (body.containsKey("email")) user.setEmail(body.get("email"));
        userRepo.save(user);
        return ApiResponse.ok(user);
    }

    @PutMapping("/password")
    public ApiResponse<?> updatePassword(Authentication auth, @RequestBody Map<String, String> body) {
        User user = userRepo.findById((Long) auth.getPrincipal()).orElse(null);
        if (user == null) return ApiResponse.fail(404, "用户不存在");
        String newPwd = body.get("password");
        if (newPwd == null || newPwd.length() < 8) return ApiResponse.fail(400, "密码至少8位");
        if (!newPwd.matches(".*[a-zA-Z].*") || !newPwd.matches(".*\\d.*"))
            return ApiResponse.fail(400, "密码需包含字母和数字");
        user.setPasswordHash(encoder.encode(newPwd));
        userRepo.save(user);
        return ApiResponse.ok("修改成功", Map.of("ok", true));
    }

    @GetMapping("/member")
    public ApiResponse<?> getMember(Authentication auth) {
        User user = userRepo.findById((Long) auth.getPrincipal()).orElse(null);
        if (user == null) return ApiResponse.fail(404, "用户不存在");
        String nextLevel = "VIP会员".equals(user.getLevel()) ? "超级VIP" : "VIP会员";
        return ApiResponse.ok(Map.of(
                "level", user.getLevel(),
                "points", user.getPoints(),
                "nextLevel", nextLevel,
                "nextNeed", 2000 - user.getPoints()
        ));
    }
}
