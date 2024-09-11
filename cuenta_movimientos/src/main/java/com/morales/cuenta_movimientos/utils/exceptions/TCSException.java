package com.morales.cuenta_movimientos.utils.exceptions;

public class TCSException extends Exception {

    public TCSException(String message, Throwable cause) {
        super(message, cause);
    }

    public TCSException(String message) {
        super(message);
    }

}
