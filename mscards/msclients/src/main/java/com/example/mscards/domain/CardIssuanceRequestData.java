package com.example.mscards.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CardIssuanceRequestData {
    private Long cardId;
    private String cpf;
    private String address;
    private BigDecimal limitReleased;
}
