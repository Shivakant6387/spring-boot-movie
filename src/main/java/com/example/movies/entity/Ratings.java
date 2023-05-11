package com.example.movies.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;
import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class Ratings {
    @Id
    private String tconst;

    private double averageRating;

    private Integer numVotes;
}
