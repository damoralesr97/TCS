package com.morales.cuenta_movimientos.utils.enums;

public enum AccountTypeEnum {

    AHORRO("ahorros"),
    CORRIENTE("corriente");

    private String typeAccount;

    AccountTypeEnum(String typeAccount) {
        this.typeAccount = typeAccount;
    }

}
