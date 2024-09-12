package com.morales.cuenta_movimientos.service;

import com.morales.cuenta_movimientos.dto.AccountDTO;
import com.morales.cuenta_movimientos.events.ClientCreatedEvent;
import com.morales.cuenta_movimientos.events.Event;
import com.morales.cuenta_movimientos.service.interfaces.IAccountService;
import com.morales.cuenta_movimientos.utils.enums.AccountTypeEnum;
import com.morales.cuenta_movimientos.utils.exceptions.TCSException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Slf4j
@Component
public class ClientEventsService {

    @Autowired
    private IAccountService accountService;


    @KafkaListener(
            topics = "${topic.customer.name:clients}",
            containerFactory = "kafkaListenerContainerFactory",
            groupId = "grupo1")
    public void consumer(Event<?> event) throws TCSException {
        if (event.getClass().isAssignableFrom(ClientCreatedEvent.class)) {
            ClientCreatedEvent clientCreatedEvent = (ClientCreatedEvent) event;
            AccountDTO accountDTO = AccountDTO.builder()
                    .numeroCuenta("123456")
                    .tipoCuenta(AccountTypeEnum.AHORRO)
                    .saldoInicial(BigDecimal.valueOf(10))
                    .estado(Boolean.TRUE)
                    .clienteIdentificacion(clientCreatedEvent.getData().getIdentificacion())
                    .clienteNombre(clientCreatedEvent.getData().getNombre())
                    .build();
            this.accountService.save(accountDTO);
        }

    }

}
