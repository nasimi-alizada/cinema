package com.example.cinema.controller;

import com.example.cinema.model.request.SeatRequest;
import com.example.cinema.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("v1/tickets")
@RequiredArgsConstructor
public class TicketController {
    private final TicketService ticketService;

    @PostMapping
    @ResponseStatus(CREATED)
    public void getTicket(@RequestBody List<SeatRequest> seatRequests,
                          @RequestParam Long sessionId,
                          @RequestParam Long userId) {


        ticketService.getTicket(seatRequests, userId, sessionId);

    }
}
