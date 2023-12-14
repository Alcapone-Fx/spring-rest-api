package com.rest.restapi.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class StudentRestExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException e) {
        StudentErrorResponse studentErrorResponseBody = new StudentErrorResponse();
        studentErrorResponseBody.setStatus(HttpStatus.NOT_FOUND.value());
        studentErrorResponseBody.setMessage(e.getMessage());
        studentErrorResponseBody.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(studentErrorResponseBody, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(Exception e) {
        StudentErrorResponse studentErrorResponseBody = new StudentErrorResponse();
        studentErrorResponseBody.setStatus(HttpStatus.BAD_REQUEST.value());
        studentErrorResponseBody.setMessage(e.getMessage());
        studentErrorResponseBody.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(studentErrorResponseBody, HttpStatus.BAD_REQUEST
        );
    }
}
