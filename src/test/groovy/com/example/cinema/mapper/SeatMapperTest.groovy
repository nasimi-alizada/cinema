package com.example.cinema.mapper


import com.example.cinema.model.request.SeatRequest
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Specification

import static com.example.cinema.model.enums.SeatStatus.AVAILABLE

class SeatMapperTest extends Specification {
    private EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()


    def "TestBuildSeatEntity"() {
        given:
        def seatRequest = random.nextObject(SeatRequest)

        when:
        def actual = SeatMapper.buildSeatEntity(seatRequest)

        then:
        actual.rowOfSeat==seatRequest.rowOfSeat
        actual.columnOfSeat==seatRequest.columnOfSeat
        actual.seatStatus==AVAILABLE

    }

}
