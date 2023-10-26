package com.example.cinema.service;

import com.example.cinema.criteria.MovieCriteria;
import com.example.cinema.criteria.MoviePageCriteria;
import com.example.cinema.dao.entity.MovieEntity;
import com.example.cinema.dao.repository.MovieRepository;
import com.example.cinema.exception.EntityNotFoundException;
import com.example.cinema.mapper.MovieMapper;
import com.example.cinema.model.request.MovieRequest;
import com.example.cinema.model.response.MoviePageableResponse;
import com.example.cinema.model.response.MovieResponse;
import com.example.cinema.service.specification.MovieSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.cinema.mapper.MovieMapper.*;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;

    public MoviePageableResponse getAllMoviesWithSearch(MovieCriteria movieCriteria,
                                                        MoviePageCriteria moviePageCriteria) {
        var movieEntityPage = movieRepository.findAll(
                new MovieSpecification(movieCriteria),
                PageRequest.of(moviePageCriteria.getPage(), moviePageCriteria.getCount()));
        return mapToMoviePageableResponse(movieEntityPage);
    }

    public List<MovieResponse> getAllMovies() {
        return movieRepository.findAll().stream()
                .map(MovieMapper::buildMovieResponse).collect(Collectors.toList());
    }

    public MovieResponse getMovieById(Long id) {
        var movieEntity = fetchMovieIfExist(id);
        return buildMovieResponse(movieEntity);
    }


    public void saveMovie(MovieRequest movieRequest) {
        var movieEntity = buildMovieEntity(movieRequest);
        movieRepository.save(movieEntity);

    }

    public void updateMovie(Long id, MovieRequest movieRequest) {
        var movieEntity = fetchMovieIfExist(id);
        buildUpdateMovieEntity(movieRequest, movieEntity);
        movieRepository.save(movieEntity);

    }

    public void deleteMovie(Long id) {
        fetchMovieIfExist(id);
        movieRepository.deleteById(id);

    }

    private MovieEntity fetchMovieIfExist(Long id) {
        return movieRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("MOVIE_NOT_FOUND")
        );

    }


    private MoviePageableResponse mapToMoviePageableResponse(Page<MovieEntity> movieEntityPage) {
        return MoviePageableResponse.builder()
                .movies(movieEntityPage.getContent().stream().map(MovieMapper::buildMovieResponse).collect(Collectors.toList()))
                .hasNextPage(movieEntityPage.hasNext())
                .totalElements(movieEntityPage.getTotalElements())
                .lastPageNumber(movieEntityPage.getTotalPages())
                .build();
    }
}
