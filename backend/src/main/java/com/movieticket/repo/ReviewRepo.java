package com.movieticket.repo;

import com.movieticket.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReviewRepo extends JpaRepository<Review, Long> {
    List<Review> findByMovieIdOrderByCreatedAtDesc(Long movieId);
    List<Review> findByUserIdOrderByCreatedAtDesc(Long userId);
}
