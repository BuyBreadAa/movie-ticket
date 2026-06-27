package com.movieticket.controller;

import com.movieticket.dto.ApiResponse;
import com.movieticket.entity.Movie;
import com.movieticket.repo.MovieRepo;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/recommend")
public class RecommendController {

    private final MovieRepo movieRepo;

    public RecommendController(MovieRepo movieRepo) {
        this.movieRepo = movieRepo;
    }

    @GetMapping("/home")
    public ApiResponse<?> home() {
        List<Movie> showing = movieRepo.findByStatusOrderByRatingDesc("showing");
        List<Movie> hot = showing.size() > 4 ? showing.subList(0, 4) : showing;
        List<Movie> recommend;
        if (showing.size() > 8) {
            recommend = showing.subList(4, 8);
        } else if (showing.size() > 4) {
            recommend = showing.subList(4, showing.size());
        } else {
            recommend = showing;
        }
        return ApiResponse.ok(Map.of("hot", hot, "recommend", recommend));
    }

    @GetMapping("/similar/{movieId}")
    public ApiResponse<?> similar(@PathVariable Long movieId) {
        Movie movie = movieRepo.findById(movieId).orElse(null);
        if (movie == null) return ApiResponse.fail(404, "影片不存在");
        // ponytail: 同类型推荐，协同过滤后续可加
        List<Movie> sameType = movieRepo.findByType(movie.getType());
        List<Movie> others = sameType.stream().filter(m -> !m.getId().equals(movieId)).limit(4).toList();
        return ApiResponse.ok(others);
    }

    @GetMapping("/hot")
    public ApiResponse<?> hot() {
        List<Movie> hot = movieRepo.findByStatusOrderByRatingDesc("showing");
        return ApiResponse.ok(hot.size() > 6 ? hot.subList(0, 6) : hot);
    }
}
