package com.trackmatch.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationErrors(MethodArgumentNotValidException ex) {
     Map<String, String> fieldErrors = ex.getBindingResult()
             .getFieldErrors()
             .stream()
             .collect(Collectors.toMap(
                     fieldError -> fieldError.getField(),
                     fieldError -> fieldError.getDefaultMessage(),
                     (existing, replacement) -> replacement
             ));

     return ResponseEntity.badRequest().body(new ErrorResponse(400, fieldErrors));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(NotFoundException ex) {
        Map<String, String> errors = new HashMap<>();

        errors.put(ex.getType().name().toLowerCase(), ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(400, errors));
    }
}
