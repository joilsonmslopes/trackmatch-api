package com.trackmatch.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


@RestControllerAdvice
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

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(404, errors));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleEnumError(HttpMessageNotReadableException ex) {
        Throwable cause = ex.getCause();
        if (cause instanceof InvalidFormatException formatEx && formatEx.getTargetType().isEnum()) {

            String fieldName = "enumField";

            if (!formatEx.getPath().isEmpty()) {
                fieldName = formatEx.getPath().get(0).getFieldName();
            }

            String invalidValue = formatEx.getValue().toString();
            String enumName = formatEx.getTargetType().getSimpleName();
            String[] expectedValues = Arrays.stream(formatEx.getTargetType().getEnumConstants())
                    .map(Object::toString)
                    .toArray(String[]::new);

            String message = String.format(
                    "Valor inválido '%s' para o tipo %s. Valores esperados: %s",
                    invalidValue,
                    enumName,
                    String.join(", ", expectedValues)
            );

            Map<String, String> errors = Map.of(fieldName, message);
            return ResponseEntity.badRequest().body(new ErrorResponse(400, errors));
        }

        Map<String, String> errors = Map.of("error", "Requisição malformada");
        return ResponseEntity.badRequest().body(new ErrorResponse(400, errors));
    }
}
