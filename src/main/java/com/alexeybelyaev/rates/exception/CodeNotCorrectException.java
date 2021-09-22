package com.alexeybelyaev.rates.exception;

public class CodeNotCorrectException extends RuntimeException {
    public CodeNotCorrectException(String message) {
        super(message);
    }
}
