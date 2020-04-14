package com.grechjoseph.melitabackendtask.client.melita.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Interceptor for Feign to add headers to each request.
 */
@Component
public class HeaderInterceptor implements RequestInterceptor {
    private static final String CANDIDATE_ID_HEADER = "candidate-id";

    @Value("${melita.candidateId}")
    private String candidateId;

    @Override
    public void apply(final RequestTemplate requestTemplate) {
        requestTemplate.header(CANDIDATE_ID_HEADER, candidateId);
    }
}
