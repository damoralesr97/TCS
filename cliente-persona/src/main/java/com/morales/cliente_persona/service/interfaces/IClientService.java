package com.morales.cliente_persona.service.interfaces;

import com.morales.cliente_persona.dto.ClientRequestDTO;
import com.morales.cliente_persona.dto.ClientResponseDTO;
import com.morales.cliente_persona.utils.exceptions.TCSException;

import java.util.List;

public interface IClientService {

    /**
     * Retorna todos los clientes activos
     * @return List<ClientResponseDTO>
     * @throws
     */
    List<ClientResponseDTO> findAll() throws TCSException;

    /**
     * Retorna un cliente, buscando por identificacion
     * @param dni Identificacion del cliente
     * @return ClientResponseDTO
     * @throws
     */
    ClientResponseDTO findByDni(String dni) throws TCSException;

    /**
     * Registrar cliente nuevo
     * @param clientRequestDTO Cliente a crear
     * @return ClientResponseDTO
     * @throws
     */
    ClientResponseDTO save(ClientRequestDTO clientRequestDTO) throws TCSException;

    /**
     * Actualizar un cliente si existe, buscando por identificacion
     * @param dni Identificacion del cliente
     * @param clientRequestDTO Cliente a actualizar
     * @return ClientResponseDTO
     * @throws
     */
    ClientResponseDTO updateByDni(String dni, ClientRequestDTO clientRequestDTO) throws TCSException;

    /**
     * Eliminar un cliente si existe, buscando por identificacion
     * @param dni Identificacion del cliente
     * @return Boolean
     * @throws
     */
    Boolean deleteByDni(String dni) throws TCSException;

}
