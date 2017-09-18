package com.adrrriannn.hoover.exception;

/**
 * Created by adrian on 18/09/17.
 */
public class InternalServerException extends BaseException {

    public InternalServerException(String message, ExceptionCode exceptionCode) {
        super(message, exceptionCode);
    }
}
