package com.morales.cuenta_movimientos.events;

import com.morales.cuenta_movimientos.dto.ClientAccountEventDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ClientCreatedEvent extends Event<ClientAccountEventDTO>{
}
