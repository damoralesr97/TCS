package com.morales.cuenta_movimientos.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class MovementRequestDTO {

    @NotNull(message = "El numero de cuenta es requerido")
    private String numeroCuenta;

    @NotNull(message = "El valor del movimiento es requerido")
    private BigDecimal valor;

}
