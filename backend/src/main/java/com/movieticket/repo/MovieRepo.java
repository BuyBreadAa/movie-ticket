package com.movieticket.repo;

import com.movieticket.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MovieRepo extends JpaRepository<Movie, Long> {
    List<Movie> findByStatus(String status);
    List<Movie> findByType(String type);
    List<Movie> findByStatusOrderByRatingDesc(String status);
}
