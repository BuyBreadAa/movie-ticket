package com.movieticket.repo;

import com.movieticket.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface OrderRepo extends JpaRepository<Order, Long> {
    List<Order> findByUserIdOrderByCreatedAtDesc(Long userId);
    List<Order> findByUserIdAndStatus(Long userId, String status);
    Optional<Order> findByPayToken(String payToken);
    Optional<Order> findByLockToken(String lockToken);
    List<Order> findByStatus(String status);
}
