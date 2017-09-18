package com.adrrriannn.hoover.exception;

/**
 * Created by adrian on 12/09/17.
 */
public class BadParameterException extends BaseException {

    public BadParameterException (String message, ExceptionCode code) {
        super(message, code);
    }
}
