package com.example.cinema.mapper

import com.example.cinema.dao.entity.MovieEntity
import com.example.cinema.model.request.MovieRequest
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Specification

import static com.example.cinema.mapper.MovieMapper.*

class MovieMapperTest extends Specification {
    private EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()


    def "TestBuildMovieEntity"() {
        given:
        def movieRequest = random.nextObject(MovieRequest)

        when:
        def actual = buildMovieEntity(movieRequest)

        then:
        actual.name == movieRequest.name
        actual.duration == movieRequest.duration
        actual.description == movieRequest.description
        actual.director == movieRequest.director
        actual.genre == movieRequest.genre
    }

    def "TestBuildMovieResponse"() {
        given:
        def movieEntity = random.nextObject(MovieEntity)

        when:
        def actual = buildMovieResponse(movieEntity)

        then:
        actual.name == movieEntity.name
        actual.duration == movieEntity.duration
        actual.description == movieEntity.description
        actual.director == movieEntity.director
        actual.genre == movieEntity.genre
    }

    def "TestBuildUpdateMovieEntity"() {
        given:
        def movieEntity = random.nextObject(MovieEntity)
        def movieRequest = random.nextObject(MovieRequest)

        when:
        buildUpdateMovieEntity(movieRequest, movieEntity)

        then:
        movieEntity.name == movieRequest.name
        movieEntity.duration == movieRequest.duration
        movieEntity.description == movieRequest.description
        movieEntity.director == movieRequest.director
        movieEntity.genre == movieRequest.genre
    }

}
