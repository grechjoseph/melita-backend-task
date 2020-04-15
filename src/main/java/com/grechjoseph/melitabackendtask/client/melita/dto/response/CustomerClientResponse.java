package com.grechjoseph.melitabackendtask.client.melita.dto.response;

import com.grechjoseph.melitabackendtask.api.dto.ApiCustomer;
import com.grechjoseph.melitabackendtask.client.melita.dto.CustomerClientResponseCode;
import lombok.Data;

/**
 * The response received when getting a Customer by its ID.
 */
@Data
public class CustomerClientResponse {

    private final int sequenceId;
    private final ApiCustomer data;
    private final CustomerClientResponseCode code;
}
