package com.example.cinema.criteria;

import com.example.cinema.model.enums.GenreType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieCriteria {

    private String name;

    private String director;

    private GenreType genre;

}
