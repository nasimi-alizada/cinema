package com.example.cinema.model.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatRequest {
    private Integer rowOfSeat;
    private Integer columnOfSeat;
}
