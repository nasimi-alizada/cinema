package com.example.cinema.mapper;

import com.example.cinema.dao.entity.CinemaEntity;
import com.example.cinema.dao.entity.MovieEntity;
import com.example.cinema.model.request.CinemaRequest;
import com.example.cinema.model.request.MovieRequest;
import com.example.cinema.model.response.CinemaResponse;

import java.util.stream.Collectors;

public class CinemaMapper {


    public static CinemaResponse buildCinemaResponse(CinemaEntity cinemaEntity) {
        return CinemaResponse.builder()
                .id(cinemaEntity.getId())
                .name(cinemaEntity.getName())
                .build();

    }

    public static CinemaEntity buildCinemaEntity(CinemaRequest cinemaRequest) {
        return CinemaEntity.builder()
                .name(cinemaRequest.getName())
                .build();
    }


}
