package com.example.mscreditanalyst.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Queue;

@Configuration
public class MQConfig {
    @Value("${mq.queues.card-issuance}")
    private String cardIssuanceQueue;

    @Bean
    public Queue cardIssuanceCard() {
        return new org.springframework.amqp.core.Queue( cardIssuanceQueue );
    }

}
