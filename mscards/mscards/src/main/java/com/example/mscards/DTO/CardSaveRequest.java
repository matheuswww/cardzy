package com.example.mscards.DTO;

import com.example.mscards.domain.Card;
import com.example.mscards.domain.CardBrand;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CardSaveRequest {
    private String name;
    private CardBrand brand;
    private BigDecimal income;
    private BigDecimal limit;

    public Card toModel() {
        return new Card(name, brand, income, limit);
    }
}
