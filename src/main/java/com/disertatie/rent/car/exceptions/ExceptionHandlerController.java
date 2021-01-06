package com.disertatie.rent.car.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHandlerController {


    @ExceptionHandler(ExceptionNotFound.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public @ResponseBody
    ExceptionResponse handleResourceNotFound(final ExceptionNotFound exception, final HttpServletRequest request) {
        ExceptionResponse error = new ExceptionResponse();
        error.setMessage(exception.getMessage());
        error.setRequestedURI(request.getRequestURI());
        return error;
    }

    @ExceptionHandler(ExceptionInvalidLoginCredentials.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public @ResponseBody
    ExceptionResponse handleIncorrectInput(final ExceptionInvalidLoginCredentials exception, final HttpServletRequest request) {
        ExceptionResponse error = new ExceptionResponse();
        error.setMessage(exception.getMessage());
        error.setRequestedURI(request.getRequestURI());
        return error;
    }
}
