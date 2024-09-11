package com.morales.cliente_persona.service.interfaces;

import com.morales.cliente_persona.dto.ClientDTO;

import java.util.List;

public interface IClientService {

    /**
     * Retorna todos los clientes activos
     * @return List<ClientDTO>
     * @throws
     */
    List<ClientDTO> findAll();

    /**
     * Retorna un cliente, buscando por identificacion
     * @param dni Identificacion del cliente
     * @return ClientDTO
     * @throws
     */
    ClientDTO findByDni(String dni);

    /**
     * Registrar cliente nuevo
     * @param clientDTO Cliente a crear
     * @return ClientDTO
     * @throws
     */
    ClientDTO save(ClientDTO clientDTO);

    /**
     * Actualizar un cliente si existe, buscando por identificacion
     * @param dni Identificacion del cliente
     * @param clientDTO Cliente a actualizar
     * @return ClientDTO
     * @throws
     */
    ClientDTO updateByDni(String dni, ClientDTO clientDTO);

    /**
     * Eliminar un cliente si existe, buscando por identificacion
     * @param dni Identificacion del cliente
     * @return List<ClientDTO>
     * @throws
     */
    Boolean deleteByDni(String dni);

}
