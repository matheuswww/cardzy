package com.example.mscreditanalyst.infra.clients;

import com.example.mscreditanalyst.domain.model.Card;
import com.example.mscreditanalyst.domain.model.ClientCard;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@FeignClient(value = "mscards", path = "/cards")
public interface CardsResourceClient {
    @GetMapping(params = "cpf")
    ResponseEntity<List<ClientCard>> getCardByClient(@RequestParam("cpf") String cpf);
    @GetMapping(params = "income")
    public ResponseEntity<List<Card>> getCardsIncomeUpTo(@RequestParam("income") Long income);
}
