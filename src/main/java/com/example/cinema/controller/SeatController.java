package com.example.cinema.controller;

import com.example.cinema.model.request.SeatRequest;
import com.example.cinema.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("v1/seats")
@RequiredArgsConstructor
public class SeatController {
    private final SeatService seatService;

    @PostMapping
    @ResponseStatus(CREATED)
    public void saveSeat(@RequestBody SeatRequest seatRequest, @RequestParam Long hallId) {
        seatService.saveSeat(seatRequest, hallId);
    }

}
