package com.morales.cliente_persona.dto.mapper;

import com.morales.cliente_persona.dto.ClientRequestDTO;
import com.morales.cliente_persona.dto.ClientResponseDTO;
import com.morales.cliente_persona.model.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    // De Client a ClientResponseDTO
    @Mapping(source = "dni", target = "identificacion")
    @Mapping(source = "name", target = "nombre")
    @Mapping(source = "genre", target = "genero")
    @Mapping(source = "age", target = "edad")
    @Mapping(source = "address", target = "direccion")
    @Mapping(source = "phoneNumber", target = "telefono")
    @Mapping(source = "status", target = "estado")
    ClientResponseDTO toResponseDto(Client client);

    // De ClientRequestDTO a Client
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "nombre", target = "name")
    @Mapping(source = "genero", target = "genre")
    @Mapping(source = "edad", target = "age")
    @Mapping(source = "identificacion", target = "dni")
    @Mapping(source = "direccion", target = "address")
    @Mapping(source = "telefono", target = "phoneNumber")
    @Mapping(source = "clave", target = "password")
    @Mapping(source = "estado", target = "status")
    Client toEntity(ClientRequestDTO clientRequestDTO);

    // Mapeo de lista de Client a lista de ClientResponseDTO
    List<ClientResponseDTO> toResponseDtoList(List<Client> clients);

}
