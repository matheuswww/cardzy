package com.example.mscreditanalyst.application.ex;

import lombok.Getter;

public class ClientDataNotFoundException extends Exception {
    public ClientDataNotFoundException() {
        super("Client data not found for the cpf provided");
    }
}
