package com.example.cinema.mapper


import com.example.cinema.dao.entity.UserEntity
import com.example.cinema.model.request.UserRequest
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Specification

import static com.example.cinema.mapper.UserMapper.*

class UserMapperTest extends Specification {
    private EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()


    def "TestBuildUserEntity"() {
        given:
        def userRequest = random.nextObject(UserRequest)

        when:
        def actual = buildUserEntity(userRequest)

        then:
        actual.name == userRequest.name
        actual.surname == userRequest.surname
        actual.email == userRequest.email
        actual.password == userRequest.password
        actual.balance == userRequest.balance


    }

    def "TestBuildUserResponse"() {
        given:
        def userEntity = random.nextObject(UserEntity)

        when:
        def actual = buildUserResponse(userEntity)

        then:
        actual.name == userEntity.name
        actual.surname == userEntity.surname
        actual.tickets == userEntity.tickets
        actual.balance == userEntity.balance
        actual.balance == userEntity.balance
    }

    def "TestBuildUpdateUserEntity"() {
        given:
        def userEntity = random.nextObject(UserEntity)
        def userRequest = random.nextObject(UserRequest)

        when:
        buildUpdateUserEntity(userRequest, userEntity)

        then:
        userRequest.name == userEntity.name
        userRequest.surname == userEntity.surname
        userRequest.email == userEntity.email
        userRequest.password == userEntity.password
        userRequest.balance == userEntity.balance

    }
}
