package com.example.mscreditanalyst.application.ex;

import lombok.Getter;

public class MicroservicesComunicationErrorException extends Exception {
    @Getter
    private Integer status;
    public MicroservicesComunicationErrorException(String msg, Integer status) {
        super(msg);
        this.status = status;
    }
}
