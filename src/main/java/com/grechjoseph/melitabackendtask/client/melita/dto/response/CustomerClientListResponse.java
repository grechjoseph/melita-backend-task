package com.grechjoseph.melitabackendtask.client.melita.dto.response;

import com.grechjoseph.melitabackendtask.client.melita.dto.CustomerClientIdsCollection;
import com.grechjoseph.melitabackendtask.client.melita.dto.CustomerClientResponseCode;
import lombok.Data;

/**
 * The Response obtained when retrieving all Customer IDs.
 */
@Data
public class CustomerClientListResponse {

    private final int sequenceId;
    private final CustomerClientIdsCollection data;
    private final CustomerClientResponseCode code;

}
