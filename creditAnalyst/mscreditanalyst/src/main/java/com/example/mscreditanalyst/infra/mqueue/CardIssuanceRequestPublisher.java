package com.example.mscreditanalyst.infra.mqueue;

import com.example.mscreditanalyst.domain.model.CardIssuanceRequestData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CardIssuanceRequestPublisher {
    private final RabbitTemplate rabbitTemplate;
    private final Queue cardIssuanceQueue;

    public void RequestCard(CardIssuanceRequestData data) throws JsonProcessingException {
        var json = convertIntoJson(data);
        rabbitTemplate.convertAndSend(cardIssuanceQueue.getName(), json);
    }

    private String convertIntoJson(CardIssuanceRequestData data) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        var json = mapper.writeValueAsString(data);
        return json;
    }
}
