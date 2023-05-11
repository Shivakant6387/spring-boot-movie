package com.example.movies.controller;
import java.util.*;

import com.example.movies.dto.MoviesDTO;
import com.example.movies.dto.RatingsDTO;
import com.example.movies.entity.Movies;
import com.example.movies.entity.Ratings;
import com.example.movies.exception.MoviesNotFoundException;
import com.example.movies.repository.MoviesRepository;
import com.example.movies.service.MoviesService;
import com.example.movies.service.RatingsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Slf4j
@RequestMapping("/api/v1")
@RestController
public class MoviesController {
    private static final Logger logger = LoggerFactory.getLogger(MoviesController.class);

    public void myMethod() {
        logger.debug("Debug message");
        logger.info("Info message");
        logger.warn("Warn message");
        logger.error("Error message");
    }
    @Autowired
    private MoviesService moviesService;
    @Autowired
    private RatingsService ratingsService;

    @GetMapping("/longest-duration-ratings")
    public ResponseEntity<List<Ratings>>getAllRatings(){

        return ResponseEntity.ok( ratingsService.getAllRatings());
    }
    @GetMapping("/longest-duration-movies")
    public ResponseEntity <List<Movies>>getLongestDurationMovies(){

        return  ResponseEntity.ok( moviesService.findTop10ByOrderByRuntimeMinutesDesc());
    }
    @PostMapping("/new-movie")
    public ResponseEntity<String> createMovies(@RequestBody Movies movies){

        return ResponseEntity.ok( moviesService.createMovies(movies));
    }
    @PostMapping("/update-runtime-minutes")

    public ResponseEntity<String>updateRuntimeMinutes(@RequestBody Movies movies) {
        return ResponseEntity.ok(moviesService.updateRuntimeMinutes(movies));
    }
    @GetMapping("/top-rated-movies")

    public List<MoviesDTO>getTopRatedMovies(){

        return moviesService.getTopRatedMovies();
    }

    @GetMapping("/{tconst}")

    public ResponseEntity<Optional<Movies>> getMovie(@PathVariable String tconst) throws MoviesNotFoundException{


        return ResponseEntity.ok(moviesService.getMovie(tconst));
    }
}
