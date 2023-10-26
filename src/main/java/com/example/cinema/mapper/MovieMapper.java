package com.example.cinema.mapper;

import com.example.cinema.dao.entity.MovieEntity;
import com.example.cinema.model.request.MovieRequest;
import com.example.cinema.model.response.MovieResponse;

import java.util.stream.Collectors;

public class MovieMapper {
    public static MovieResponse buildMovieResponse(MovieEntity movieEntity) {
        return MovieResponse.builder()
                .id(movieEntity.getId())
                .name(movieEntity.getName())
                .duration(movieEntity.getDuration())
                .director(movieEntity.getDirector())
                .description(movieEntity.getDescription())
                .genre(movieEntity.getGenre())
                .sessions(movieEntity.getSessions().stream()
                        .map(SessionMapper::buildSessionResponse).toList())
                .build();
    }

    public static MovieEntity buildMovieEntity(MovieRequest movieRequest) {
        return MovieEntity.builder()
                .name(movieRequest.getName())
                .duration(movieRequest.getDuration())
                .director(movieRequest.getDirector())
                .description(movieRequest.getDescription())
                .genre(movieRequest.getGenre())
                .build();
    }

    public static void buildUpdateMovieEntity(MovieRequest movieRequest,
                                              MovieEntity movieEntity) {
        movieEntity.setName(movieRequest.getName());
        movieEntity.setDuration(movieRequest.getDuration());
        movieEntity.setDirector(movieRequest.getDirector());
        movieEntity.setDescription(movieRequest.getDescription());
        movieEntity.setGenre(movieRequest.getGenre());
    }
}
