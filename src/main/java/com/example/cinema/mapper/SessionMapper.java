package com.example.cinema.mapper;

import com.example.cinema.dao.entity.SessionEntity;
import com.example.cinema.model.request.SessionRequest;
import com.example.cinema.model.response.SessionResponse;

public class SessionMapper {
    public static SessionResponse buildSessionResponse(SessionEntity sessionEntity) {
        return SessionResponse.builder()
                .id(sessionEntity.getId())
                .startTime(sessionEntity.getStartTime())
                .endTime(sessionEntity.getEndTime())
                .sessionType(sessionEntity.getSessionType())
                .price(sessionEntity.getPrice())
                .build();

    }

    public static SessionEntity buildSessionEntity(SessionRequest sessionRequest) {
        return SessionEntity.builder()
                .startTime(sessionRequest.getStartTime())
                .endTime(sessionRequest.getEndTime())
                .sessionType(sessionRequest.getSessionType())
                .price(sessionRequest.getPrice())
                .build();
    }

    public static void buildUpdateSessionEntity(SessionRequest sessionRequest,
                                                SessionEntity sessionEntity) {
        sessionEntity.setStartTime(sessionRequest.getStartTime());
        sessionEntity.setEndTime(sessionRequest.getEndTime());
        sessionEntity.setSessionType(sessionRequest.getSessionType());
        sessionEntity.setPrice(sessionRequest.getPrice());

    }
}
