package com.grechjoseph.melitabackendtask.service;

import com.grechjoseph.melitabackendtask.domain.data.Customer;

import java.util.Set;
import java.util.UUID;

public interface CustomerService {

    Customer createCustomer(final Customer cutomer);

    Customer getCustomerById(final UUID customerId);

    Set<Customer> getAllCustomers();


}
