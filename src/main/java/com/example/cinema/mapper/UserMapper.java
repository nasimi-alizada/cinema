package com.example.cinema.mapper;

import com.example.cinema.dao.entity.UserEntity;
import com.example.cinema.model.request.UserRequest;
import com.example.cinema.model.response.UserResponse;

import java.util.stream.Collectors;

public class UserMapper {

    public static UserResponse buildUserResponse(UserEntity userEntity) {

        return UserResponse.builder()
                .name(userEntity.getName())
                .surname(userEntity.getSurname())
                .balance(userEntity.getBalance())
                .tickets(userEntity.getTickets().stream()
                        .map(TicketMapper::buildTicketResponse)
                        .collect(Collectors.toList()))
                .build();


    }


    public static UserEntity buildUserEntity(UserRequest userRequest) {
        return UserEntity.builder()
                .name(userRequest.getName())
                .surname(userRequest.getSurname())
                .balance(userRequest.getBalance())
                .build();
    }


    public static void buildUpdateUserEntity(UserRequest userRequest,
                                             UserEntity userEntity) {
        userEntity.setName(userRequest.getName());
        userEntity.setSurname(userRequest.getSurname());
        userEntity.setBalance(userRequest.getBalance());

    }
}
