package com.morales.cuenta_movimientos.dto.mapper;

import com.morales.cuenta_movimientos.dto.ReportDTO;
import com.morales.cuenta_movimientos.model.Movement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReportMapper {

    @Mapping(source = "movementDate", target = "fecha")
    //@Mapping(source = "account.client.name", target = "cliente")
    @Mapping(source = "account.accountNumber", target = "numeroCuenta")
    @Mapping(source = "account.typeAccount", target = "tipo")
    @Mapping(source = "account.initialBalance", target = "saldoInicial")
    @Mapping(source = "account.status", target = "estado")
    @Mapping(source = "value", target = "movimiento")
    @Mapping(source = "balance", target = "saldoDisponible")
    ReportDTO toReportDto(Movement movement);
    List<ReportDTO> toReportDtos(List<Movement> movements);

}
