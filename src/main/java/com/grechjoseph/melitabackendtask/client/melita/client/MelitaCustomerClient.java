package com.grechjoseph.melitabackendtask.client.melita.client;

import com.grechjoseph.melitabackendtask.client.melita.dto.response.CustomerClientListResponse;
import com.grechjoseph.melitabackendtask.client.melita.dto.response.CustomerClientResponse;
import com.grechjoseph.melitabackendtask.domain.data.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Feign Client responsible of interacting with the Melita endpoints.
 */
@FeignClient(name = "melitaclient" , url = "${melita.url}")
public interface MelitaCustomerClient {

    /**
     * Create a {@link Customer} object at Melita.
     * @param request {@link Customer} object to create.
     * @return {@link CustomerClientResponse} with the created Customer object.
     */
    @PutMapping("${melita.endpoints.customer}")
    CustomerClientResponse createCustomer(@RequestBody final Customer request);

    /**
     * Get a Customer object by its ID.
     * @param id The ID by which to query.
     * @return {@link CustomerClientResponse} object with the retrieved (if any) Customer object.
     */
    @GetMapping("${melita.endpoints.customer}/{id}")
    CustomerClientResponse getCustomerById(@PathVariable("id") final String id);

    /**
     * Get a list of all customer IDs at Melita.
     * @return {@link CustomerClientListResponse} object with all customer IDs at Melita.
     */
    @GetMapping("${melita.endpoints.customer}")
    CustomerClientListResponse getCustomers();

}
