package com.morales.cuenta_movimientos.model;

import com.morales.cuenta_movimientos.utils.enums.MovementTypeEnum;
import jakarta.persistence.*;
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
@Entity
@Table(name = "T_MOVEMENTS")
public class Movement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MOV_ID", unique = true, nullable = false)
    private Integer id;

    @Column(name = "MOV_DATE", nullable = false)
    private Date movementDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "MOV_TYPE", nullable = false)
    private MovementTypeEnum movementType;

    @Column(name = "MOV_VALUE", nullable = false)
    private BigDecimal value;

    @Column(name = "MOV_BALANCE", nullable = false)
    private BigDecimal balance;

}
