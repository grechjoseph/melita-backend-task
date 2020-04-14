package com.grechjoseph.melitabackendtask.config;

import com.grechjoseph.melitabackendtask.client.melita.client.MelitaCustomerClient;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
@EnableFeignClients(clients = MelitaCustomerClient.class)
public class MelitaBackendTaskConfig {
}
