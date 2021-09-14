package com.gh.carrot.carrotmart.advice;

import com.gh.carrot.carrotmart.exception.MemberNotFoundException;
import com.gh.carrot.carrotmart.exception.UnAuthorizedAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.gh.carrot.carrotmart.commons.HttpStatusResponseEntity.RESPONSE_NOT_FOUND;
import static com.gh.carrot.carrotmart.commons.HttpStatusResponseEntity.RESPONSE_UNAUTHORIZED;

@RestControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(MemberNotFoundException.class)
    public ResponseEntity<HttpStatus> memberNotFoundException() {
        return RESPONSE_NOT_FOUND;
    }

    @ExceptionHandler(UnAuthorizedAccessException.class)
    public ResponseEntity<HttpStatus> unAuthorizedAccessException() {
        return RESPONSE_UNAUTHORIZED;
    }
}