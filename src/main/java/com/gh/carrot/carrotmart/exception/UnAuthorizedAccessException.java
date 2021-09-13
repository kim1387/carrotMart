package com.gh.carrot.carrotmart.exception;

public class UnAuthorizedAccessException extends RuntimeException {

    public UnAuthorizedAccessException() {
    }

    public UnAuthorizedAccessException(String message) {
        super(message);
    }

    public UnAuthorizedAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}