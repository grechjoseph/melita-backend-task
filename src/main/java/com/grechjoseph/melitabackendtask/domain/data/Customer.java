package com.grechjoseph.melitabackendtask.domain.data;

import com.grechjoseph.melitabackendtask.domain.enums.CustomerStatus;
import com.grechjoseph.melitabackendtask.domain.enums.CustomerType;
import lombok.Data;

/**
 * Represents a Customer object.
 */
@Data
public class Customer {

    /**
     * ID has not been specified its type. Values appear to be UUIDs, but left the implementation as String.
     */
    private String id;
    private String name;
    private String surname;
    private CustomerType type;
    private CustomerStatus status;

}
