package com.example.movies.service;

import com.example.movies.entity.Ratings;
import com.example.movies.repository.RatingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingsService {
    @Autowired
    private RatingsRepository ratingsRepository;
    public List<Ratings> getAllRatings(){
        return ratingsRepository.findAll();
    }
}
