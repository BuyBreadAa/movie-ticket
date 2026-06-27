package com.movieticket.controller;

import com.movieticket.dto.*;
import com.movieticket.entity.*;
import com.movieticket.repo.*;
import org.springframework.transaction.annotation.Transactional;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final OrderRepo orderRepo;
    private final ShowRepo showRepo;
    private final SeatLockRepo seatLockRepo;
    private final UserRepo userRepo;
    private final MovieRepo movieRepo;

    public OrderController(OrderRepo orderRepo, ShowRepo showRepo, SeatLockRepo seatLockRepo,
                           UserRepo userRepo, MovieRepo movieRepo) {
        this.orderRepo = orderRepo;
        this.showRepo = showRepo;
        this.seatLockRepo = seatLockRepo;
        this.userRepo = userRepo;
        this.movieRepo = movieRepo;
    }

    @PostMapping("/lock-seats")
    public ApiResponse<?> lockSeats(Authentication auth, @Valid @RequestBody LockSeatsRequest req) {
        Long userId = (Long) auth.getPrincipal();
        String lockToken = UUID.randomUUID().toString();
        LocalDateTime expireAt = LocalDateTime.now().plusMinutes(15);

        for (String seat : req.seats) {
            // 检查该座位是否已被锁定（未过期）
            List<SeatLock> existing = seatLockRepo.findByShowIdAndSeatNo(req.showId, seat);
            boolean locked = existing.stream().anyMatch(l -> l.getExpireAt().isAfter(LocalDateTime.now()));
            if (locked) return ApiResponse.fail(409, "座位 " + seat + " 已被锁定");

            SeatLock lock = new SeatLock();
            lock.setShowId(req.showId);
            lock.setSeatNo(seat);
            lock.setUserId(userId);
            lock.setLockToken(lockToken);
            lock.setExpireAt(expireAt);
            seatLockRepo.save(lock);
        }
        return ApiResponse.ok(Map.of("lockToken", lockToken, "expireAt", expireAt.toString()));
    }

    @PostMapping("/create")
    @Transactional
    public ApiResponse<?> create(Authentication auth, @Valid @RequestBody OrderCreateRequest req) {
        Long userId = (Long) auth.getPrincipal();
        Show show = showRepo.findById(req.showId).orElse(null);
        if (show == null) return ApiResponse.fail(404, "场次不存在");

        // 校验 lockToken
        for (String seat : req.seats) {
            seatLockRepo.findByShowIdAndSeatNoAndLockToken(req.showId, seat, req.lockToken)
                    .orElseThrow(() -> new RuntimeException("座位 " + seat + " 锁定已过期或无效"));
        }

        Order order = new Order();
        order.setUserId(userId);
        order.setShowId(req.showId);
        order.setSeats(String.join(",", req.seats));
        order.setTotalPrice(show.getPrice() * req.seats.size());
        order.setPayPrice(order.getTotalPrice());
        order.setLockToken(req.lockToken);
        order.setPayToken(UUID.randomUUID().toString());
        order.setStatus("PENDING");
        orderRepo.save(order);

        return ApiResponse.ok(Map.of("id", order.getId(), "payToken", order.getPayToken(),
                "totalPrice", order.getTotalPrice(), "status", order.getStatus()));
    }

    @PostMapping("/{id}/pay")
    @Transactional
    public ApiResponse<?> pay(@PathVariable Long id, Authentication auth, @Valid @RequestBody PayRequest req) {
        Long userId = (Long) auth.getPrincipal();
        Order order = orderRepo.findById(id).orElse(null);
        if (order == null || !order.getUserId().equals(userId))
            return ApiResponse.fail(404, "订单不存在");
        if (!"PENDING".equals(order.getStatus()))
            return ApiResponse.fail(400, "订单状态不允许支付");
        if (order.getPayToken() == null || !req.payToken.equals(order.getPayToken()))
            return ApiResponse.fail(400, "支付凭证无效");

        // 积分抵扣
        double finalPrice = order.getTotalPrice();
        if (req.pointsUsed != null && req.pointsUsed > 0) {
            User user = userRepo.findById(userId).orElse(null);
            if (user != null && user.getPoints() >= req.pointsUsed) {
                int discount = Math.min(req.pointsUsed, (int) (finalPrice * 100));
                finalPrice -= discount / 100.0;
                user.setPoints(user.getPoints() - discount);
                order.setPointsUsed(discount);
                userRepo.save(user);
            }
        }
        order.setPayPrice(Math.max(finalPrice, 0));
        order.setStatus("PAID");
        order.setPaidAt(LocalDateTime.now());
        order.setTicketCode(String.format("%06d", order.getId() % 1000000));
        orderRepo.save(order);

        // 清除座位锁
        for (String seat : order.getSeats().split(",")) {
            seatLockRepo.findByShowIdAndSeatNoAndLockToken(order.getShowId(), seat.trim(),
                    order.getLockToken()).ifPresent(seatLockRepo::delete);
        }
        return ApiResponse.ok(Map.of("ok", true, "status", "PAID", "ticketCode", order.getTicketCode()));
    }

    @PostMapping("/{id}/cancel")
    @Transactional
    public ApiResponse<?> cancel(@PathVariable Long id, Authentication auth) {
        Long userId = (Long) auth.getPrincipal();
        Order order = orderRepo.findById(id).orElse(null);
        if (order == null || !order.getUserId().equals(userId))
            return ApiResponse.fail(404, "订单不存在");
        if ("PAID".equals(order.getStatus())) {
            // 退款
            order.setStatus("REFUNDED");
            // 退回积分
            if (order.getPointsUsed() > 0) {
                User user = userRepo.findById(userId).orElse(null);
                if (user != null) {
                    user.setPoints(user.getPoints() + order.getPointsUsed());
                    userRepo.save(user);
                }
            }
        } else if ("PENDING".equals(order.getStatus())) {
            order.setStatus("CANCELLED");
            // 释放座位锁
            for (String seat : order.getSeats().split(",")) {
                seatLockRepo.findByShowIdAndSeatNoAndLockToken(order.getShowId(), seat.trim(),
                        order.getLockToken()).ifPresent(seatLockRepo::delete);
            }
        }
        orderRepo.save(order);
        return ApiResponse.ok("操作成功", Map.of("status", order.getStatus()));
    }

    @GetMapping("/{id}")
    public ApiResponse<?> detail(@PathVariable Long id, Authentication auth) {
        Long userId = (Long) auth.getPrincipal();
        Order order = orderRepo.findById(id).orElse(null);
        if (order == null || !order.getUserId().equals(userId))
            return ApiResponse.fail(404, "订单不存在");
        Show show = showRepo.findById(order.getShowId()).orElse(null);
        Movie movie = show != null ? movieRepo.findById(show.getMovieId()).orElse(null) : null;
        return ApiResponse.ok(Map.of(
                "id", order.getId(), "movieTitle", movie != null ? movie.getTitle() : "",
                "showTime", show != null ? show.getStartTime().toString() : "",
                "seats", List.of(order.getSeats().split(",")),
                "totalPrice", order.getTotalPrice(), "payPrice", order.getPayPrice(),
                "status", order.getStatus(), "ticketCode", order.getTicketCode()
        ));
    }

    @GetMapping("/list")
    public ApiResponse<?> list(Authentication auth, @RequestParam(required = false) String status) {
        Long userId = (Long) auth.getPrincipal();
        List<Order> orders = status != null
                ? orderRepo.findByUserIdAndStatus(userId, status)
                : orderRepo.findByUserIdOrderByCreatedAtDesc(userId);
        List<Map<String, Object>> result = new ArrayList<>();
        for (Order o : orders) {
            Show show = showRepo.findById(o.getShowId()).orElse(null);
            Movie movie = show != null ? movieRepo.findById(show.getMovieId()).orElse(null) : null;
            result.add(Map.of(
                    "id", o.getId(), "movieTitle", movie != null ? movie.getTitle() : "",
                    "startTime", show != null ? show.getStartTime().toString() : "",
                    "seats", List.of(o.getSeats().split(",")),
                    "totalPrice", o.getTotalPrice(), "status", o.getStatus()
            ));
        }
        return ApiResponse.ok(Map.of("list", result, "total", result.size()));
    }
}
