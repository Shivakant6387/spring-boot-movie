package com.example.movies.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MoviesWithRating {
    private String genres;
    private String primaryTitle;
    private BigDecimal numVotes;
    private BigDecimal sum;
}
