package com.morales.cuenta_movimientos.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.morales.cuenta_movimientos.utils.enums.AccountTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@JsonPropertyOrder({
        "fecha",
        //"cliente",
        "numeroCuenta",
        "tipo",
        "saldoInicial",
        "estado",
        "movimiento",
        "saldoDisponible"
})
public class ReportDTO {

    @JsonFormat(pattern="yyy-MM-dd")
    @JsonProperty("Fecha")
    private Date fecha;
    //@JsonProperty("Cliente")
    //private String cliente;
    @JsonProperty("Numero Cuenta")
    private String numeroCuenta;
    @JsonProperty("Tipo")
    private AccountTypeEnum tipo;
    @JsonProperty("Saldo Inicial")
    private BigDecimal saldoInicial;
    @JsonProperty("Estado")
    private Boolean estado;
    @JsonProperty("Movimiento")
    private BigDecimal movimiento;
    @JsonProperty("Saldo Disponible")
    private BigDecimal saldoDisponible;

}
