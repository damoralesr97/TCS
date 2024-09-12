package com.morales.cuenta_movimientos.dto;

import com.morales.cuenta_movimientos.utils.enums.AccountTypeEnum;
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
