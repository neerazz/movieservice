package com.neeraj.microservice.movies.movieservice.graphql.exceptions;

public class NoSuchObjectFoundException extends RuntimeException {
    public NoSuchObjectFoundException(String message) {
        super(message);
    }
}
