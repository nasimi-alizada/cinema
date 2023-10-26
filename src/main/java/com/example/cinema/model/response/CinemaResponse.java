package com.example.cinema.model.response;

import com.example.cinema.dao.entity.HallEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CinemaResponse {

    private Long id;

    private String name;

}
