package com.morales.cuenta_movimientos.dto.mapper;

import com.morales.cuenta_movimientos.dto.AccountDTO;
import com.morales.cuenta_movimientos.model.Account;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    // Mapeo de Account a AccountDTO
    @Mapping(source = "accountNumber", target = "numeroCuenta")
    @Mapping(source = "typeAccount", target = "tipoCuenta")
    @Mapping(source = "initialBalance", target = "saldoInicial")
    @Mapping(source = "status", target = "estado")
    @Mapping(source = "clientDni", target = "clienteIdentificacion")
    @Mapping(source = "clientName", target = "clienteNombre")
    AccountDTO toDTO(Account account);

    // Mapeo de AccountDTO a Account
    @Mapping(source = "numeroCuenta", target = "accountNumber")
    @Mapping(source = "tipoCuenta", target = "typeAccount")
    @Mapping(source = "saldoInicial", target = "initialBalance")
    @Mapping(source = "estado", target = "status")
    @Mapping(source = "clienteIdentificacion", target = "clientDni")
    @Mapping(source = "clienteNombre", target = "clientName")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "movements", ignore = true)
    Account toEntity(AccountDTO accountDTO);

    // Mapeo de List<Account> a List<AccountDTO>
    List<AccountDTO> toDTOList(List<Account> accounts);

    // Mapeo de List<AccountDTO> a List<Account>
    List<Account> toEntityList(List<AccountDTO> accountDTOs);

}
