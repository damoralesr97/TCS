package com.morales.cliente_persona.service;

import com.morales.cliente_persona.config.NonNullBeanProperties;
import com.morales.cliente_persona.dto.ClientDTO;
import com.morales.cliente_persona.dto.mapper.ClientMapper;
import com.morales.cliente_persona.model.Client;
import com.morales.cliente_persona.repository.IClientRepository;
import com.morales.cliente_persona.service.interfaces.IClientService;
import com.morales.cliente_persona.utils.MessageUtil;
import com.morales.cliente_persona.utils.Messages;
import com.morales.cliente_persona.utils.exceptions.TCSException;
import jakarta.validation.ConstraintViolationException;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements IClientService {

    @Autowired
    private IClientRepository clientRepository;

    @Autowired
    private ClientMapper clientMapper;

    @Override
    public List<ClientDTO> findAll() throws TCSException {
        try {
            List<Client> clientList = this.clientRepository.findByStatusTrue();
            return clientMapper.toClientDtos(clientList);
        } catch (Exception e) {
            throw new TCSException(e.getMessage(), e);
        }
    }

    @Override
    public ClientDTO findByDni(String dni) throws TCSException {
        try {
            Optional<Client> client = clientRepository.findByDniAndStatusTrue(dni);
            return clientMapper.toClientDto(client.orElseThrow(() -> new TCSException(MessageUtil.getMessage(Messages.USER_NOT_FOUND))));
        } catch (Exception e) {
            throw new TCSException(e.getMessage(), e);
        }
    }

    @Override
    public synchronized ClientDTO save(ClientDTO clientDTO) throws TCSException {
        try {
            return clientMapper.toClientDto(this.clientRepository.save(clientMapper.toClient(clientDTO)));
        } catch (ConstraintViolationException | DataIntegrityViolationException ev){
            throw ev;
        } catch (Exception e) {
            throw new TCSException(e.getMessage(), e);
        }
    }

    @Override
    public ClientDTO updateByDni(String dni, ClientDTO clientDTO) throws TCSException {
        try{
            Client client = clientRepository.findByDniAndStatusTrue(dni).orElseThrow(() -> new TCSException(MessageUtil.getMessage(Messages.USER_NOT_FOUND)));

            BeanUtilsBean beanUtils = new NonNullBeanProperties();
            beanUtils.copyProperties(client, clientMapper.toClient(clientDTO));

            return clientMapper.toClientDto(clientRepository.save(client));
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
