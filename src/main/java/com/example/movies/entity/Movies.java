package com.example.movies.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;
import javax.persistence.*;

@Data
@Table
@Entity
@AllArgsConstructor
@NoArgsConstructor(force = true)

public class Movies {
    @Id

    private String tconst;
    private String titleType;
    private String primaryTitle;
    private Integer runtimeMinutes;
    private String genres;
}
