package com.morales.cuenta_movimientos.utils.enums;

public enum MovementTypeEnum {

    DEPOSITO("deposito"),
    RETIRO("retiro");

    private String typeMovement;

    MovementTypeEnum(String typeMovement) {
        this.typeMovement = typeMovement;
    }

}
