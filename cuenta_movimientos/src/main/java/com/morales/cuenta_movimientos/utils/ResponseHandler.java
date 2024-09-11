package com.morales.cuenta_movimientos.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ResponseHandler<T>{

    private Integer code;
    private T value;

}
