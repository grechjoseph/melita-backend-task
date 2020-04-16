package com.grechjoseph.melitabackendtask.service.impl;

import com.grechjoseph.melitabackendtask.client.melita.client.MelitaCustomerClient;
import com.grechjoseph.melitabackendtask.domain.data.Customer;
import com.grechjoseph.melitabackendtask.domain.exception.BaseException;
import com.grechjoseph.melitabackendtask.mapping.ModelMapper;
import com.grechjoseph.melitabackendtask.service.CustomerService;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

import static com.grechjoseph.melitabackendtask.domain.exception.ErrorCode.*;
import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * Service responsible for retrieving/adding {@link Customer} objects.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MelitaCustomerServiceImpl implements CustomerService {

    private final MelitaCustomerClient melitaCustomerClient;
    private final ModelMapper mapper;

    /**
     * Create a {@link Customer}.
     * @param customer The {@link Customer} to create.
     * @return The created {@link Customer}.
     */
    @CacheEvict(value = "customer-cache", allEntries = true)
    @Override
    public Customer createCustomer(final Customer customer) {
        log.debug("Creating Customer.");
        return mapper.map(melitaCustomerClient.createCustomer(customer).getData(), Customer.class);
    }

    /**
     * Get a {@link Customer} by its ID.
     * @param customerId The ID by which to query.
     * @return The retrieved {@link Customer}.
     */
    @Cacheable(value = "customer-cache", key = "'Customer' + #customerId")
    @Override
    public Customer getCustomerById(final String customerId) {
        log.debug("Retrieving Customer by ID '{}'.", customerId);
        try {
            Customer customer = mapper.map(melitaCustomerClient.getCustomerById(customerId).getData(), Customer.class);
            customer.setId(customerId);
            return customer;
        } catch (final FeignException ex) {
            throw new BaseException(ex.status() == NOT_FOUND.value() ?
                    CUSTOMER_FIND_BY_ID_NOT_FOUND : CUSTOMER_FIND_BY_ID_UNKNOWN);
        }
    }

    /**
     * Retrieve all {@link Customer} objects.
     * @return {@link Set} of {@link Customer} objects retrieved.
     */
    @Cacheable(value = "customer-cache")
    @Override
    public Set<Customer> getAllCustomers() {
        log.debug("Retrieving all Customers.");
        return getAllCustomerIds().stream()
                .map(this::getCustomerById)
                .collect(Collectors.toSet());
    }

    /**
     * Retrieve all Customer IDs.
     * @return {@link Set} of Customer IDs.
     */
    private Set<String> getAllCustomerIds() {
        log.debug("Fetching all Customer IDs.");
        try {
            return melitaCustomerClient.getCustomers().getData().getCustomerIds();
        } catch (final FeignException ex) {
            throw new BaseException(FEIGN_CLIENT_UNREACHABLE);
        }
    }

}
