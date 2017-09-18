package com.adrrriannn.hoover.exception;

/**
 * Created by adrian on 12/09/17.
 */
public enum ExceptionCode {
    INVALID_PATCHES, INVALID_INITIAL_POSITION, INVALID_INSTRUCTIONS, INVALID_ROOM_SIZE,
    INTERNAL_SERVER_ERROR, UNKNOWN;

    public String getCode() {
        return this.toString();
    }

    public static ExceptionCode fromString(String input) {
        for(ExceptionCode exceptionCode : values()) {
            if(exceptionCode.getCode().equals(input)){
                return exceptionCode;
            }
        }

        return UNKNOWN;
    }
}
