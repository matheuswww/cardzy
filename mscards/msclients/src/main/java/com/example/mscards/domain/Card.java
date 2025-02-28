package com.example.mscards.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private CardBrand cardBrand;
    private BigDecimal income;
    private BigDecimal basicLimit;

    public Card(String name, CardBrand cardBrand, BigDecimal income, BigDecimal basicLimit) {
        this.name = name;
        this.cardBrand = cardBrand;
        this.income = income;
        this.basicLimit = basicLimit;
    }
}
