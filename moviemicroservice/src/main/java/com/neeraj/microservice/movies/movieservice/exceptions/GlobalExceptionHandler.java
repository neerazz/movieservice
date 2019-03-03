package com.neeraj.microservice.movies.movieservice.exceptions;

import com.neeraj.microservice.movies.movieservice.domain.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/*

    HTTP Status Codes:

    200	Success	            The request has succeeded
    201	Created	            The request has been fulfilled and resulted in a new resource being created
    204	No Content	        The request has fulfilled the request but does not need to return an entity body
    206	Partial Content	    The server has fulfilled the partial GET request for the resource
    400	Bad request	        The request could not be understood by the server due to malformed syntax
    404	Not Found	        The server has not found anything matching the request URI
    405	Method Not allowed	The method specified in the request is not allowed for the resource identified by the request URI
    409	Conflict	        The request could not be completed due to a conflict with the current state of resource

 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoSuchObjectFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(NoSuchObjectFoundException e, WebRequest request) {

        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        ErrorResponse errorResponse = new ErrorResponse(httpStatus, e.getMessage(), request);
        return new ResponseEntity<>(errorResponse, httpStatus);
    }
}
