package com.movieticket.repo;

import com.movieticket.entity.Hall;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HallRepo extends JpaRepository<Hall, Long> {
    List<Hall> findByCinemaId(Long cinemaId);
}
