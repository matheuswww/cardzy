package com.example.mscreditanalyst.application;

import com.example.mscreditanalyst.application.ex.ClientDataNotFoundException;
import com.example.mscreditanalyst.application.ex.MicroservicesComunicationErrorException;
import com.example.mscreditanalyst.domain.model.*;
import com.example.mscreditanalyst.infra.clients.CardsResourceClient;
import com.example.mscreditanalyst.infra.clients.ClientResourceClient;
import feign.FeignException;
import feign.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CreditEvaluatorService {
    private final ClientResourceClient clientsClient;
    private final CardsResourceClient cardsClient;

    public ClientSituation getClientSituation(String cpf) throws ClientDataNotFoundException, MicroservicesComunicationErrorException {
        try {
            ResponseEntity<ClientData> clientDataResponse =  clientsClient.clientData(cpf);
            ResponseEntity<List<ClientCard>> cardsResponse = cardsClient.getCardByClient(cpf);
            return  ClientSituation.builder().client(clientDataResponse.getBody()).cards(cardsResponse.getBody()).build();
        } catch (FeignException.FeignClientException e) {
            int status = e.status();
            if (status == HttpStatus.NOT_FOUND.value()) {
                throw new ClientDataNotFoundException();
            }
            throw new MicroservicesComunicationErrorException(e.getMessage(), status);
        }
    }

    @PostMapping
    public ReturnClientEvaluation performEvaluation(String cpf, Long income) throws ClientDataNotFoundException, MicroservicesComunicationErrorException {
        try {
            ResponseEntity<ClientData> clientDataResponse = clientsClient.clientData(cpf);
            ResponseEntity<List<Card>> cardResponse = cardsClient.getCardsIncomeUpTo(income);
            List<Card> cards = cardResponse.getBody();
            var approvedCardList = cards.stream().map(card -> {
                ClientData clientData = clientDataResponse.getBody();

                BigDecimal basicLimit = card.getBasicLimit();
                BigDecimal ageBD = BigDecimal.valueOf(clientData.getAge());
                var factor = ageBD.divide(BigDecimal.valueOf(10));
                BigDecimal approvedLimit = factor.multiply(basicLimit);

                ApprovedCard approved = new ApprovedCard();
                approved.setCard(card.getName());
                approved.setBrand(card.getBrand());
                approved.setApprovedLimit(approvedLimit);

                return approved;
            }).collect(Collectors.toList());

            return new ReturnClientEvaluation(approvedCardList);
        } catch (FeignException.FeignClientException e) {
            int status = e.status();
            if (status == HttpStatus.NOT_FOUND.value()) {
                throw new ClientDataNotFoundException();
            }
            throw new MicroservicesComunicationErrorException(e.getMessage(), status);
        }
    }
}
