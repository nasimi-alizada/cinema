package com.example.cinema.service;

import com.example.cinema.dao.repository.HallRepository;
import com.example.cinema.dao.repository.SeatRepository;
import com.example.cinema.exception.EntityNotFoundException;
import com.example.cinema.mapper.SeatMapper;
import com.example.cinema.model.request.SeatRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SeatService {
    private final SeatRepository seatRepository;
    private final HallRepository hallRepository;

    public void saveSeat(SeatRequest seatRequest, Long hallId) {
        var hallEntity = hallRepository.findById(hallId).orElseThrow(
                () -> new EntityNotFoundException("HALL_NOT_FOUND")
        );

        var seatEntity = SeatMapper.buildSeatEntity(seatRequest);

        seatEntity.setHall(hallEntity);
        seatRepository.save(seatEntity);


    }
}
