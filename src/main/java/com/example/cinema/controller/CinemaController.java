package com.example.cinema.controller;

import com.example.cinema.model.request.CinemaRequest;
import com.example.cinema.model.response.CinemaResponse;
import com.example.cinema.service.CinemaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.net.CacheRequest;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("v1/cinemas")
@RequiredArgsConstructor
public class CinemaController {
    private final CinemaService cinemaService;


    @GetMapping
    public List<CinemaResponse> getAllCinemas() {
        return cinemaService.getAllCinemas();
    }

    @GetMapping("/{id}")
    public CinemaResponse getCinemaById(@PathVariable Long id) {
        return cinemaService.getCinemaById(id);

    }

    @PostMapping
    @ResponseStatus(CREATED)
    public void saveCinema(@RequestBody CinemaRequest cinemaRequest) {
        cinemaService.saveCinema(cinemaRequest);
    }
}
