package com.example.movies.repository;
import com.example.movies.entity.Movies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoviesRepository extends JpaRepository<Movies, String> {

    @Query(value = "SELECT m.genres, m.primary_title,sum(r.num_votes) as numVotes " +
            "FROM movies m " +
            "INNER JOIN ratings r " +
            "ON m.tconst = r.tconst " +
            "group by m.tconst " +
            "order  by m.genres asc;", nativeQuery = true)
    List<Object[]> getMoviesWithNumVotes();

    @Query(value = "UPDATE movies SET runtime_minutes = " +
            "CASE " +
            "    WHEN genres='Documentary' THEN runtime_minutes + 15 " +
            "    WHEN genres='Animation' THEN runtime_minutes + 30 " +
            "    ELSE runtime_minutes + 45 " +
            "END;", nativeQuery = true)
    List<Movies> updateMoviesRuntimeMinute();
}
