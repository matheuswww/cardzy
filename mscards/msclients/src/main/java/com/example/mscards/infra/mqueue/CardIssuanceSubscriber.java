package com.example.mscards.infra.mqueue;

import com.example.mscards.domain.Card;
import com.example.mscards.domain.CardIssuanceRequestData;
import com.example.mscards.domain.ClientCard;
import com.example.mscards.infra.repository.CardRepository;
import com.example.mscards.infra.repository.ClientCardRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CardIssuanceSubscriber {
    private final CardRepository cardRepository;
    private final ClientCardRepository clientCardRepository;

    @RabbitListener(queues = "${mq.queues.card-issuance}")
    public void ReceiveIssuanceRequest(@Payload String payload) {
        try {
            var mapper = new ObjectMapper();
            CardIssuanceRequestData data = mapper.readValue(payload, CardIssuanceRequestData.class);
            Card card = cardRepository.findById(data.getCardId()).orElseThrow();
            ClientCard clientCard = new ClientCard();
            clientCard.setCard(card);
            clientCard.setCpf(data.getCpf());
            clientCard.setCard_limit(data.getLimitReleased());

            clientCardRepository.save(clientCard);
        } catch(JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
