package com.example.cinema.mapper;

import com.example.cinema.dao.entity.SeatEntity;
import com.example.cinema.model.request.SeatRequest;

import static com.example.cinema.model.enums.SeatStatus.AVAILABLE;

public class SeatMapper {

    public static SeatEntity buildSeatEntity(SeatRequest seatRequest) {
        return SeatEntity.builder()
                .rowOfSeat(seatRequest.getRowOfSeat())
                .columnOfSeat(seatRequest.getColumnOfSeat())
                .seatStatus(AVAILABLE)
                .build();
    }
}
