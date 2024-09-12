package com.morales.cuenta_movimientos.dto.mapper;

import com.morales.cuenta_movimientos.dto.AccountDTO;
import com.morales.cuenta_movimientos.model.Account;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Mapping(source = "accountNumber", target = "numeroCuenta")
    @Mapping(source = "typeAccount", target = "tipoCuenta")
    @Mapping(source = "initialBalance", target = "saldoInicial")
    @Mapping(source = "status", target = "estado")
    AccountDTO toAccountDto(Account account);
    List<AccountDTO> toAccountDtos(List<Account> accounts);

    @InheritInverseConfiguration
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "movements", ignore = true)
    Account toAccount(AccountDTO accountDTO);

}
