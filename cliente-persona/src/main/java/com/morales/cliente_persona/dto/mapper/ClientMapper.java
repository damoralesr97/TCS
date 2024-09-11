package com.morales.cliente_persona.dto.mapper;

import com.morales.cliente_persona.dto.ClientDTO;
import com.morales.cliente_persona.model.Client;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    @Mapping(source = "name",target = "nombre")
    @Mapping(source = "genre",target = "genero")
    @Mapping(source = "age",target = "edad")
    @Mapping(source = "dni",target = "identificacion")
    @Mapping(source = "address",target = "direccion")
    @Mapping(source = "phoneNumber",target = "telefono")
    @Mapping(source = "password",target = "clave")
    @Mapping(source = "status",target = "estado")
    ClientDTO toClientDto(Client client);
    List<ClientDTO> toClientDtos(List<Client> clients);

    @InheritInverseConfiguration
    @Mapping(target = "id", ignore = true)
    Client toClient(ClientDTO clientDTO);

}
