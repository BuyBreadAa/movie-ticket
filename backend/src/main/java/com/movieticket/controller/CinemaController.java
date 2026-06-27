package com.movieticket.controller;

import com.movieticket.dto.ApiResponse;
import com.movieticket.entity.Cinema;
import com.movieticket.entity.Hall;
import com.movieticket.entity.Show;
import com.movieticket.repo.CinemaRepo;
import com.movieticket.repo.HallRepo;
import com.movieticket.repo.ShowRepo;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cinema")
public class CinemaController {

    private final CinemaRepo cinemaRepo;
    private final HallRepo hallRepo;
    private final ShowRepo showRepo;

    public CinemaController(CinemaRepo cinemaRepo, HallRepo hallRepo, ShowRepo showRepo) {
        this.cinemaRepo = cinemaRepo;
        this.hallRepo = hallRepo;
        this.showRepo = showRepo;
    }

    @GetMapping("/list")
    public ApiResponse<?> list(@RequestParam(required = false) String region) {
        List<Cinema> list = region != null ? cinemaRepo.findByCity(region) : cinemaRepo.findAll();
        return ApiResponse.ok(Map.of("list", list));
    }

    @GetMapping("/{id}")
    public ApiResponse<?> detail(@PathVariable Long id) {
        Cinema cinema = cinemaRepo.findById(id).orElse(null);
        if (cinema == null) return ApiResponse.fail(404, "影院不存在");
        List<Hall> halls = hallRepo.findByCinemaId(id);
        return ApiResponse.ok(Map.of("cinema", cinema, "halls", halls));
    }

    @GetMapping("/{id}/schedule")
    public ApiResponse<?> schedule(@PathVariable Long id,
                                    @RequestParam(required = false) Long movieId,
                                    @RequestParam(required = false) String date) {
        LocalDateTime start, end;
        if (date != null) {
            start = LocalDate.parse(date).atStartOfDay();
            end = start.plusDays(1);
        } else {
            start = LocalDate.now().atStartOfDay();
            end = start.plusDays(7);
        }
        List<Show> shows = showRepo.findByCinemaIdAndStartTimeBetween(id, start, end);
        if (movieId != null) shows = shows.stream().filter(s -> s.getMovieId().equals(movieId)).toList();
        return ApiResponse.ok(Map.of("list", shows));
    }

    @GetMapping("/hall/{hallId}/seats")
    public ApiResponse<?> seats(@PathVariable Long hallId, @RequestParam Long showId) {
        Hall hall = hallRepo.findById(hallId).orElse(null);
        if (hall == null) return ApiResponse.fail(404, "影厅不存在");
        return ApiResponse.ok(Map.of("seatLayout", hall.getSeatLayout()));
    }
}
