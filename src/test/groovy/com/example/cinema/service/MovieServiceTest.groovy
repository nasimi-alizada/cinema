package com.example.cinema.service


import com.example.cinema.dao.entity.MovieEntity
import com.example.cinema.dao.repository.MovieRepository
import com.example.cinema.exception.EntityNotFoundException
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Specification

class MovieServiceTest extends Specification {
    private EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()
    private MovieRepository movieRepository
    private MovieService movieService


    def setup() {
        movieRepository = Mock()
        movieService = new MovieService(movieRepository)
    }

    def "TestGetMovieById success"() {
        given:
        def id = random.nextObject(Long)
        def entity = random.nextObject(MovieEntity)

        when:
        def actual = movieService.getMovieById(id)


        then:
        1 * movieRepository.findById(id) >> Optional.of(entity)
        actual.id == entity.id
        actual.name == entity.name
        actual.description == entity.description
        actual.duration == entity.duration
        actual.director == entity.director
        actual.genre == entity.genre
        actual.sessions == entity.sessions
    }

    def "TestGetMovieById entity not found"() {
        given:
        def id = random.nextObject(Long)

        when:
        movieService.getMovieById(id)


        then:
        1 * movieRepository.findById(id) >> Optional.empty()

        EntityNotFoundException ex = thrown()
        ex.message == "MOVIE_NOT_FOUND"
    }


}
