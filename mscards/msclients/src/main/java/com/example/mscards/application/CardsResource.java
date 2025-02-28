package com.example.mscards.application;

import com.example.mscards.DTO.CardSaveRequest;
import com.example.mscards.domain.Card;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cards")
@RequiredArgsConstructor
public class CardsResource {
    private final CardService service;

    @GetMapping
    public String status() {
        return "ok";
    }

    @PostMapping
    public ResponseEntity signup(@RequestBody CardSaveRequest request) {
        Card card = request.toModel();
        service.save(card);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(params = "income")
    public ResponseEntity<List<Card>> getCardsIncomeUpTo(@RequestParam("income") Long income) {
        List<Card> list = service.GetIncomeCardLessThanEqual(income);
        return ResponseEntity.ok(list);
    }


}
