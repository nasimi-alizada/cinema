package com.example.cinema.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MoviePageableResponse {

    private List<MovieResponse> movies;

    private int lastPageNumber;

    private long totalElements;

    private boolean hasNextPage;
}
