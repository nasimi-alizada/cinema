package com.example.cinema.model.response;
import com.example.cinema.dao.entity.SessionEntity;
import com.example.cinema.model.enums.GenreType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieResponse {
    private Long id;

    private String name;

    private Integer duration;

    private String director;

    private String description;

    private GenreType genre;

    private List<SessionResponse> sessions;
}
