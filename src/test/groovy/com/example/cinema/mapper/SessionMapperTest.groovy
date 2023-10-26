package com.example.cinema.mapper


import com.example.cinema.dao.entity.SessionEntity
import com.example.cinema.model.request.SessionRequest
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Specification

import static com.example.cinema.mapper.SessionMapper.*

class SessionMapperTest extends Specification {
    private EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()


    def "TestBuildSessionEntity"() {
        given:
        def sessionRequest = random.nextObject(SessionRequest)

        when:
        def actual = buildSessionEntity(sessionRequest)


        then:
        actual.startTime == sessionRequest.startTime
        actual.endTime == sessionRequest.endTime
        actual.sessionType == sessionRequest.sessionType
        actual.price == sessionRequest.price
    }

    def "TestBuildSessionResponse"() {
        given:
        def sessionEntity = random.nextObject(SessionEntity)

        when:
        def actual = buildSessionResponse(sessionEntity)

        then:
        actual.startTime == sessionEntity.startTime
        actual.endTime == sessionEntity.endTime
        actual.sessionType == sessionEntity.sessionType
        actual.price == sessionEntity.price
    }

    def "TestBuildUpdateSessionEntity"() {
        given:
        def sessionEntity = random.nextObject(SessionEntity)
        def sessionRequest = random.nextObject(SessionRequest)

        when:
        buildUpdateSessionEntity(sessionRequest, sessionEntity)

        then:
        sessionRequest.startTime == sessionEntity.startTime
        sessionRequest.endTime == sessionEntity.endTime
        sessionRequest.sessionType == sessionEntity.sessionType
        sessionRequest.price == sessionEntity.price
    }
}
