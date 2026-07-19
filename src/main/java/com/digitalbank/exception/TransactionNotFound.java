package com.digitalbank.exception;

public class TransactionNotFound extends RuntimeException{
    public TransactionNotFound (String message){
        super(message);
    }
}
