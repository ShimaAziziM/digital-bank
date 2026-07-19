package com.digitalbank.exception;

public class CustomerAlreadyActiveException extends RuntimeException{
    public CustomerAlreadyActiveException(String message){
        super(message);
    }
}
