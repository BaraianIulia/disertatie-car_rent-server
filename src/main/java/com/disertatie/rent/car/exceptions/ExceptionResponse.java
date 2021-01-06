package com.disertatie.rent.car.exceptions;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ExceptionResponse {

    private HttpStatus httpStatus;
    private String message;
    private LocalDateTime timestamp;
    private String requestedURI;

    public ExceptionResponse() {
        this.timestamp = LocalDateTime.now();
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getRequestedURI() {
        return requestedURI;
    }

    public void setRequestedURI(String requestedURI) {
        this.requestedURI = requestedURI;
    }
}
