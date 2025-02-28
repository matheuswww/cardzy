package com.example.mscards.application;

import com.example.mscards.domain.Card;
import com.example.mscards.infra.repository.CardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardRepository repository;

    @Transactional
    public Card save(Card card) {
        return repository.save(card);
    }

    public List<Card> GetIncomeCardLessThanEqual(Long income) {
        var incomeCard = BigDecimal.valueOf(income);
        return repository.findByIncomeLessThanEqual(incomeCard);
    }

}
