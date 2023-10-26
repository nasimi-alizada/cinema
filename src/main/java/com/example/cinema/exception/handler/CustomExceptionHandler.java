package com.example.cinema.exception.handler;

import com.example.cinema.exception.EntityNotFoundException;
import com.example.cinema.exception.NotEnoughBalanceException;
import com.example.cinema.exception.OccupiedSeatException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(EntityNotFoundException ex) {
        log.error("Entity Not Found ", ex);
        ErrorResponse error = new ErrorResponse(NOT_FOUND, ex.getMessage(), LocalDateTime.now());

        return new ResponseEntity<>(error, NOT_FOUND);
    }

    @ExceptionHandler(OccupiedSeatException.class)
    public ResponseEntity<ErrorResponse> handleOccupiedSeatException(OccupiedSeatException ex) {
        log.error("Seat Is Occupied ", ex);
        ErrorResponse error = new ErrorResponse(NOT_ACCEPTABLE, ex.getMessage(), LocalDateTime.now());

        return new ResponseEntity<>(error, NOT_ACCEPTABLE);
    }

    @ExceptionHandler(NotEnoughBalanceException.class)
    public ResponseEntity<ErrorResponse> handleNotEnoughBalanceException(NotEnoughBalanceException ex) {
        log.error("Not Enough Balance ", ex);
        ErrorResponse error = new ErrorResponse(NOT_ACCEPTABLE, ex.getMessage(), LocalDateTime.now());

        return new ResponseEntity<>(error, NOT_ACCEPTABLE);
    }
}
