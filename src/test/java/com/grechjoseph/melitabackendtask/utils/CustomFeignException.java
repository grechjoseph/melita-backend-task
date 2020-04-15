package com.grechjoseph.melitabackendtask.utils;

import feign.FeignException;

public class CustomFeignException extends FeignException {
    public CustomFeignException(int status, String message) {
        super(status, message);
    }
}
