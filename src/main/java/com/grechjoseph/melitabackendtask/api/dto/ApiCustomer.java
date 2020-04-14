package com.grechjoseph.melitabackendtask.api.dto;

import com.grechjoseph.melitabackendtask.domain.enums.CustomerStatus;
import com.grechjoseph.melitabackendtask.domain.enums.CustomerType;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Endpoint object used for writing to Customers.
 */
@Data
@RequiredArgsConstructor
public class ApiCustomer {

    @NotNull(message = "Name cannot be empty.")
    @Size(min = 2, max = 20, message = "Name must be between 2 and 20 characters long.")
    private final String name;

    @NotNull(message = "Surname cannot be empty.")
    @Size(min = 2, max = 30, message = "Surname must be between 2 and 20 characters long.")
    private final String surname;

    @Valid
    @NotNull(message = "Type cannot be empty.")
    private final CustomerType type;

    @NotNull(message = "Status cannot be empty")
    private final CustomerStatus status;

}
