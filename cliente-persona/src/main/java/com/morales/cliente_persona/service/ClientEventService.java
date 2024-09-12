package com.morales.cliente_persona.service;

import com.morales.cliente_persona.dto.ClientAccountEventDTO;
import com.morales.cliente_persona.events.ClientCreatedEvent;
import com.morales.cliente_persona.events.Event;
import com.morales.cliente_persona.events.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class ClientEventService {

    @Autowired
    private KafkaTemplate<String, Event<?>> producer;

    @Value("${topic.account.name:clients}")
    private String topicAccount;

    public void publish(ClientAccountEventDTO clientAccountEventDTO) {
        ClientCreatedEvent createdEvent = new ClientCreatedEvent();
        createdEvent.setData(clientAccountEventDTO);
        createdEvent.setId(UUID.randomUUID().toString());
        createdEvent.setType(EventType.CREATED);
        createdEvent.setDate(new Date());

        this.producer.send(topicAccount, createdEvent);
    }

}
