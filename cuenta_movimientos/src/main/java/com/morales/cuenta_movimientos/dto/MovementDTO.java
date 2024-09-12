package com.morales.cuenta_movimientos.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.morales.cuenta_movimientos.utils.enums.MovementTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class MovementDTO {

    private Integer id;
    private String numeroCuenta;
    private String tipoCuenta;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fechaMovimiento;

    private MovementTypeEnum tipoMovimiento;
    private BigDecimal valor;
    private BigDecimal saldo;

}
