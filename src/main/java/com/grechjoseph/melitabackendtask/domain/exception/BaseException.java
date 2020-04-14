package com.grechjoseph.melitabackendtask.domain.exception;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

/**
 * The {@link RuntimeException} to handle errors within the system.
 */
@Data
@RequiredArgsConstructor
public class BaseException extends RuntimeException {

    private final ErrorCode errorCode;
    private final UUID id;

}
