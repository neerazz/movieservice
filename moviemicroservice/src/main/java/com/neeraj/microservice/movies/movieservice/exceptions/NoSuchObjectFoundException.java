package com.neeraj.microservice.movies.movieservice.exceptions;

public class NoSuchObjectFoundException extends RuntimeException {
    public NoSuchObjectFoundException(String message) {
        super(message);
    }
}
