package com.example.cinema.controller;

import com.example.cinema.criteria.MovieCriteria;
import com.example.cinema.criteria.MoviePageCriteria;
import com.example.cinema.model.request.MovieRequest;
import com.example.cinema.model.response.MoviePageableResponse;
import com.example.cinema.model.response.MovieResponse;
import com.example.cinema.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("v1/movies")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;

    @GetMapping("/search")
    public MoviePageableResponse getAllMoviesWithSearch(MovieCriteria movieCriteria,
                                              MoviePageCriteria moviePageCriteria) {
        return movieService.getAllMoviesWithSearch(movieCriteria, moviePageCriteria);
    }
    @GetMapping
    public List<MovieResponse> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/{id}")
    public MovieResponse getMovieById(@PathVariable Long id) {
        return movieService.getMovieById(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public void saveMovie(@RequestBody MovieRequest movieRequest) {
        movieService.saveMovie(movieRequest);
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void updateMovie(@PathVariable Long id, @RequestBody MovieRequest movieRequest) {
        movieService.updateMovie(id, movieRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
    }


}
