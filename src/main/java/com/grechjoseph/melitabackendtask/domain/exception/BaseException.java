package com.grechjoseph.melitabackendtask.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * The {@link RuntimeException} to handle errors within the system.
 */
@Data
@AllArgsConstructor
public class BaseException extends RuntimeException {

    private final ErrorCode errorCode;

}
