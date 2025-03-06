package com.example.mscreditanalyst.domain.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Card {
    private Long id;
    private String name;
    private String brand;
    private BigDecimal basicLimit;
}
