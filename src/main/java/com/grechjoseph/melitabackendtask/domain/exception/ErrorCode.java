package com.grechjoseph.melitabackendtask.domain.exception;

/**
 * The Error Code to be assigned to the {@link BaseException}.
 * Naming: ENTITYNAME_ACTIONNAME_ERRORNAME.
 */
public enum ErrorCode {

    CUSTOMER_FIND_BY_ID_NOT_FOUND,
    CUSTOMER_FIND_BY_ID_UNKNOWN,
    FEIGN_CLIENT_UNREACHABLE

}
