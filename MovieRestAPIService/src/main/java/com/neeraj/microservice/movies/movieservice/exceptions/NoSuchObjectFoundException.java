package com.neeraj.microservice.movies.movieservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NoSuchObjectFoundException extends RuntimeException {
    public NoSuchObjectFoundException(String message) {
        super(message);
    }
}
