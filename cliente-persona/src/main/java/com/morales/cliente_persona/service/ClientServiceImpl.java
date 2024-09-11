package com.morales.cliente_persona.service;

import com.morales.cliente_persona.config.NonNullBeanProperties;
import com.morales.cliente_persona.dto.ClientDTO;
import com.morales.cliente_persona.dto.mapper.ClientMapper;
import com.morales.cliente_persona.model.Client;
import com.morales.cliente_persona.repository.ClientRepository;
import com.morales.cliente_persona.service.interfaces.IClientService;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements IClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientMapper clientMapper;

    @Override
    public List<ClientDTO> findAll() {
        try {
            List<Client> clientList = this.clientRepository.findAll();
            return clientMapper.toClientDtos(clientList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ClientDTO findByDni(String dni) {
        try {
            Optional<Client> client = clientRepository.findByDni(dni);
            return clientMapper.toClientDto(client.orElseThrow());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ClientDTO save(ClientDTO clientDTO) {
        try {
            return clientMapper.toClientDto(clientRepository.save(clientMapper.toClient(clientDTO)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ClientDTO updateByDni(String dni, ClientDTO clientDTO) {
        try{
            Client client = clientRepository.findByDni(dni).orElseThrow();

            BeanUtilsBean beanUtils = new NonNullBeanProperties();
            beanUtils.copyProperties(client, clientMapper.toClient(clientDTO));

            return clientMapper.toClientDto(clientRepository.save(client));
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean deleteByDni(String dni) {
        Client client = clientRepository.findByDni(dni).orElseThrow();

        client.setState(Boolean.FALSE);
        clientRepository.save(client);

        return Boolean.TRUE;
    }

}
