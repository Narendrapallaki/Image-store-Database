package com.boardcastaMail.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(InvalidFileExtensionException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ProblemDetail> handleInvalidFileExtensionException(InvalidFileExtensionException ex, HttpServletRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);

        problemDetail.setProperty("message", ex.getMessage());
        problemDetail.setProperty("statusMessage", "error");
        problemDetail.setStatus(HttpStatus.NOT_FOUND);
        problemDetail.setType(URI.create("/error"));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problemDetail);

    }
}
