package com.movieticket.repo;

import com.movieticket.entity.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface ShowRepo extends JpaRepository<Show, Long> {
    List<Show> findByMovieIdAndStatus(Long movieId, String status);
    List<Show> findByCinemaIdAndStartTimeBetween(Long cinemaId, LocalDateTime start, LocalDateTime end);
    List<Show> findByMovieIdAndCinemaIdAndStatus(Long movieId, Long cinemaId, String status);
}
