package com.morales.cuenta_movimientos.events;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "@type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = ClientCreatedEvent.class, name = "ClientCreatedEvent"),
})
public abstract class Event <T> {
    private String id;
    private Date date;
    private EventType type;
    private T data;
}
