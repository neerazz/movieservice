package com.neeraj.microservice.movies.movieservice.graphql.model.domain;

import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

public class ErrorResponse {

    private final LocalDateTime timeStamp;
    private int statusCode;
    private String error;
    private String message;
    private String path;

    public ErrorResponse(HttpStatus status, String message, WebRequest request) {
        this.statusCode = status.value();
        this.error = status.name();
        this.message = message;
        this.timeStamp = LocalDateTime.now();
        this.path = ((ServletWebRequest) request).getRequest().getRequestURI();
    }

    public String getMessage() {
        return message;
    }

    public ErrorResponse setMessage(String message) {
        this.message = message;
        return this;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public ErrorResponse setStatusCode(int statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public String getError() {
        return error;
    }

    public ErrorResponse setError(String error) {
        this.error = error;
        return this;
    }

    public String getPath() {
        return path;
    }

    public ErrorResponse setPath(String path) {
        this.path = path;
        return this;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }
}
