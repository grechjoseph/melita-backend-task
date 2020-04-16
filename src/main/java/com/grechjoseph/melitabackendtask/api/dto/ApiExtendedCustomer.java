package com.grechjoseph.melitabackendtask.api.dto;

import com.grechjoseph.melitabackendtask.domain.enums.CustomerStatus;
import com.grechjoseph.melitabackendtask.domain.enums.CustomerType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

/**
 * Endpoint object representing Customer reads.
 */
@Getter
@ApiModel("An object representing a retrieved Customer.")
public class ApiExtendedCustomer extends ApiCustomer {

    @ApiModelProperty(value = "Customer's ID.")
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
