package com.example.cinema.controller;

import com.example.cinema.model.request.SessionRequest;
import com.example.cinema.model.response.SessionResponse;
import com.example.cinema.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("v1/sessions")
@RequiredArgsConstructor
public class SessionController {
    private final SessionService sessionService;

    @GetMapping
    public List<SessionResponse> getAllSessions() {
        return sessionService.getAllSessions();
    }

    @GetMapping("/{id}")
    public SessionResponse getSessionById(@PathVariable Long id) {
        return sessionService.getSessionById(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public void addSession(@RequestBody SessionRequest sessionRequest,
                           @RequestParam Long hallId, @RequestParam Long movieId) {
        sessionService.addSession(sessionRequest, hallId, movieId);
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void updateSession(@PathVariable Long id,
                              @RequestBody SessionRequest sessionRequest,
                              @RequestParam Long hallId, @RequestParam Long movieId) {
        sessionService.updateSession(id, sessionRequest, hallId, movieId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteSession(@PathVariable Long id) {
        sessionService.deleteSession(id);

    }
}
