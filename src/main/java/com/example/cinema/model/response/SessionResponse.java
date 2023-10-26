package com.example.cinema.model.response;

import com.example.cinema.model.enums.SessionType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SessionResponse {

    private Long id;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private SessionType sessionType;

    private BigDecimal price;
}
