package com.morales.cliente_persona.service;

import com.morales.cliente_persona.dto.ClientAccountEventDTO;
import com.morales.cliente_persona.dto.ClientRequestDTO;
import com.morales.cliente_persona.dto.ClientResponseDTO;
import com.morales.cliente_persona.dto.mapper.ClientMapper;
import com.morales.cliente_persona.events.ClientCreatedEvent;
import com.morales.cliente_persona.events.Event;
import com.morales.cliente_persona.events.EventType;
import com.morales.cliente_persona.model.Client;
import com.morales.cliente_persona.repository.IClientRepository;
import com.morales.cliente_persona.service.ClientServiceImpl;
import com.morales.cliente_persona.utils.enums.AccountTypeEnum;
import com.morales.cliente_persona.utils.enums.GenreEnum;
import com.morales.cliente_persona.utils.exceptions.TCSException;
import jakarta.validation.ConstraintViolationException;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {

    @InjectMocks
    private ClientServiceImpl clientService;

    @Mock
    private IClientRepository clientRepository;

    @Mock
    private ClientMapper clientMapper;

    @Mock
    private ClientEventService clientEventService;

    @Test
    @DisplayName("Test Save Client Successfully")
    void testSaveClient() throws TCSException {

        ClientResponseDTO expected = ClientResponseDTO.builder()
                .identificacion("0105523154")
                .nombre("David Morales")
                .genero(GenreEnum.M)
                .edad(27)
                .direccion("Cuenca")
                .telefono("0995874411")
                .estado(true)
                .build();

        ClientRequestDTO clientRequestDTO = ClientRequestDTO.builder()
                .identificacion("0105523154")
                .nombre("David Morales Rivera")
                .genero(GenreEnum.M)
                .edad(24)
                .direccion("Cuenca")
                .telefono("0995874411")
                .clave("1234")
                .tipoCuenta(AccountTypeEnum.AHORRO)
                .saldoInicial(BigDecimal.valueOf(5))
                .estado(true)
                .build();

        Client clientEntity = new Client();
        clientEntity.setDni("0105523154");
        clientEntity.setName("David Morales Rivera");
        clientEntity.setGenre(GenreEnum.M);
        clientEntity.setAge(24);
        clientEntity.setAddress("Cuenca");
        clientEntity.setPhoneNumber("0995874411");
        clientEntity.setStatus(Boolean.TRUE);

        // Stubbing methods
        when(this.clientMapper.toEntity(any(ClientRequestDTO.class))).thenReturn(clientEntity);
        when(this.clientRepository.save(any(Client.class))).thenReturn(clientEntity);
        when(this.clientMapper.toResponseDto(any(Client.class))).thenReturn(expected);
        doNothing().when(this.clientEventService).publish(Mockito.any());

        // Call the service method
        ClientResponseDTO clientSave = this.clientService.save(clientRequestDTO);

        // Assert that the expected result matches the actual result
        assertThat(expected).isEqualTo(clientSave);
    }

    @Test
    @DisplayName("Test Save Client Exception")
    void testSaveClientException(){

        ClientRequestDTO clientRequestDTO = ClientRequestDTO.builder()
                .identificacion("0105523154")
                .nombre("David Morales Rivera")
                .genero(GenreEnum.M)
                .edad(24)
                .direccion("Cuenca")
                .telefono("0995874411")
                .clave("1234")
                .tipoCuenta(AccountTypeEnum.AHORRO)
                .saldoInicial(BigDecimal.valueOf(5))
                .estado(true)
                .build();

        Client clientEntity = new Client();
        clientEntity.setDni("0105523154");
        clientEntity.setName("David Morales Rivera");
        clientEntity.setGenre(GenreEnum.M);
        clientEntity.setAge(24);
        clientEntity.setAddress("Cuenca");
        clientEntity.setPhoneNumber("0995874411");
        clientEntity.setStatus(Boolean.TRUE);

        when(this.clientMapper.toEntity(any(ClientRequestDTO.class))).thenReturn(clientEntity);
        when(this.clientRepository.save(any(Client.class))).thenThrow(new ConstraintViolationException("", new HashSet<>()));

        //@Then
        Assertions.assertThrows(ConstraintViolationException.class, () -> this.clientService.save(clientRequestDTO));
    }

}
