package com.example.mscards.application;

import com.example.mscards.DTO.CardByClientResponse;
import com.example.mscards.DTO.CardSaveRequest;
import com.example.mscards.domain.Card;
import com.example.mscards.domain.ClientCard;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("cards")
@RequiredArgsConstructor
public class CardsResource {
    private final CardService cardService;
    private final ClientCardService clientCardService;

    @GetMapping
    public String status() {
        return "ok";
    }

    @PostMapping
    public ResponseEntity signup(@RequestBody CardSaveRequest request) {
        Card card = request.toModel();
        cardService.save(card);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(params = "income")
    public ResponseEntity<List<Card>> getCardsIncomeUpTo(@RequestParam("income") Long income) {
        List<Card> list = cardService.GetIncomeCardLessThanEqual(income);
        return ResponseEntity.ok(list);
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<List<CardByClientResponse>> getCardByClient(@RequestParam("cpf") String cpf) {
        List<ClientCard> list = clientCardService.listCardByCpf(cpf);
        List<CardByClientResponse> resultList = list.stream().map(CardByClientResponse::fromModel).collect(Collectors.toList());
        return ResponseEntity.ok(resultList);
    }
}
