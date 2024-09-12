package com.morales.cliente_persona.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.morales.cliente_persona.utils.enums.AccountTypeEnum;
import com.morales.cliente_persona.utils.enums.GenreEnum;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ClientRequestDTO {

    @Size(min = 10, max = 10, message = "La cedula debe tener 10 digitos")
    private String identificacion;

    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "El nombre solo debe contener letras")
    private String nombre;

    private GenreEnum genero;

    @Min(value = 18,message = "La edad debe ser minima de 18")
    private Integer edad;

    private String direccion;

    @Pattern(regexp = "[0-9]+", message = "El telefono solo debe contener numeros")
    private String telefono;

    @NotNull(message = "La clave es requerida")
    private String clave;

    @NotNull(message = "El tipo de cuenta es requerido")
    private AccountTypeEnum tipoCuenta;

    @NotNull(message = "El saldo inicial de la cuenta es requerido")
    @Min(value = 1,message = "El saldo inicial debe ser mayor o igual que 1")
    private BigDecimal saldoInicial;

    @JsonIgnore
    private boolean estado;

}
