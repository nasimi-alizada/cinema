package com.example.cinema.mapper;

import com.example.cinema.dao.entity.HallEntity;
import com.example.cinema.dao.entity.MovieEntity;
import com.example.cinema.model.request.HallRequest;
import com.example.cinema.model.request.MovieRequest;

public class HallMapper {

    public static HallEntity buildHallEntity(HallRequest hallRequest) {
        return HallEntity.builder()
                .name(hallRequest.getName())
                .maxSeats(hallRequest.getMaxSeats())
                .build();
    }
}
