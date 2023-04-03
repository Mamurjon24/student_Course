package com.example.controller;

import com.example.exp.AppBadRequestException;
import com.example.exp.PhoneAlreadyExistExeption;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class AdviceController {
    @ExceptionHandler({AppBadRequestException.class, PhoneAlreadyExistExeption.class})
    public ResponseEntity<String> handleException(RuntimeException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
