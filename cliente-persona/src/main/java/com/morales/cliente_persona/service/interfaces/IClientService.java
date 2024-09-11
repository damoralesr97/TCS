package com.morales.cliente_persona.service.interfaces;

import com.morales.cliente_persona.dto.ClientDTO;
import com.morales.cliente_persona.utils.exceptions.TCSException;

import java.util.List;

public interface IClientService {

    /**
     * Retorna todos los clientes activos
     * @return List<ClientDTO>
     * @throws
     */
    List<ClientDTO> findAll() throws TCSException;

    /**
     * Retorna un cliente, buscando por identificacion
     * @param dni Identificacion del cliente
     * @return ClientDTO
     * @throws
     */
    ClientDTO findByDni(String dni) throws TCSException;

    /**
     * Registrar cliente nuevo
     * @param clientDTO Cliente a crear
     * @return ClientDTO
     * @throws
     */
    ClientDTO save(ClientDTO clientDTO) throws TCSException;

    /**
     * Actualizar un cliente si existe, buscando por identificacion
     * @param dni Identificacion del cliente
     * @param clientDTO Cliente a actualizar
     * @return ClientDTO
     * @throws
     */
    ClientDTO updateByDni(String dni, ClientDTO clientDTO) throws TCSException;

    /**
     * Eliminar un cliente si existe, buscando por identificacion
     * @param dni Identificacion del cliente
     * @return List<ClientDTO>
     * @throws
     */
    Boolean deleteByDni(String dni) throws TCSException;

}
