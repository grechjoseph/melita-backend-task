package com.grechjoseph.melitabackendtask.domain.data;

import com.grechjoseph.melitabackendtask.domain.enums.CustomerStatus;
import com.grechjoseph.melitabackendtask.domain.enums.CustomerType;
import lombok.Data;

import java.util.UUID;

/**
 * Represents a Customer object.
 */
@Data
public class Customer {

    private UUID id = UUID.randomUUID();
    private String name;
    private String surname;
    private CustomerType type;
    private CustomerStatus status;

}
