package com.morales.cuenta_movimientos.dto.mapper;

import com.morales.cuenta_movimientos.dto.MovementDTO;
import com.morales.cuenta_movimientos.model.Movement;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MovementMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "account.accountNumber", target = "numeroCuenta")
    @Mapping(source = "account.typeAccount", target = "tipoCuenta")
    @Mapping(source = "movementDate", target = "fechaMovimiento")
    @Mapping(source = "movementType", target = "tipoMovimiento")
    @Mapping(source = "value", target = "valor")
    @Mapping(source = "balance", target = "saldo")
    MovementDTO toMovementDto(Movement movement);
    List<MovementDTO> toMovementDtos(List<Movement> movements);

    @InheritInverseConfiguration
    @Mapping(target = "account", ignore = true)
    Movement toMovement(MovementDTO movementDTO);

}
