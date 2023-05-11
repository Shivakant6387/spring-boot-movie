package com.example.movies.exception;

public class MoviesNotFoundException extends RuntimeException{
    public MoviesNotFoundException(String message){
        super(message);
    }
    public MoviesNotFoundException(String message,Throwable cause){
        super(message);
    }
}
