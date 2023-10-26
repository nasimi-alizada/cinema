package com.example.cinema.mapper

import com.example.cinema.dao.entity.TicketEntity
import com.example.cinema.model.request.SeatRequest
import com.example.cinema.model.request.TicketRequest
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Specification

import static com.example.cinema.model.enums.SeatStatus.AVAILABLE

class TicketMapperTest extends Specification {
    private EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()


    def "TestBuildTicketResponse"() {
        given:
        def ticketEntity = random.nextObject(TicketEntity)

        when:
        def actual = TicketMapper.buildTicketResponse(ticketEntity)

        then:
        actual.id==ticketEntity.id

    }

}
