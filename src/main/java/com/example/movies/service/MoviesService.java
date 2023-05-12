package com.example.movies.service;

import com.example.movies.dto.MoviesDTO;
import com.example.movies.dto.MoviesWithRating;
import com.example.movies.entity.Movies;

import java.math.BigDecimal;
import java.util.*;

import com.example.movies.entity.Ratings;
import com.example.movies.exception.MoviesNotFoundException;
import com.example.movies.repository.MoviesRepository;
import com.example.movies.repository.RatingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MoviesService {
    @Autowired
    private MoviesRepository moviesRepository;
    @Autowired
    private RatingsRepository ratingsRepository;


    public List<Movies>findTop10ByOrderByRuntimeMinutesDesc(){
        return moviesRepository.findAll();
    }
    public String createMovies(Movies movies){
       moviesRepository.save(movies);
        return "Success";
    }

    public List<MoviesDTO> getTopRatedMovies(){
        List<Ratings>ratings=ratingsRepository.findByAverageRatingGreaterThan(6.0);
        List<MoviesDTO> moviesDTOS=new ArrayList<>();
        for (Ratings rating : ratings) {
            Movies movie = moviesRepository.findById(rating.getTconst()).orElse(null);
            if (movie != null) {
                MoviesDTO moviesDTO = new MoviesDTO();
                moviesDTO.setTconst(movie.getTconst());
                moviesDTO.setPrimaryTitle(movie.getPrimaryTitle());
                moviesDTO.setGenres(movie.getGenres());
                moviesDTO.setAverageRating(rating.getAverageRating());
                moviesDTOS.add(moviesDTO);
            }
        }
        moviesDTOS.sort(Comparator.comparing(MoviesDTO::getAverageRating).reversed());
        return moviesDTOS;
    }
    public String updateRuntimeMinutes(Movies movies){
        moviesRepository.save(movies);
        return "Success";
    }
    public Optional<Movies> getMovie(String tconst) throws MoviesNotFoundException{
        Optional<Movies> movies=moviesRepository.findById(tconst);
        if (movies!=null){
            return movies;
        }
        else {
            throw new MoviesNotFoundException("Movies Not Found Exception!");
        }
    }


    public List<MoviesWithRating> getMovieWithNumVotes() {
        List<Object[]> movWnumV=moviesRepository.getMoviesWithNumVotes();
        List<MoviesWithRating> mList=new ArrayList<>();
        MoviesWithRating mwr=null;
        for(Object[] o:movWnumV){
            mwr=new MoviesWithRating();
            mwr.setGenres((String) o[0]);
            mwr.setPrimaryTitle((String) o[1]);
            mwr.setNumVotes((BigDecimal) o[2]);
            mwr.setSum((BigDecimal) o[2]);
            mList.add(mwr);
        }

        return mList;
    }

}

