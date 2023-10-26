package com.example.cinema.controller;

import com.example.cinema.model.request.HallRequest;
import com.example.cinema.model.request.MovieRequest;
import com.example.cinema.service.HallService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;


@RestController
@RequestMapping("v1/halls")
@RequiredArgsConstructor
public class HallController {
    private final HallService hallService;

    @PostMapping
    @ResponseStatus(CREATED)
    public void saveHall(@RequestBody HallRequest hallRequest) {
        hallService.saveHall(hallRequest);
    }
}
