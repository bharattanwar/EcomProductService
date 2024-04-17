package com.example.demo.exceptions;

public class InvalidProductIdException extends RuntimeException{
    public InvalidProductIdException(String message){
        super(message);
    }
}
