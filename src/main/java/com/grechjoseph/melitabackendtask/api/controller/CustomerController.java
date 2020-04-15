package com.grechjoseph.melitabackendtask.api.controller;

import com.grechjoseph.melitabackendtask.api.dto.ApiCustomer;
import com.grechjoseph.melitabackendtask.api.dto.ApiExtendedCustomer;
import com.grechjoseph.melitabackendtask.domain.data.Customer;
import com.grechjoseph.melitabackendtask.mapping.ModelMapper;
import com.grechjoseph.melitabackendtask.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * REST Controller for managing the Customer entities.
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;
    private final ModelMapper mapper;

    /**
     * Create a new {@link ApiCustomer}.
     * @param apiCustomer {@link ApiCustomer} object to create.
     * @return The created {@link ApiCustomer} object.
     */
    @PutMapping()
    public ApiCustomer createCustomer(@RequestBody @Valid final ApiCustomer apiCustomer) {
        log.info("Creating Customer with [Name = {}, Surname = {}, Type = {}, Status = {}].",
                apiCustomer.getName(), apiCustomer.getSurname(), apiCustomer.getType(), apiCustomer.getStatus());
        return mapper.map(
                customerService.createCustomer(mapper.map(apiCustomer, Customer.class)),
                ApiCustomer.class);
    }

    /**
     * Get {@link Customer} by ID.
     * @param customerId The ID by which to query.
     * @return The {@link ApiCustomer} object retrieved.
     */
    @GetMapping("/{customerId}")
    public ApiCustomer getCustomerById(@PathVariable final String customerId) {
        log.info("Retrieving Customer with ID = {}.", customerId);
        return mapper.map(customerService.getCustomerById(customerId), ApiCustomer.class);
    }

    /**
     * Get All {@link ApiCustomer} instances.
     * @return A {@link List} of {@link ApiCustomer} objects.
     */
    @GetMapping()
    public List<ApiExtendedCustomer> getAllCustomers() {
        log.info("Retrieving all Customers.");
        return mapper.mapAsList(customerService.getAllCustomers(), ApiExtendedCustomer.class);
    }

}
