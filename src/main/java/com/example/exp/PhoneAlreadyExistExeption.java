package com.example.exp;

public class PhoneAlreadyExistExeption extends RuntimeException{
    public PhoneAlreadyExistExeption(String message) {
        super(message);
    }
}
