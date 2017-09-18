package com.adrrriannn.hoover.exception;

/**
 * Created by adrian on 12/09/17.
 */
public abstract class BaseException extends Exception {

    private ExceptionCode code;

    public BaseException(String message, ExceptionCode code) {
        super(message);
        this.code = code;
    }

    public ExceptionCode getCode () {
        return code;
    }
}