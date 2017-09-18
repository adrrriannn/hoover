package com.adrrriannn.hoover.exception;

import org.springframework.http.HttpStatus;

/**
 * Created by adrian on 18/09/17.
 */public class ExceptionMessage
{

    public Class exception;
    public String message;
    public int status;
    public long timestamp;
    public String url;
    public ExceptionCode code;

    public ExceptionMessage(BaseException ex, HttpStatus status, String url)
    {
        this.exception = ex.getClass();
        this.message = ex.getMessage();
        this.code = ex.getCode();
        this.status = status.value();
        this.url = url;
        this.timestamp = System.currentTimeMillis();
    }

}
