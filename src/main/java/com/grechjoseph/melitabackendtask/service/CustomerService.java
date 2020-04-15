package com.grechjoseph.melitabackendtask.service;

import com.grechjoseph.melitabackendtask.domain.data.Customer;

import java.util.Set;

public interface CustomerService {

    Customer createCustomer(final Customer cutomer);

    Customer getCustomerById(final String customerId);

    Set<Customer> getAllCustomers();


}
