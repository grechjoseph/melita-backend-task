package com.grechjoseph.melitabackendtask.api.dto;

import com.grechjoseph.melitabackendtask.domain.enums.CustomerStatus;
import com.grechjoseph.melitabackendtask.domain.enums.CustomerType;
import lombok.Getter;

import java.util.UUID;

/**
 * Endpoint object representing Customer reads.
 */
@Getter
public class ApiExtendedCustomer extends ApiCustomer {

    private final UUID id;

    public ApiExtendedCustomer(final UUID id,
                               final String name,
                               final String surname,
                               final CustomerType type,
                               final CustomerStatus status) {
        super(name, surname, type, status);
        this.id = id;
    }

}
