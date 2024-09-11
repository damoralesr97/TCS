package com.morales.cuenta_movimientos.model;

import com.morales.cuenta_movimientos.utils.enums.AccountTypeEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "T_ACCOUNTS")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACT_ID", unique = true, nullable = false)
    private Integer id;

    @Column(name = "ACT_NUMBER", unique = true, nullable = false)
    private String accountNumber;

    @Column(name = "ACT_TYPE", nullable = false)
    @Enumerated(EnumType.STRING)
    private AccountTypeEnum typeAccount;

    @Column(name = "ACT_BALANCE", nullable = false)
    @Min(value = 1,message = "El saldo inicial debe ser mayor o igual 1")
    private BigDecimal initialBalance;

    @Column(name = "ACT_STATUS")
    private Boolean status;

}
