package com.example.movies.repository;
import com.example.movies.entity.Movies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface MoviesRepository extends JpaRepository<Movies, String> {

    @Modifying
    @Query(value = "UPDATE movies " +
            "SET runtime_minutes = runtime_minutes + " +
            "CASE " +
            "WHEN genres LIKE '%Documentary%' THEN 15 " +
            "WHEN genres LIKE '%Animation%' THEN 30 " +
            "ELSE 45 " +
            "END", nativeQuery = true)
    Movies updateRuntimeMinutes(Movies movies);
}
