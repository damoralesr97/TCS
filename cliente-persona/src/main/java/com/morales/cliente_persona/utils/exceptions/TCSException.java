package com.morales.cliente_persona.utils.exceptions;

public class TCSException extends Exception {

    public TCSException(String message, Throwable cause) {
        super(message, cause);
    }

    public TCSException(String message) {
        super(message);
    }

}
