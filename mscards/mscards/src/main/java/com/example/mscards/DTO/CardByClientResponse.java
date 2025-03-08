package com.example.mscards.DTO;

import com.example.mscards.domain.ClientCard;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardByClientResponse {
    private String name;
    private String brand;
    private BigDecimal limitReleased;

    public static CardByClientResponse fromModel(ClientCard model) {
        return new CardByClientResponse(
                model.getCard().getName(),
                model.getCard().getCardBrand().toString(),
                model.getCard_limit()
        );
    }
}
