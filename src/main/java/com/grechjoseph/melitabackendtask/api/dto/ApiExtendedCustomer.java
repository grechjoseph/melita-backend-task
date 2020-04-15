package com.grechjoseph.melitabackendtask.api.dto;

import com.grechjoseph.melitabackendtask.domain.enums.CustomerStatus;
import com.grechjoseph.melitabackendtask.domain.enums.CustomerType;
import lombok.Getter;

/**
 * Endpoint object representing Customer reads.
 */
@Getter
public class ApiExtendedCustomer extends ApiCustomer {

    private final String id;

    public ApiExtendedCustomer(final String id,
                               final String name,
                               final String surname,
                               final CustomerType type,
                               final CustomerStatus status) {
        super(name, surname, type, status);
        this.id = id;
    }

}
