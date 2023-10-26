package com.example.cinema.mapper

import com.example.cinema.model.request.HallRequest
import com.example.cinema.model.request.SeatRequest
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Specification

import static com.example.cinema.model.enums.SeatStatus.AVAILABLE

class HallMapperTest extends Specification {
    private EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()


    def "TestBuildHallEntity"() {
        given:
        def hallRequest = random.nextObject(HallRequest)

        when:
        def actual = HallMapper.buildHallEntity(hallRequest)

        then:
        actual.name == hallRequest.name
        actual.maxSeats == hallRequest.maxSeats

    }

}
