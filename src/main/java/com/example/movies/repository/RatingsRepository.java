package com.example.movies.repository;

import com.example.movies.entity.Movies;
import com.example.movies.entity.Ratings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingsRepository extends JpaRepository<Ratings,String> {
    List<Ratings> findByAverageRatingGreaterThan(double averageRating);
}
