package com.adrrriannn.hoover.controller;

import com.adrrriannn.hoover.exception.BadParameterException;
import com.adrrriannn.hoover.exception.ExceptionCode;
import com.adrrriannn.hoover.exception.ExceptionMessage;
import com.adrrriannn.hoover.exception.InternalServerException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by adrian on 12/09/17.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionMessage handleMethodArgumentNotValidException(HttpServletRequest request, MethodArgumentNotValidException ex) {

        BindingResult bindingResult = ex.getBindingResult();
        FieldError fieldError = bindingResult.getFieldError();

        BadParameterException badParameterException =  new BadParameterException(fieldError.getDefaultMessage(), ExceptionCode.fromString(fieldError.getCode()));
        return new ExceptionMessage(badParameterException, HttpStatus.BAD_REQUEST, request.getRequestURI());
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ExceptionMessage handleExceptions(HttpServletRequest request, Exception ex) {
        InternalServerException internalServerException = new InternalServerException(ex.getMessage(), ExceptionCode.INTERNAL_SERVER_ERROR);
        return new ExceptionMessage(internalServerException, HttpStatus.INTERNAL_SERVER_ERROR, request.getRequestURI());
    }

}
