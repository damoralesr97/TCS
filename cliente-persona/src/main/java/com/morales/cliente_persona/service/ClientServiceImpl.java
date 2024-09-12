package com.morales.cliente_persona.service;

import com.morales.cliente_persona.config.NonNullBeanProperties;
import com.morales.cliente_persona.dto.ClientRequestDTO;
import com.morales.cliente_persona.dto.ClientAccountEventDTO;
import com.morales.cliente_persona.dto.ClientResponseDTO;
import com.morales.cliente_persona.dto.mapper.ClientMapper;
import com.morales.cliente_persona.events.ClientCreatedEvent;
import com.morales.cliente_persona.events.Event;
import com.morales.cliente_persona.events.EventType;
import com.morales.cliente_persona.model.Client;
import com.morales.cliente_persona.repository.IClientRepository;
import com.morales.cliente_persona.service.interfaces.IClientService;
import com.morales.cliente_persona.utils.MessageUtil;
import com.morales.cliente_persona.utils.Messages;
import com.morales.cliente_persona.utils.exceptions.TCSException;
import jakarta.validation.ConstraintViolationException;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClientServiceImpl implements IClientService {

    @Autowired
    private IClientRepository clientRepository;

    @Autowired
    private ClientMapper clientMapper;

    @Autowired
    private KafkaTemplate<String, Event<?>> producer;

    @Value("${topic.account.name:clients}")
    private String topicAccount;

    @Override
    public List<ClientResponseDTO> findAll() throws TCSException {
        try {
            List<Client> clientList = this.clientRepository.findByStatusTrue();
            return clientMapper.toResponseDtoList(clientList);
        } catch (Exception e) {
            throw new TCSException(e.getMessage(), e);
        }
    }

    @Override
    public ClientResponseDTO findByDni(String dni) throws TCSException {
        try {
            Optional<Client> client = clientRepository.findByDniAndStatusTrue(dni);
            return clientMapper.toResponseDto(client.orElseThrow(() -> new TCSException(MessageUtil.getMessage(Messages.USER_NOT_FOUND))));
        } catch (Exception e) {
            throw new TCSException(e.getMessage(), e);
        }
    }

    @Override
    public synchronized ClientResponseDTO save(ClientRequestDTO clientRequestDTO) throws TCSException {
        try {
            Client clienteSave = clientMapper.toEntity(clientRequestDTO);
            clienteSave.setStatus(Boolean.TRUE);
            Client client = this.clientRepository.save(clienteSave);

            ClientAccountEventDTO clientAccountEventDTO = ClientAccountEventDTO.builder()
                    .identificacion(client.getDni())
                    .nombre(client.getName())
                    .tipoCuenta(clientRequestDTO.getTipoCuenta())
                    .saldoInicial(clientRequestDTO.getSaldoInicial())
                    .build();

            ClientCreatedEvent createdEvent = new ClientCreatedEvent();
            createdEvent.setData(clientAccountEventDTO);
            createdEvent.setId(UUID.randomUUID().toString());
            createdEvent.setType(EventType.CREATED);
            createdEvent.setDate(new Date());
            this.producer.send(topicAccount, createdEvent);

            return clientMapper.toResponseDto(client);
        } catch (ConstraintViolationException | DataIntegrityViolationException ev){
            throw ev;
        } catch (Exception e) {
            throw new TCSException(e.getMessage(), e);
        }
    }

    @Override
    public ClientResponseDTO updateByDni(String dni, ClientRequestDTO clientRequestDTO) throws TCSException {
        try{
            Client client = clientRepository.findByDniAndStatusTrue(dni).orElseThrow(() -> new TCSException(MessageUtil.getMessage(Messages.USER_NOT_FOUND)));

            BeanUtilsBean beanUtils = new NonNullBeanProperties();
            beanUtils.copyProperties(client, clientMapper.toEntity(clientRequestDTO));

            return clientMapper.toResponseDto(clientRepository.save(client));
        }catch (Exception e){
            throw new TCSException(e.getMessage(), e);
        }
    }

    @Override
    public Boolean deleteByDni(String dni) throws TCSException {
        try {
            Client client = clientRepository.findByDniAndStatusTrue(dni).orElseThrow(() -> new TCSException(MessageUtil.getMessage(Messages.USER_NOT_FOUND)));

            client.setStatus(Boolean.FALSE);
            clientRepository.save(client);

            return Boolean.TRUE;
        } catch (Exception e) {
            throw new TCSException(e.getMessage(), e);
        }
    }

}
