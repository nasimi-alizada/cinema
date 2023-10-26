package com.example.cinema.mapper;

import com.example.cinema.dao.entity.MovieEntity;
import com.example.cinema.dao.entity.TicketEntity;
import com.example.cinema.model.response.MovieResponse;
import com.example.cinema.model.response.TicketResponse;

public class TicketMapper {
    public static TicketResponse buildTicketResponse(TicketEntity ticketEntity) {
        return TicketResponse.builder()
                .id(ticketEntity.getId())
                .build();
    }
}
