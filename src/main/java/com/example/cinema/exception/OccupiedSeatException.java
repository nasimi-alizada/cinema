package com.example.cinema.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;

@ResponseStatus(NOT_ACCEPTABLE)
public class OccupiedSeatException extends RuntimeException {
    public OccupiedSeatException(String message) {
        super(message);
    }
}
