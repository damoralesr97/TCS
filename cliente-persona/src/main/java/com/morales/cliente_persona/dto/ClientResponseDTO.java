package com.morales.cliente_persona.dto;

import com.morales.cliente_persona.utils.enums.GenreEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ClientResponseDTO {

    private String identificacion;
    private String nombre;
    private GenreEnum genero;
    private Integer edad;
    private String direccion;
    private String telefono;
    private Boolean estado;

}
