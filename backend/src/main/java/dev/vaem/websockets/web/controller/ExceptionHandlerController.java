package dev.vaem.websockets.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import dev.vaem.websockets.web.dto.error.ErrorDetails;
import dev.vaem.websockets.web.dto.error.ErrorResponse;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        var errors = e.getBindingResult().getFieldErrors().stream()
                .map(err -> new ErrorDetails(err.getField(), err.getDefaultMessage()))
                .toList();
        return ResponseEntity.badRequest().body(new ErrorResponse("invalid input", errors));
    }

}
