package com.example.movies.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
@Data
public class MoviesDTO {
    private String tconst;
    private String primaryTitle;
    private String genres;
    private double averageRating;

}
