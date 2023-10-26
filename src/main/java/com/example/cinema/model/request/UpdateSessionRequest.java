package com.example.cinema.model.request;

import com.example.cinema.model.enums.SessionType;
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
public class UpdateSessionRequest {

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private SessionType sessionType;

    private BigDecimal price;
}
