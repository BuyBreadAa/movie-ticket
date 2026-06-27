package com.movieticket.controller;

import com.movieticket.dto.ApiResponse;
import com.movieticket.entity.Movie;
import com.movieticket.entity.Review;
import com.movieticket.repo.MovieRepo;
import com.movieticket.repo.ReviewRepo;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/movie")
public class MovieController {

    private final MovieRepo movieRepo;
    private final ReviewRepo reviewRepo;

    public MovieController(MovieRepo movieRepo, ReviewRepo reviewRepo) {
        this.movieRepo = movieRepo;
        this.reviewRepo = reviewRepo;
    }

    @GetMapping("/list")
    public ApiResponse<?> list(@RequestParam(required = false) String type,
                                @RequestParam(required = false) String region,
                                @RequestParam(required = false) Integer year) {
        List<Movie> all = movieRepo.findAll();
        if (type != null) all = all.stream().filter(m -> type.equals(m.getType())).toList();
        if (region != null) all = all.stream().filter(m -> region.equals(m.getRegion())).toList();
        if (year != null) all = all.stream().filter(m -> m.getYear() != null && m.getYear().equals(year)).toList();
        return ApiResponse.ok(Map.of("list", all, "total", all.size()));
    }

    @GetMapping("/{id}")
    public ApiResponse<?> detail(@PathVariable Long id) {
        Movie movie = movieRepo.findById(id).orElse(null);
        if (movie == null) return ApiResponse.fail(404, "影片不存在");
        return ApiResponse.ok(movie);
    }

    @GetMapping("/{id}/reviews")
    public ApiResponse<?> reviews(@PathVariable Long id) {
        List<Review> list = reviewRepo.findByMovieIdOrderByCreatedAtDesc(id);
        return ApiResponse.ok(Map.of("list", list, "total", list.size()));
    }

    @PostMapping("/{id}/review")
    public ApiResponse<?> addReview(@PathVariable Long id, Authentication auth, @RequestBody Map<String, Object> body) {
        Review review = new Review();
        review.setMovieId(id);
        review.setUserId((Long) auth.getPrincipal());
        review.setRating((Integer) body.get("rating"));
        review.setContent((String) body.get("content"));
        reviewRepo.save(review);

        // 更新影片均分
        List<Review> all = reviewRepo.findByMovieIdOrderByCreatedAtDesc(id);
        double avg = all.stream().mapToInt(Review::getRating).average().orElse(0);
        Movie movie = movieRepo.findById(id).orElse(null);
        if (movie != null) {
            movie.setRating(Math.round(avg * 10) / 10.0);
            movieRepo.save(movie);
        }
        return ApiResponse.ok("发布成功", review);
    }
}
