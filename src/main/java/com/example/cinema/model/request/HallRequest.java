package com.example.cinema.model.request;

import com.example.cinema.model.enums.GenreType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HallRequest {

    private String name;

    private Integer maxSeats;

}
