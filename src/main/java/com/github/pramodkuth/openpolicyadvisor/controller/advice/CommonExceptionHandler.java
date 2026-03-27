package com.github.pramodkuth.openpolicyadvisor.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CommonExceptionHandler {
    @ExceptionHandler(Exception.class)
    ResponseEntity<ProblemDetail> handleException(SecurityException e){
        ProblemDetail problemDetail=ProblemDetail.forStatus(HttpStatus.FORBIDDEN);
        problemDetail.setDetail(e.getMessage());
        return ResponseEntity.of(problemDetail).build();
    }
}
