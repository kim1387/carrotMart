package com.gh.carrot.carrotmart.commons;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

// 따로 만든 이유가 뭘까? enum을 써보는 거는?
public class HttpStatusResponseEntity {
    public static final ResponseEntity<HttpStatus> RESPONSE_OK = ResponseEntity.status(HttpStatus.OK).build();
    public static final ResponseEntity<HttpStatus> RESPONSE_CONFLICT = ResponseEntity.status(HttpStatus.CONFLICT).build();
    public static final ResponseEntity<HttpStatus> RESPONSE_BAD_REQUEST = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    public static final ResponseEntity<HttpStatus> RESPONSE_NOT_FOUND = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    public static final ResponseEntity<HttpStatus> RESPONSE_UNAUTHORIZED = ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    public static final ResponseEntity<HttpStatus> RESPONSE_FORBIDDEN = ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    public static final ResponseEntity<HttpStatus> RESPONSE_PAYLOAD_TOO_LARGE = ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).build();
}
