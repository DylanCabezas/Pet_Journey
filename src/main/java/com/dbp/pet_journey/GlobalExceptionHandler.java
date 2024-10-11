package com.dbp.pet_journey;

import com.dbp.pet_journey.Exceptions.ErrorMessage;
import com.dbp.pet_journey.Exceptions.ResourceConflictException;
import com.dbp.pet_journey.Exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleResourceNotFoundException(ResourceNotFoundException e) {
        ErrorMessage error = new ErrorMessage(e.getMessage(), HttpStatus.NOT_FOUND.toString());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(ResourceConflictException.class)
    public ResponseEntity<ErrorMessage> handleResourceConflictException(ResourceConflictException e) {
        ErrorMessage error = new ErrorMessage(e.getMessage(), HttpStatus.CONFLICT.toString());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }
}
