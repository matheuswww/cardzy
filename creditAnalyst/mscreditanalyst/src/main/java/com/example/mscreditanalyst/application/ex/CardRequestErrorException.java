package com.example.mscreditanalyst.application.ex;

public class CardRequestErrorException extends RuntimeException {
    public CardRequestErrorException(String message) {
        super(message);
    }
}
