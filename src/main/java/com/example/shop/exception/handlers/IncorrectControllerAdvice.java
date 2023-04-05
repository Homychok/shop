package com.example.shop.exception.handlers;

import com.example.shop.exception.CategoryIncorrectException;
import com.example.shop.exception.ProductNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class IncorrectControllerAdvice {
    @ExceptionHandler(CategoryIncorrectException.class)
    public ResponseEntity<?> incorrectCategory() {
        return ResponseEntity.notFound().build();
    }
}
