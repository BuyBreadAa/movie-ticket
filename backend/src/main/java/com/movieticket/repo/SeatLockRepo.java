package com.movieticket.repo;

import com.movieticket.entity.SeatLock;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface SeatLockRepo extends JpaRepository<SeatLock, Long> {
    List<SeatLock> findByShowIdAndSeatNo(Long showId, String seatNo);
    Optional<SeatLock> findByShowIdAndSeatNoAndLockToken(Long showId, String seatNo, String lockToken);
    void deleteByExpireAtBefore(LocalDateTime now);
}
