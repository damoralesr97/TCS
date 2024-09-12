package com.morales.cliente_persona.events;

import com.morales.cliente_persona.dto.ClientAccountEventDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ClientCreatedEvent extends Event<ClientAccountEventDTO> {

}
