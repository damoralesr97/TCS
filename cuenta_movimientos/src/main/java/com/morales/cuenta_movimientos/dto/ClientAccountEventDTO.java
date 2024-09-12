package com.morales.cuenta_movimientos.dto;

import com.morales.cuenta_movimientos.utils.enums.AccountTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class ClientAccountEventDTO {

    private String identificacion;
    private String nombre;
    private AccountTypeEnum tipoCuenta;
    private BigDecimal saldoInicial;

}
