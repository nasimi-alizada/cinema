package com.example.cinema.mapper

import com.example.cinema.dao.entity.CinemaEntity
import com.example.cinema.model.request.CinemaRequest
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Specification

import static com.example.cinema.mapper.CinemaMapper.buildCinemaEntity
import static com.example.cinema.mapper.CinemaMapper.buildCinemaResponse

class CinemaMapperTest extends Specification {
    private EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()

    def "TestBuildCinemaResponse"() {
        given:
        def cinemaEntity = random.nextObject(CinemaEntity)

        when:
        def actual = buildCinemaResponse(cinemaEntity)

        then:
        actual.name == cinemaEntity.name

    }
    def "TestBuildCinemaEntity"() {
        given:
        def cinemaRequest = random.nextObject(CinemaRequest)

        when:
        def actual = buildCinemaEntity(cinemaRequest)

        then:
        actual.name == cinemaRequest.name

    }

}
