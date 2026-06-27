package com.movieticket.controller;

import com.movieticket.dto.ApiResponse;
import com.movieticket.entity.*;
import com.movieticket.repo.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final OrderRepo orderRepo;
    private final UserRepo userRepo;
    private final MovieRepo movieRepo;
    private final ShowRepo showRepo;

    public AdminController(OrderRepo orderRepo, UserRepo userRepo, MovieRepo movieRepo, ShowRepo showRepo) {
        this.orderRepo = orderRepo;
        this.userRepo = userRepo;
        this.movieRepo = movieRepo;
        this.showRepo = showRepo;
    }

    @GetMapping("/dashboard")
    public ApiResponse<?> dashboard() {
        long todayOrders = orderRepo.count();
        double todayBoxOffice = orderRepo.findAll().stream()
                .filter(o -> "PAID".equals(o.getStatus()))
                .mapToDouble(Order::getPayPrice).sum();
        long activeUsers = userRepo.count();
        return ApiResponse.ok(Map.of(
                "todayOrders", todayOrders,
                "todayBoxOffice", Math.round(todayBoxOffice * 100) / 100.0,
                "activeUsers", activeUsers,
                "occupancyRate", 0.72
        ));
    }

    @GetMapping("/movies")
    public ApiResponse<?> movies() {
        return ApiResponse.ok(Map.of("list", movieRepo.findAll(), "total", movieRepo.count()));
    }

    @PostMapping("/movies")
    public ApiResponse<?> createMovie(@RequestBody Movie movie) {
        movieRepo.save(movie);
        return ApiResponse.ok("创建成功", movie);
    }

    @PutMapping("/movies/{id}")
    public ApiResponse<?> updateMovie(@PathVariable Long id, @RequestBody Movie data) {
        Movie movie = movieRepo.findById(id).orElse(null);
        if (movie == null) return ApiResponse.fail(404, "影片不存在");
        if (data.getTitle() != null) movie.setTitle(data.getTitle());
        if (data.getDirector() != null) movie.setDirector(data.getDirector());
        if (data.getCastList() != null) movie.setCastList(data.getCastList());
        if (data.getType() != null) movie.setType(data.getType());
        if (data.getStatus() != null) movie.setStatus(data.getStatus());
        movieRepo.save(movie);
        return ApiResponse.ok("更新成功", movie);
    }

    @DeleteMapping("/movies/{id}")
    public ApiResponse<?> deleteMovie(@PathVariable Long id) {
        movieRepo.deleteById(id);
        return ApiResponse.ok("删除成功", null);
    }

    @GetMapping("/schedules")
    public ApiResponse<?> schedules() {
        return ApiResponse.ok(Map.of("list", showRepo.findAll(), "total", showRepo.count()));
    }

    @PostMapping("/schedules")
    public ApiResponse<?> createSchedule(@RequestBody Show show) {
        showRepo.save(show);
        return ApiResponse.ok("创建成功", show);
    }

    @GetMapping("/orders")
    public ApiResponse<?> orders(@RequestParam(required = false) String status) {
        List<Order> list = status != null ? orderRepo.findByStatus(status) : orderRepo.findAll();
        return ApiResponse.ok(Map.of("list", list, "total", list.size()));
    }

    @PostMapping("/orders/{id}/refund")
    public ApiResponse<?> refund(@PathVariable Long id) {
        Order order = orderRepo.findById(id).orElse(null);
        if (order == null) return ApiResponse.fail(404, "订单不存在");
        if (!"PAID".equals(order.getStatus())) return ApiResponse.fail(400, "仅已支付订单可退款");
        order.setStatus("REFUNDED");
        orderRepo.save(order);
        return ApiResponse.ok("退款成功", Map.of("status", "REFUNDED"));
    }

    @GetMapping("/users")
    public ApiResponse<?> users() {
        return ApiResponse.ok(Map.of("list", userRepo.findAll(), "total", userRepo.count()));
    }

    @PutMapping("/users/{id}/status")
    public ApiResponse<?> updateUserStatus(@PathVariable Long id, @RequestBody Map<String, String> body) {
        User user = userRepo.findById(id).orElse(null);
        if (user == null) return ApiResponse.fail(404, "用户不存在");
        user.setStatus(body.get("status"));
        userRepo.save(user);
        return ApiResponse.ok("操作成功", user);
    }
}
