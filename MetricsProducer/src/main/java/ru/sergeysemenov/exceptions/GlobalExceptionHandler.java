package ru.sergeysemenov.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<AppError> handleException(NotFoundException e) {
        return ResponseEntity.status(404).body(new AppError("Metric not found", e.getMessage()));
    }


}
