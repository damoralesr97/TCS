package com.morales.cliente_persona.dto;

import com.morales.cliente_persona.utils.enums.AccountTypeEnum;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder(toBuilder = true)
public class ClientAccountEventDTO {

    private String identificacion;
    private String nombre;
    private AccountTypeEnum tipoCuenta;
    private BigDecimal saldoInicial;

}
