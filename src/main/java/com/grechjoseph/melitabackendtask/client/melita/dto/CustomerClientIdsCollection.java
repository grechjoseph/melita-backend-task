package com.grechjoseph.melitabackendtask.client.melita.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Set;

/**
 * An object containing a list, to be used to represent a list of customer IDs.
 */
@Data
public class CustomerClientIdsCollection {

    @JsonProperty("customers")
    private Set<String> customerIds;

}
